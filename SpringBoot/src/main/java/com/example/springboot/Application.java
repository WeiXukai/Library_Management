package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目主启动类，入口方法 main。
 * 包路径必须覆盖整个项目的包结构，才能扫描到全部组件。
 */
@SpringBootApplication
@Transactional
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("\n");
        System.out.println("SpringBoot is starting......");
    }
}
