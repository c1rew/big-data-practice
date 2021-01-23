package com.viper.springboot.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author c1rew
 * @create 2021-01-23 18:30
 */
@RestController
public class HelloController {
    @RequestMapping("/sayhello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return "hello " + name + ", this is spring boot demo";
    }
}
