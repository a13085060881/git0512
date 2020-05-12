package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    //@GetMapping
    //@PutMapping
    //@DeleteMapping
    //SpringBoot高版本提供了@PostMapping方式替换@RequestMapping，可以查看底层的代码一致的
    @PostMapping("/user/login")
    //指定映射接口，并且指定Post的提交方式
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    //使用@RequestParam("username")明确的指定从request请求中获取到username
    //用@RequestParam标注的参数一但没有提交就会直接的报错
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String password, Map<String,String> map, HttpSession session){
        if(!StringUtils.isEmpty(userName) && "123456".equals(password)){
            //使用session存储用户信息，防止用户复制地址链接登录系统
            session.setAttribute("username",userName);

            //登陆成功操作,防止表单重复提交，可以重定向到主页，
            // 在登陆成功之后重定向到mian.html页面，但是main.html添加了视图映射到dashboard.html页面了
            //return "redirect:main.html"; 注意这种形式，否则访问的静态资源的路径前会拼接上/user
            return "redirect:/main.html";
            //return "dashboard";
        }else{
            //登陆失败，跳到登陆页，同时返回一个错误提示信息
            map.put("msg","用户名或密码错误！");
            return "login";
        }
    }
}