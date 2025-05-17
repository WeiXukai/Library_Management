package com.example.springboot.service;

import com.example.springboot.common.Result;
import com.example.springboot.entity.User;

/**
 * ClassName:ForgetService
 * Package: com.example.springboot.service
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/20 11:37
 * @Version 1.0
 */
public interface ForgetService {

    Result<?> getCode(String username);

    Result<?> resetPassword(User user);
}