package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Pay;

import javax.crypto.interfaces.PBEKey;
import java.util.List;

/**
 * @ Author：Aijinhui
 * @ Description：
 * @ Date：2024-05-24-18:08
 */
public interface PayService
{
    int add(Pay pay);
    int delete(Integer id);
    int update(Pay pay);
    Pay getById(Integer id);
    List<Pay> getAll();
}
