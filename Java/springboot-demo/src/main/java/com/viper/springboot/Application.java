package com.viper.springboot;

import com.viper.springboot.config.DroidDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


/**
 * @author c1rew
 * @create 2021-01-23 10:11
 */

/**
 * @SpringBootApplication 注解等于一下三个注解组合
 * @Configuration + @EnableAutoConfiguration + @ComponentScan
 */
@SpringBootApplication
@Import(DroidDataSourceConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
