package com.atguigu.cloud.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-31-16:07
 */
@RestController
@Slf4j
public class RateLimitController {
    // 默认的限流提示(Blocked by Sentinel (flow limiting))
    @GetMapping("/rateLimit/byUrl")
    public String byUrl() {
        return "按rest地址限流测试OK";
    }

    // 返回自定义限流的提示
    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResourceSentinelResource", blockHandler = "handleException")
    public String byResource() {
        return "按资源名称SentinelResource限流测试OK";
    }
    public String handleException(BlockException exception) {  // 自定义限流提示
        return "服务不可用@SentinelResource启动" + "\t" + "o(╥﹏╥)o";
    }

    // 自定义限流提示+程序异常返回fallback服务降级
    @GetMapping("/rateLimit/doAction/{p1}")
    @SentinelResource(value = "doActionSentinelResource",
            blockHandler = "doActionBlockHandler", fallback = "doActionFallback")
    // 两者同时共存时：
    // blockHandler，主要针对sentinel配置后，对于配置而言出现的违规情况进行处理
    // fallback，程序异常了JVM抛出的异常服务降级，即p1=0时
    public String doAction(@PathVariable("p1") Integer p1) {
        if (p1 == 0){
            throw new RuntimeException("p1等于零直接异常");
        }
        return "doAction";
    }

    public String doActionBlockHandler(@PathVariable("p1") Integer p1, BlockException e){
        log.error("sentinel配置自定义限流了:{}", e);
        return "sentinel配置自定义限流了";
    }

    public String doActionFallback(@PathVariable("p1") Integer p1,Throwable e){
        log.error("程序逻辑异常了:{}", e);
        return "程序逻辑异常了"+"\t"+e.getMessage();
    }

}
