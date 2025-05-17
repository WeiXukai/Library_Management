package com.example.springboot.service;

/**
 * ClassName:DashboardService
 * Package: com.example.springboot.service
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/20 11:32
 * @Version 1.0
 */
import com.example.springboot.common.Result;


public interface DashboardService {
    Result<?> getDashboardRecords();
}