package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-26-17:08
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul为注册中心时注册服务
@EnableFeignClients  // 表示开启Feign客户端功能
public class MainFegin80 {
    public static void main(String[] args) {
        SpringApplication.run(MainFegin80.class, args);
    }
}