/**
 * ClassName: DashboardController
 * Package: com.example.springboot.controller
 * Description: 首页仪表盘控制器，用于统计系统当前访问量、用户数、图书数、借阅记录等数据。
 *
 * @Author WXK
 * @Create 2025/4/9 17:45
 * @Version 1.0
 */

package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 控制器类：负责处理首页仪表盘数据统计接口
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Resource
    private DashboardService dashboardService;

    /**
     * 获取系统当前的仪表盘统计数据
     * @return 包含访问量、用户数、图书数、借阅记录数的结果对象
     */
    @GetMapping
    public Result<?> dashboardRecords() {
        return dashboardService.getDashboardRecords();
    }
}