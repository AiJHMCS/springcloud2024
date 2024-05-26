package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-26-19:20
 */
@Configuration
public class FeignConfig
{
    @Bean
    public Retryer myRetryer()
    {
//        return Retryer.NEVER_RETRY; //Feign默认配置是不走重试策略的

        //重试间隔时间为100ms，重试间最大间隔时间为1s，最大请求次数为3（1（default）+2）
        return new Retryer.Default(100,1,3);  // 默认(100, SECONDS.toMillis(1), 5)
    }

    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }

}
