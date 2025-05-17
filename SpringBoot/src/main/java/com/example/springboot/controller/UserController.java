package com.example.springboot.controller;

/**
 * ClassName:UserController
 * Package: com.example.springboot.controller
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/9 15:56
 * @Version 1.0
 */


import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getcode")
    public Result<?> getCode(@RequestParam String username) {
        return userService.getCode(username);
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping
    public Result<?> save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/password")
    public Result<?> updatePassword(@RequestParam Long id,
                                    @RequestParam String password2) {
        return userService.updatePassword(id, password2);
    }

    @PutMapping("/update")
    public Result<?> updateInfo(@RequestBody User user) {
        return userService.updateInfo(user);
    }

    @PostMapping("/deleteBatch")
    public Result<?> deleteBatch(@RequestBody List<Long> ids) {
        return userService.deleteBatch(ids);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        return userService.findPage(pageNum, pageSize, search);
    }

    @GetMapping("/usersearch")
    public Result<?> userSearch(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(required = false) Long id,
                                @RequestParam(required = false) String username,
                                @RequestParam(required = false) String email) {
        return userService.userSearch(pageNum, pageSize, id, username, email);
    }
}