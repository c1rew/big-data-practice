package com.viper.dynamicproxy.impl;

import com.viper.dynamicproxy.Cal;

/**
 * @author c1rew
 * @create 2021-03-02 06:00
 */
public class CalImpl implements Cal {
    @Override
    public Integer add(Integer num1, Integer num2) {
        return num1 + num2;
    }

    @Override
    public Integer sub(Integer num1, Integer num2) {
        return num1 - num2;
    }

    @Override
    public Integer mul(Integer num1, Integer num2) {
        return num1 * num2;
    }

    @Override
    public Integer div(Integer num1, Integer num2) {
        return num1 / num2;
    }
}
