package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Rating;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.mapper.BorrowMapper;
import com.example.springboot.mapper.RatingMapper;
import com.example.springboot.service.RecommendService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:RecommendServiceImpl
 * Package: com.example.springboot.service.impl
 * Description: 推荐系统实现类，包含三种推荐逻辑：
 * 1. 基于借阅历史分析的个性化推荐
 * 2. 热门图书推荐
 * 3. 基于协同过滤的用户兴趣推荐（UserCF）
 *
 * 使用 Redis 缓存协同过滤结果，提高响应效率
 *
 * @Author WXK
 * @Create 2025/4/21 14:14
 * @Version 1.0
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource
    private BookMapper bookMapper; // 图书表操作接口

    @Resource
    private BorrowMapper borrowMapper; // 借阅表操作接口

    @Resource
    private RatingMapper ratingMapper; // 评分表操作接口

    @Resource
    private StringRedisTemplate stringRedisTemplate; // Redis 用于缓存推荐结果

    @Resource
    private ObjectMapper objectMapper; // Jackson 用于对象序列化/反序列化

    /**
     * 根据用户借阅偏好进行推荐（推荐与用户借阅过相似作者的图书）
     * 若用户无借阅记录，则推荐全局热门图书
     */
    @Override
    public Result<?> recommendBooks(Long userId, Integer pageNum, Integer pageSize, String name) {
        // 获取该用户所有借阅记录
        List<Borrow> records = borrowMapper.selectList(
                new QueryWrapper<Borrow>().eq("user_id", userId));

        List<Book> candidates;

        // 若用户没有借阅记录，则推荐全局热门图书
        if (records == null || records.isEmpty()) {
            candidates = bookMapper.selectList(Wrappers.<Book>lambdaQuery()
                    .like(StringUtils.isNotBlank(name), Book::getTitle, name)
                    .orderByDesc(Book::getBorrowCount));
        } else {
            // 统计借阅记录中最常出现的作者
            Map<String, Integer> authorCount = new HashMap<>();
            for (Borrow r : records) {
                Book b = bookMapper.selectById(r.getBookId());
                if (b != null) {
                    authorCount.put(b.getAuthor(), authorCount.getOrDefault(b.getAuthor(), 0) + 1);
                }
            }
            // 获取用户偏好作者
            String favoriteAuthor = authorCount.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("未知");

            // 推荐该作者下其他热门图书
            candidates = bookMapper.selectList(Wrappers.<Book>lambdaQuery()
                    .eq(Book::getAuthor, favoriteAuthor)
                    .like(StringUtils.isNotBlank(name), Book::getTitle, name)
                    .orderByDesc(Book::getBorrowCount));
        }

        // 手动分页处理
        int total = candidates.size();
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, total);
        List<Book> pageList = start >= total ? Collections.emptyList() : candidates.subList(start, end);

        Page<Book> page = new Page<>(pageNum, pageSize);
        page.setTotal(total);
        page.setRecords(pageList);
        return Result.success(page);
    }

    /**
     * 推荐借阅量最高的热门图书
     */
    @Override
    public Result<?> hotRecommend(Integer pageNum, Integer pageSize) {
        // 查询所有图书按借阅量降序排列
        Page<Book> page = new Page<>(pageNum, pageSize);
        List<Book> books = bookMapper.selectList(Wrappers.<Book>lambdaQuery()
                .orderByDesc(Book::getBorrowCount));

        // 手动分页
        int total = books.size();
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, total);
        List<Book> pageList = start >= total ? Collections.emptyList() : books.subList(start, end);
        // 设置推荐指数字段
        for (Book book : pageList) {
            book.setRecommendIndex(book.getBorrowCount() == null ? 0.0 : book.getBorrowCount().doubleValue());
        }
        page.setTotal(total);
        page.setRecords(pageList);
        return Result.success(page);
    }

    /**
     * 基于用户协同过滤的推荐（UserCF）
     * 寻找借阅行为相似的用户并推荐其借阅但我未借阅的图书
     */
    @Override
    public Result<?> cfRecommend(Long userId, Integer pageNum, Integer pageSize) {
        String cacheKey = "recommend:cf:" + userId + ":" + pageNum + ":" + pageSize;
        String cacheValue = stringRedisTemplate.opsForValue().get(cacheKey);
        if (cacheValue != null) {
            try {
                Page<Book> page = objectMapper.readValue(cacheValue,
                        objectMapper.getTypeFactory().constructParametricType(Page.class, Book.class));
                return Result.success(page);
            } catch (Exception e) {
                // 反序列化失败，忽略缓存
            }
        }
        // 1. 读取所有评分数据，构建评分矩阵 userId -> (bookId -> score)
        List<Rating> allRatings = ratingMapper.selectList(null);
        Map<Long, Map<Long, Double>> userRatingMap = new HashMap<>();
        for (Rating r : allRatings) {
            userRatingMap.computeIfAbsent(r.getUserId(), k -> new HashMap<>()).put(r.getBookId(), r.getScore() == null ? 0.0 : r.getScore().doubleValue());
        }
        // 2. 获取当前用户评分
        Map<Long, Double> uRatings = userRatingMap.get(userId);
        if (uRatings == null || uRatings.isEmpty()) {
            // 没有评分记录，退化为热门推荐
            return hotRecommend(pageNum, pageSize);
        }
        // 3. 计算当前用户与其他用户的余弦相似度
        Map<Long, Double> simMap = new HashMap<>();
        for (Long v : userRatingMap.keySet()) {
            if (v.equals(userId)) continue;
            Map<Long, Double> vRatings = userRatingMap.get(v);
            double sum = 0, sum1 = 0, sum2 = 0;
            for (Long bookId : uRatings.keySet()) {
                if (vRatings.containsKey(bookId)) {
                    double ru = uRatings.get(bookId);
                    double rv = vRatings.get(bookId);
                    sum += ru * rv;
                    sum1 += ru * ru;
                    sum2 += rv * rv;
                }
            }
            if (sum1 > 0 && sum2 > 0) {
                simMap.put(v, sum / (Math.sqrt(sum1) * Math.sqrt(sum2)));
            }
        }
        // 4. 预测当前用户未评分图书的兴趣分
        Set<Long> allBookIds = new HashSet<>();
        for (Map<Long, Double> m : userRatingMap.values()) allBookIds.addAll(m.keySet());
        Map<Long, Double> predictScore = new HashMap<>();
        for (Long bookId : allBookIds) {
            if (uRatings.containsKey(bookId)) continue; // 已评分
            double numerator = 0, denominator = 0;
            for (Long v : simMap.keySet()) {
                Map<Long, Double> vRatings = userRatingMap.get(v);
                if (vRatings.containsKey(bookId)) {
                    double sim = simMap.get(v);
                    numerator += sim * vRatings.get(bookId);
                    denominator += Math.abs(sim);
                }
            }
            if (denominator > 0) {
                predictScore.put(bookId, numerator / denominator);
            }
        }
        // 5. 按预测分降序排序，分页取Top-N
        List<Map.Entry<Long, Double>> sorted = new ArrayList<>(predictScore.entrySet());
        sorted.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        int total = sorted.size();
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, total);
        List<Book> recommendBooks = new ArrayList<>();
        for (int i = start; i < end; i++) {
            Long bookId = sorted.get(i).getKey();
            Book b = bookMapper.selectById(bookId);
            if (b != null) {
                b.setRecommendIndex(sorted.get(i).getValue()); // 设置兴趣分到Book对象
                recommendBooks.add(b);
            }
        }
        Page<Book> page = new Page<>(pageNum, pageSize);
        page.setTotal(total);
        page.setRecords(recommendBooks);
        try {
            stringRedisTemplate.opsForValue().set(cacheKey,
                    objectMapper.writeValueAsString(page), 10, TimeUnit.MINUTES);
        } catch (Exception e) {
            // 缓存失败忽略
        }
        return Result.success(page);
    }
}
