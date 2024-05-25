package com.atguigu.cloud.responce;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-24-19:24
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {

    private String code;/** 结果状态 ,具体状态码参见枚举类ReturnCodeEnum.java*/
    private String message;
    private T data;
    private long timestamp ;


    public ResultData (){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        if(data == null){
            throw new RuntimeException("data can not be null");
        }
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(String code, String message) {
        // 失败状态码传什么返回什么
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);

        return resultData;
    }

}
