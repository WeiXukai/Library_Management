package com.example.springboot.service;

import com.example.springboot.common.Result;
import com.example.springboot.entity.User;

import java.util.List;

/**
 * ClassName:UserService
 * Package: com.example.springboot.service
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/20 11:43
 * @Version 1.0
 */
public interface UserService {

    Result<?> register(User user);

    Result<?> login(String username, String password);

    Result<?> save(User user);

    Result<?> updatePassword(Long id, String password2);

    Result<?> updateInfo(User user);

    Result<?> deleteBatch(List<Long> ids);

    Result<?> deleteById(Long id);

    Result<?> findPage(Integer pageNum, Integer pageSize, String search);

    Result<?> getCode(String username);

    Result<?> userSearch(Integer pageNum, Integer pageSize, Long id, String username, String email);
}
