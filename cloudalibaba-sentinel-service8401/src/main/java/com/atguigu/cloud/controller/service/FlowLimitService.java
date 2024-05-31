package com.atguigu.cloud.controller.service;

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
    @SentinelResource(value = "common")
    public void common()
    {
        System.out.println("------FlowLimitService come in");
    }
}
