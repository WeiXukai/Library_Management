package com.example.springboot.common;

/**
 * ClassName:MybatisPlusConfig
 * Package: com.example.springboot.common
 * Description:
 *
 * @Author WXK
 * @Create 2025/4/9 15:50
 * @Version 1.0
 */

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 分页配置类。
 * 该配置类主要用于注册分页插件，使 MyBatis-Plus 查询支持分页功能。
 */
@Configuration  // 声明为配置类，相当于 Spring XML 中的 <bean>
@MapperScan("com.example.springboot.mapper")  // 扫描 Mapper 接口所在包，自动注入到 Spring 容器
public class MybatisPlusConfig {

    /**
     * 注册 MyBatis-Plus 的主插件拦截器（Interceptor）。
     * 在其中加入分页插件 PaginationInnerInterceptor，使所有分页查询生效。
     *
     * @return MybatisPlusInterceptor 插件链
     */
    @Bean  // Spring 容器中注册为 Bean，项目启动时自动加载
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 添加分页拦截器，指定数据库类型为 MYSQL，适配 SQL 方言
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        return interceptor;
    }
}
