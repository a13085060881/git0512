package com.example.springboot.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 实现自定义的多滤器
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行自定义的filter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
