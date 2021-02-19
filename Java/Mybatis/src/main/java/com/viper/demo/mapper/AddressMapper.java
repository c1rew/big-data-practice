package com.viper.demo.mapper;

import com.viper.demo.domain.AddressDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 地址mapper接口
 * @author c1rew
 * @create 2021-02-19 18:27
 */
public interface AddressMapper {

    // 根据id查询Address对象
    AddressDO find(long id);
    // 查询一个用户的全部地址信息
    List<AddressDO> findAll(long customerId);
    // 查询指定订单的送货地址
    AddressDO findByOrderId(long orderId);
    // 存储Address对象，同时会记录关联的Customer
    int save(@Param("address") AddressDO addressDO,
             @Param("customerId") long customerId);
}
