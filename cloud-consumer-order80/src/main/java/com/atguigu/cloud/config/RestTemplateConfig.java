package com.atguigu.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-24-20:38
 */
@Configuration
public class RestTemplateConfig
{
    @Bean
    // consul是支持负载均衡的，这里启用restTemplate支持负载均衡，否则其使用对象order调用payment时会报错Caused by: java.net.UnknownHostException: cloud-payment-service%20
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
