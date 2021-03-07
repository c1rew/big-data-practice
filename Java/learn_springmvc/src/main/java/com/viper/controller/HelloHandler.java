package com.viper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author c1rew
 * @create 2021-03-07 16:56
 */
@Controller
@RequestMapping("/hello")
public class HelloHandler {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        System.out.println("index .........");
        return "index";
    }
}
