package com.viper.dynamicproxy;

import com.viper.dynamicproxy.impl.CalImpl;

/**
 * @author c1rew
 * @create 2021-03-02 06:05
 */
public class Main {
    public static void main(String[] args) {
        Cal cal = new CalImpl();
        CalInvocationHandler calInvocationHandler = new CalInvocationHandler();
        Cal proxy = (Cal)calInvocationHandler.getProxy(cal);
        proxy.add(1,1);
        proxy.sub(3,1);
        proxy.mul(3,3);
        proxy.div(8,2);
    }
}
