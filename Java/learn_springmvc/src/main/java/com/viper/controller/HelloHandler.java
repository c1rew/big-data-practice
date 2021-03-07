package com.viper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author c1rew
 * @create 2021-03-07 16:56
 */
@Controller
@RequestMapping("/hello")
public class HelloHandler {

    /**
     * 传统web请求
     * @param str
     * @param age
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET, params = {"name","id"})
    public String index(@RequestParam("name") String str, @RequestParam("id") int age) {
        System.out.println(str);
        System.out.println(age);
        System.out.println("index .........");
        return "index";
    }

    /**
     * RESTful风格请求
     * @param str
     * @param age
     * @return
     */
    @RequestMapping("rest/{name}/{id}")
    public String rest(@PathVariable("name") String str, @PathVariable("id") int age) {
        System.out.println(str);
        System.out.println(age);
        System.out.println("rest.........");
        return "index";
    }
}
