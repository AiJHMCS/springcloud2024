package com.atguigu.cloud.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-24-20:38
 */
@Configuration
@LoadBalancerClient(
        value = "cloud-payment-service",configuration = RestTemplateConfig.class)  // 指定要调用的微服务名称，需要更改负载均衡算法的时候才加上
public class RestTemplateConfig
{
    @Bean
    @LoadBalanced // consul是支持负载均衡（默认是轮询）的，这里启用restTemplate支持负载均衡，否则其使用对象order调用payment时会报错Caused by: java.net.UnknownHostException: cloud-payment-service%20
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    // 将默认的轮询RoundRobinLoadBalancer修改为RandomLoadBalancer
    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
                                                            LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);

        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}
/*
负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启动后rest接口计数从1开始。
List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
如：List [0] instances = 127.0.0.1:8002
   List [1] instances = 127.0.0.1:8001
   8001+ 8002 组合成为集群，它们共计2台机器，集群总数为2， 按照轮询算法原理：
当总请求数为1时： 1 % 2 =1 对应下标位置为1 ，则获得服务地址为127.0.0.1:8001
当总请求数位2时： 2 % 2 =0 对应下标位置为0 ，则获得服务地址为127.0.0.1:8002
当总请求数位3时： 3 % 2 =1 对应下标位置为1 ，则获得服务地址为127.0.0.1:8001
当总请求数位4时： 4 % 2 =0 对应下标位置为0 ，则获得服务地址为127.0.0.1:8002
如此类推......
*/
/*
* 官网的负载均衡算法有 轮询 和 随机 ：RoundRobinLoadBalancer and RandomLoadBalancer
* */