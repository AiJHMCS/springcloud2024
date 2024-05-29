package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.responce.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-26-17:13
 */
@FeignClient(value = "cloud-payment-service")  // PayFeginApi是cloud-payment-service微服务的Feign接口，可供其他微服务调用
public interface PayFeignApi {  // PayFeginApi对外暴露以下方法，可供其他微服务调用
    /**
     * 新增一条支付相关流水记录
     * @param payDTO
     * @return
     */
    @PostMapping("/pay/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * 按照主键记录查询支付流水信息
     * @param id
     * @return
     */
    @GetMapping("/pay/get/{id}")
    ResultData getPayInfo(@PathVariable("id") Integer id);

    /**
     * openfeign天然支持负载均衡演示（默认轮询）
     * @return
     */
    @GetMapping(value = "/pay/get/info")
    String mylb();

    /**
     * Resilience4j CircuitBreaker 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    String myRatelimit(@PathVariable("id") Integer id);
}
