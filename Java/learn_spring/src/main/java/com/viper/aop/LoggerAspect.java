package com.viper.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author c1rew
 * @date 2021-03-02 10:42
 */
@Aspect
@Component
public class LoggerAspect {
    @Before(value = "com.viper.dynamicproxy.impl.CalImpl.*(..)")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name);
    }
}
