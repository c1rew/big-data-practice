package com.viper.demo.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 商城用户类
 * @author c1rew
 * @create 2021-02-19 18:00
 */
public class CustomerDO {

    /**
     * 用户id
     */
    private long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址集合
     */
    private List<AddressDO> addressDOs = new ArrayList<>();

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AddressDO> getAddressDOs() {
        return addressDOs;
    }

    public void setAddressDOs(List<AddressDO> addressDOs) {
        this.addressDOs = addressDOs;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", addresses=" + addressDOs +
                '}';
    }
}
