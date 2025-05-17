package com.example.springboot.service;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Rating;

public interface RatingService {
    Result<?> rate(Rating rating);
    Result<?> avg(Long bookId);
    Result<?> userRating(Long userId, Long bookId);
} 