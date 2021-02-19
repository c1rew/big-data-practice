package com.viper.demo.domain;

import java.math.BigDecimal;

/**
 * 订单项类
 * @author c1rew
 * @create 2021-02-19 18:15
 */
public class OrderItemDO {

    /**
     * id
     */
    private long id;
    /**
     * 订单商品
     */
    private ProductDO productDO;
    /**
     * 商品数量
     */
    private int amount;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 订单id
     */
    private long orderInfoId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductDO getProductDO() {
        return productDO;
    }

    public void setProductDO(ProductDO productDO) {
        this.productDO = productDO;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(long orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", productDO=" + productDO +
                ", amount=" + amount +
                ", price=" + price +
                ", orderInfoId=" + orderInfoId +
                '}';
    }
}
