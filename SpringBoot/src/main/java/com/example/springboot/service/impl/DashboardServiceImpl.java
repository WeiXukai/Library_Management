package com.example.springboot.service.impl;

import com.example.springboot.common.LoginUser;
import com.example.springboot.common.Result;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.mapper.BorrowMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.DashboardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:DashboardServiceImpl
 * Package: com.example.springboot.service.impl
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/20 11:32
 * @Version 1.0
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BookMapper bookMapper;

    @Resource
    private BorrowMapper borrowMapper;

    /**
     * 获取系统首页仪表盘的统计信息，包括访问量、用户数、图书数、借阅记录数
     */
    @Override
    public Result<?> getDashboardRecords() {
        // 访问量从静态类 LoginUser 中获取
        int visitCount = LoginUser.getVisitCount();

        // 查询数据库统计数量
        int userCount = Math.toIntExact(userMapper.selectCount(null));
        int bookCount = Math.toIntExact(bookMapper.selectCount(null));
        int lendRecordCount = Math.toIntExact(borrowMapper.selectCount(null));

        // 封装返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("visitCount", visitCount);
        map.put("userCount", userCount);
        map.put("bookCount", bookCount);
        map.put("lendRecordCount", lendRecordCount);

        return Result.success(map);
    }
}