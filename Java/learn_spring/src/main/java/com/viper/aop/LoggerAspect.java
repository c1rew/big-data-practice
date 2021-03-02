package com.viper.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author c1rew
 * @date 2021-03-02 10:42
 */
@Aspect
@Component
public class LoggerAspect {
    @Before("execution(public Integer com.viper.dynamicproxy.impl.CalImpl.*(..))")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(name + " " + args);
    }


}
