package com.example.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 创建自定义的区域视图解析器,需要实现LocaleResolver接口
 * 同时需要将当前的local添加到容器中，添加在SpringMVC的扩展文件中
 */
public class MyLocaleResolver implements LocaleResolver{
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //从请求中取到local
        String local = httpServletRequest.getParameter("local");

        //默认的取系统自带的
        Locale locale = Locale.getDefault();

        //如果定义了，
        if(!StringUtils.isEmpty(local)){
            //截取local
            String[] localArray =local.split("_");
            //使用Locale传递两个参数的构造方法，第一个参数是语言。第二个参数是国家，可以查看这个Locale源码
            locale = new Locale(localArray[0],localArray[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }

    /*@Override
    public Locale resolveLocale(HttpServletRequest request) {
        //从请求中取到local
        String local = request.getParameter("local");
        System.out.println("=======local:"+local);
        //默认的取系统自带的
        Locale locale = Locale.getDefault();

        //如果定义了，
        if(!StringUtils.isEmpty(local)){
            //截取local
            String[] localArray =local.split("_");
            //使用Locale传递两个参数的构造方法，第一个参数是语言。第二个参数是国家，可以查看这个Locale源码
            locale = new Locale(localArray[0],localArray[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }*/
}