package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Rating;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.mapper.RatingMapper;
import com.example.springboot.service.RatingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Resource
    private RatingMapper ratingMapper;

    @Resource
    private BookMapper bookMapper;

    @Override
    public Result<?> rate(Rating rating) {
        // 先查是否已评分
        QueryWrapper<Rating> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", rating.getUserId()).eq("book_id", rating.getBookId());
        Rating old = ratingMapper.selectOne(wrapper);
        if (old != null) {
            // 更新评分
            old.setScore(rating.getScore());
            ratingMapper.updateById(old);
        } else {
            // 新增评分
            ratingMapper.insert(rating);
        }
        // 更新Book表的ratingCount和ratingSum
        List<Rating> ratings = ratingMapper.selectList(new QueryWrapper<Rating>().eq("book_id", rating.getBookId()));
        int count = ratings.size();
        int sum = ratings.stream().mapToInt(Rating::getScore).sum();
        Book book = bookMapper.selectById(rating.getBookId());
        if (book != null) {
            book.setRatingCount(count);
            book.setRatingSum(sum);
            bookMapper.updateById(book);
        }
        return Result.success();
    }

    @Override
    public Result<?> avg(Long bookId) {
        List<Rating> ratings = ratingMapper.selectList(new QueryWrapper<Rating>().eq("book_id", bookId));
        if (ratings.isEmpty()) return Result.success(0);
        double avg = ratings.stream().mapToInt(Rating::getScore).average().orElse(0);
        return Result.success(avg);
    }

    @Override
    public Result<?> userRating(Long userId, Long bookId) {
        QueryWrapper<Rating> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("book_id", bookId);
        Rating rating = ratingMapper.selectOne(wrapper);
        return Result.success(rating != null ? rating.getScore() : 0);
    }
} 