package com.atguigu.cloud.handler;


import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-31-16:33
 */
@Component
public class MyRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("serverName");  // 从请求参数中获取serverName作为来源
    }
}

