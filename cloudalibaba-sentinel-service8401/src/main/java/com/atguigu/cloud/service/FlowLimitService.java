package com.atguigu.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;
/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-31-14:55
 */
@Service
public class FlowLimitService
{
    @SentinelResource(value = "common")  // 一个流量防卫防护组件注解，用于指定防护资源，对配置的资源进行流量控制、熔断降级等功能。
    public void common()
    {
        System.out.println("------FlowLimitService come in");
    }
}
