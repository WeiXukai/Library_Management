package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:UserServiceImpl
 * Package: com.example.springboot.service.impl
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/20 11:44
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result<?> register(User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res != null) {
            return Result.error("-1", "用户名已重复");
        }
        user.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
        userMapper.insert(user);
        return Result.success();
    }

    @Override
    public Result<?> getCode(String username) {
        // 生成4位验证码
        String code = String.valueOf((int)((Math.random()*9+1)*1000));
        // 存入Redis，有效期5分钟
        stringRedisTemplate.opsForValue().set("login:code:" + username, code, 5, TimeUnit.MINUTES);
        // 实际项目应返回图片验证码，这里直接返回字符串（前端可自行渲染为图片）
        return Result.success(code);
    }

    @Override
    public Result<?> login(String username, String password) {
        // 只校验用户名和密码
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, username)
                .eq(User::getPassword, password));
        if (user == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        // 校验账号状态
        if (user.getStatus() == null || user.getStatus() != 1) {
            return Result.error("-1", "账号已被禁用");
        }
        // 登录成功，返回用户信息（包含role）
        return Result.success(user);
    }

    @Override
    public Result<?> save(User user) {
        if (user.getPassword() == null) user.setPassword("123456");
        user.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
        userMapper.insert(user);
        return Result.success();
    }

    @Override
    public Result<?> updatePassword(Long id, String password2) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        User user = new User();
        user.setPassword(password2);
        userMapper.update(user, wrapper);
        return Result.success();
    }

    @Override
    public Result<?> updateInfo(User user) {
        // 支持修改email、role、status等
        userMapper.updateById(user);
        return Result.success();
    }

    @Override
    public Result<?> deleteBatch(List<Long> ids) {
        userMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @Override
    public Result<?> deleteById(Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result<?> findPage(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(search)) {
            wrapper.like(User::getUsername, search)
                   .or().like(User::getEmail, search);
        }
        // 支持按role/status过滤
        // 可根据前端需求扩展参数
        Page<User> page = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(page);
    }

    @Override
    public Result<?> userSearch(Integer pageNum, Integer pageSize, Long id, String username, String email) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        if (id != null) {
            wrapper.eq(User::getId, id);
        }
        if (StringUtils.isNotBlank(username)) {
            wrapper.like(User::getUsername, username);
        }
        if (StringUtils.isNotBlank(email)) {
            wrapper.like(User::getEmail, email);
        }
        Page<User> page = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(page);
    }
}