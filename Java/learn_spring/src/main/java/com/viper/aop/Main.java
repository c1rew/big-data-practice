package com.viper.aop;

import com.viper.dynamicproxy.Cal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author c1rew
 * @create 2021-03-02 19:32
 */
@ComponentScan("com.viper")
@Configuration
@EnableAspectJAutoProxy
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        Cal proxy = (Cal) context.getBean("calImpl");
        proxy.add(1, 1);
        proxy.sub(3, 1);
        proxy.mul(3, 3);
        proxy.div(8, 2);
    }
}
