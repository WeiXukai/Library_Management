package com.example.springboot.service;

import com.example.springboot.common.Result;

/**
 * ClassName:RecommendService
 * Package: com.example.springboot.service
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/21 14:14
 * @Version 1.0
 */

public interface RecommendService {
    Result<?> recommendBooks(Long userId, Integer pageNum, Integer pageSize, String name);
    Result<?> hotRecommend(Integer pageNum, Integer pageSize);
    Result<?> cfRecommend(Long userId, Integer pageNum, Integer pageSize);
}