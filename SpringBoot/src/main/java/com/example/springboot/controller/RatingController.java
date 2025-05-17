package com.example.springboot.controller;

import org.springframework.web.bind.annotation.*;
import com.example.springboot.common.Result;
import com.example.springboot.service.RatingService;
import javax.annotation.Resource;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Resource
    private RatingService ratingService;

    // 查询某本书的平均分
    @GetMapping("/avg")
    public Result<?> avg(@RequestParam Long bookId) {
        return ratingService.avg(bookId);
    }

    // 查询某用户对某书的评分
    @GetMapping("/user")
    public Result<?> userRating(@RequestParam Long userId, @RequestParam Long bookId) {
        return ratingService.userRating(userId, bookId);
    }
} 