package com.example.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "say hello!";
    }

    //简单的返回到success的页面
        /*@RequestMapping("/success")
        public String success(){
            //返回到的页面是：classpath:/templates/success.html
            return "success";
        }*/

    //查找一些数据在页面上显示
    @RequestMapping("/success")
    public String success(Map<String,String> map){
        map.put("hello","您好");
        return  "success";
    }

    @RequestMapping("/curd")
    public String login(){
        System.out.println("===========login=======");
        return "login";
    }
}