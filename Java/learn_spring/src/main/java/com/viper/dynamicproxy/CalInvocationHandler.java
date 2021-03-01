package com.viper.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author c1rew
 * @create 2021-03-02 06:01
 */
public class CalInvocationHandler implements InvocationHandler {
    private Object object = null;

    public Object getProxy(Object object) {
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this::invoke);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object, args);
        System.out.println(method.getName() + ": " + Arrays.toString(args) +"="+result);
        return result;
    }
}
