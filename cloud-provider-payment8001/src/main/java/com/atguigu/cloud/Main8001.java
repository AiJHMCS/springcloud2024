package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-24-17:12
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul为注册中心时注册服务
@MapperScan("com.atguigu.cloud.mapper")  // 扫描mapper接口
@RefreshScope  // 使得consul的服务器配置能够实时刷新，不配置的话默认是55s刷新一次，在bootstrap.yml下也能手动调整，用watch
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
    }}