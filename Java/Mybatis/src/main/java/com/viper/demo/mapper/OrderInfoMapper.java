package com.viper.demo.mapper;


import com.viper.demo.domain.OrderInfoDO;

import java.util.List;

/**
 * @author c1rew
 * @create 2021-02-19 18:29
 */
public interface OrderInfoMapper {

    // 根据订单Id查询
    OrderInfoDO find(long id);
    // 查询一个用户一段时间段内的订单列表
    List<OrderInfoDO> findByCustomerId(long customerId, long startTime, long endTime);
    // 保存一个订单
    long save(OrderInfoDO orderInfoDO);
}
