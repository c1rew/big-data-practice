package com.viper.demo.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单类
 * @author c1rew
 * @create 2021-02-19 18:22
 */
public class OrderInfoDO {

    /**
     * 订单id
     */
    private long id;
    /**
     * 订单用户
     */
    private CustomerDO customerDO;
    /**
     * 快递地址
     */
    private AddressDO addressDO;
    /**
     * 订单商品集合
     */
    private List<OrderItemDO> orderItemDOs = new ArrayList<>();
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 商品总价
     */
    private BigDecimal totalPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomerDO getCustomerDO() {
        return customerDO;
    }

    public void setCustomerDO(CustomerDO customerDO) {
        this.customerDO = customerDO;
    }

    public AddressDO getAddressDO() {
        return addressDO;
    }

    public void setAddressDO(AddressDO addressDO) {
        this.addressDO = addressDO;
    }

    public List<OrderItemDO> getOrderItemDOs() {
        return orderItemDOs;
    }

    public void setOrderItemDOs(List<OrderItemDO> orderItemDOs) {
        this.orderItemDOs = orderItemDOs;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerDO=" + customerDO +
                ", addressDO=" + addressDO +
                ", orderItems=" + orderItemDOs +
                ", createTime=" + createTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
