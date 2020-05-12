package com.example.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查拦截器，实现HandlerInterceptor接口，需要重写方法
 * 解决问题：防止用户直接获取到"http://localhost:8080/user/main.html"地址链接，在浏览器中访问
 * 通过在用户登录成功，在session中放置当前登录用户的基本信息，在拦截器中取到对应的session用户信息进行检查
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

        //目标方法执行之前
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            //从session中取用户信息
            Object userName = request.getSession().getAttribute("username");
            if(userName == null){
                //未登录
                request.setAttribute("msg","没有权限，请重新登录！");
                //并且返回到登录页，转发请求，SpringMVC扩展的配置文件中添加了视图映射到login.html
                request.getRequestDispatcher("/index.html").forward(request,response);
                //拦截请求
                return false;
            }else{
                //已经登录，放行请求
                return true;
            }
        }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
