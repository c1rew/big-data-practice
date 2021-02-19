package com.viper.demo.domain;

import java.math.BigDecimal;

/**
 * 商品描述类
 * @author c1rew
 * @create 2021-02-19 18:06
 */
public class ProductDO {

    /**
     * 商品id
     */
    private long id;
    /**
     * 商品名字
     */
    private String name;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 商品价格
     */
    private BigDecimal price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
