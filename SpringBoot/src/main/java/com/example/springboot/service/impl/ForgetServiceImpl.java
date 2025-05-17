package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.ForgetService;

// import com.example.springboot.utils.SmsUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * ClassName:ForgetServiceImpl
 * Package: com.example.springboot.service.impl
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/20 11:38
 * @Version 1.0
 */
@Service
public class ForgetServiceImpl implements ForgetService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserMapper userMapper;

    /**
     * 生成验证码并存入 Redis，同时模拟发送短信
     */
    @Override
    public Result<?> getCode(String username) {
        // 根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return Result.error("-1", "用户名不存在");
        }
        // 直接返回成功，无需手机号和验证码
        return Result.success();
    }

    /**
     * 只通过用户名和新密码重置密码
     */
    @Override
    public Result<?> resetPassword(User user) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUsername, user.getUsername());
        User existingUser = userMapper.selectOne(wrapper);
        if (existingUser == null) {
            return Result.error("-1", "用户不存在");
        }
        existingUser.setPassword(user.getPassword());
        userMapper.updateById(existingUser);
        return Result.success();
    }
}
