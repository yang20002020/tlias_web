package com.itheima.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override //初始化的方法，只会调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("初始化方法执行了");
    }

    @Override // 拦截到前端请求，可以调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo拦截到了请求，放行之前的逻辑....");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Demo拦截到了请求，放行之后的逻辑....");
    }

    @Override  //销毁的方法，只会调用一次
    public void destroy() {
        Filter.super.destroy();
        System.out.println("销毁的方法执行了");
    }
}
