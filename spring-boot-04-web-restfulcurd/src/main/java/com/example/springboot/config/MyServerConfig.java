package com.example.springboot.config;

import com.example.springboot.filter.MyFilter;
import com.example.springboot.listener.MyListener;
import com.example.springboot.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 与服务器相关配置的操作
 * @Configuration 表明是一个配置类
 */
@Configuration
public class MyServerConfig {
    /**
     * 注册三大组件
     * @Bean 使用@Bean标签将其加入到容器中
     * @return
     */
    @Bean
    public ServletRegistrationBean myServlet(){
        /**
         * 调用带有两个参数的构造器
         * 第一个参数：指定你自定义的Servlet容器
         * 第二个参数：映射指定的路径，就是要拦截的请求
         */
        ServletRegistrationBean registrationBean=
                new ServletRegistrationBean(new MyServlet(),"/myServlet");
        //设置启动的顺序
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    /**
     * 注册自定义的过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        //设置自定义的filter
        filter.setFilter(new MyFilter());
        //设置要过滤的请求
        filter.setUrlPatterns(Arrays.asList("/hell0","/myServlet"));

        return filter;
    }

    /**
     * 注册监听器
     * @Bean 将监听器添加到容器中
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> listener =
                new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return listener;
    }
}
