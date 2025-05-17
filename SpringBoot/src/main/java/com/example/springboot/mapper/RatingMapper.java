package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Rating;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RatingMapper extends BaseMapper<Rating> {
} 