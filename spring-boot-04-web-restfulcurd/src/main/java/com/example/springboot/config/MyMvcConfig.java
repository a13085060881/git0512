package com.example.springboot.config;


import com.example.springboot.component.LoginHandlerInterceptor;
import com.example.springboot.component.MyLocaleResolver;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 使用WebMvcConfigurationSupport可以来扩展SpringMVC的功能
 * WebMvcConfigurerAdapter高版本已经弃用了
 * @EnableWebMvc 用于全面接管SpringMVC
 * WebMvcConfigurerAdapter
 */
//@EnableWebMvc
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {





    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    //添加这个的时候，方法名称要是localeResolver，如果其他的@Bean("localeResolver"),否则不生效
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/example").setViewName("success");
    }

    //所有的webMvcConfigurationSupport组件都会一起起作用
    @Bean //将组件注册在容器中
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        WebMvcConfigurer adapter = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                //添加了视图映射，访问index.html直接的映射到login.html页面
                registry.addViewController("/index.html").setViewName("login");
                //给main.html添加视图映射，防止表单重复提交，
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            //  "/**"表示任意路径下的任意请求
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源: css、js
                //super.addInterceptors(registry);
                //SprintBoot已经做好了静态资源的映射
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        //排除掉/index.html、/、/user/login的登录请求
                        .excludePathPatterns("/curd","/index.html","/","/user/login");
            }
        };
        return adapter;
    }
}