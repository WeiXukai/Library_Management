/**
 * ClassName: ForgetController
 * Package: com.example.springboot.controller
 * Description: 用户找回密码控制器，提供获取验证码和验证重置功能。
 *
 * @Author WXK
 * @Create 2025/4/9 17:55
 * @Version 1.0
 */

package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.ForgetService;


import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 控制器类：处理找回密码功能（验证码验证 + 密码重置）
 */
@RestController
@RequestMapping("/forget")
public class ForgetController {

    @Resource
    private ForgetService forgetService;

    /**
     * 根据用户名获取验证码（模拟短信发送）
     */
    @GetMapping("/getcode")
    public Result<?> getcode(@RequestParam String username) {
        return forgetService.getCode(username);
    }

    /**
     * 验证验证码并重置用户密码
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        return forgetService.resetPassword(user);
    }
}