package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.responce.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-24-20:39
 */
@RestController
public class OrderController {
//    public static final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码

    // cloud-payment-service 下面可以有多个不同端口的payment微服务，直接使用http://cloud-payment-service即可，不需要写死端口号
    public static final String PaymentSrv_URL = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;
    // RestTemplate作用：用来发送http请求，在order模块中向payment模块发送请求，便可以直接调用payment模块的接口

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     *
     * @param payDTO
     * @return
     */
    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @DeleteMapping("/consumer/pay/del/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        restTemplate.delete(PaymentSrv_URL + "/pay/del/" + id);
        return ResultData.success(id);
    }

    @PutMapping("/consumer/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        restTemplate.put(PaymentSrv_URL + "/pay/update", payDTO);
        return ResultData.success("Update successful");
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul()
    {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }

    // 模拟consul用于发现客户端的原理是通过注册中心来存储服务信息
    // print：cloud-payment-service	localhost	8001	http://localhost:8001
    //        cloud-payment-service	localhost	8002	http://localhost:8002
    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> discoveryClientInstances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : discoveryClientInstances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return discoveryClientInstances.get(0).getServiceId()+":"+discoveryClientInstances.get(0).getPort();
    }

}
