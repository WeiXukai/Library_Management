package com.example.springboot.controller;

/**
 * ClassName:RecommendController
 * Package: com.example.springboot.controller
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/21 14:15
 * @Version 1.0
 */

import com.example.springboot.service.RecommendService;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.common.Result;

import javax.annotation.Resource;

/**
 * 控制器：处理图书推荐相关请求
 */
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Resource
    private RecommendService recommendService;

    @GetMapping
    public Result<?> recommend(@RequestParam String type,
                               @RequestParam(required = false) Long userId,
                               @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize) {
        if ("hot".equals(type)) {
            return recommendService.hotRecommend(pageNum, pageSize);
        } else if ("cf".equals(type) && userId != null) {
            return recommendService.cfRecommend(userId, pageNum, pageSize);
        } else {
            return Result.error("参数错误", null);
        }
    }
}
