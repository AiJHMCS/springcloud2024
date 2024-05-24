package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-24-17:12
 */
@SpringBootApplication
@MapperScan("com.atguigu.cloud.mapper")  // 扫描mapper接口
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
    }}