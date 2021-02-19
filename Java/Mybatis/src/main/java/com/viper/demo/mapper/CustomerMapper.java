package com.viper.demo.mapper;

import com.viper.demo.domain.CustomerDO;

/**
 * @author c1rew
 * @create 2021-02-19 18:29
 */
public interface CustomerMapper {
    // 根据用户Id查询Customer(不查询Address)
    CustomerDO find(long id);
    // 根据用户Id查询Customer(同时查询Address)
    CustomerDO findWithAddress(long id);
    // 根据orderId查询Customer
    CustomerDO findByOrderId(long orderId);
    // 持久化Customer对象
    int save(CustomerDO customerDO);
}
