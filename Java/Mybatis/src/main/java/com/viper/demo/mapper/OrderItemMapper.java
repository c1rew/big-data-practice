package com.viper.demo.mapper;

import com.viper.demo.domain.OrderItemDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author c1rew
 * @create 2021-02-19 18:29
 */
public interface OrderItemMapper {

    // 根据id查询OrderItem对象
    OrderItemDO find(long id);
    // 查询指定的订单中的全部OrderItem
    List<OrderItemDO> findByOrderId(long orderId);
    // 保存一个OrderItem信息
    long save(@Param("orderItem")OrderItemDO orderItemDO,
              @Param("orderId") long orderInfoId);
}
