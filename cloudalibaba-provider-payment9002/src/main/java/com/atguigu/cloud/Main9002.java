package com.atguigu.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-26-20:19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main9002
{
    public static void main(String[] args)
    {
        SpringApplication.run(Main9002.class,args);
    }
}