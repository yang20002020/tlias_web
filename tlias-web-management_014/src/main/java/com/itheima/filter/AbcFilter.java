package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(urlPatterns = "/*")
public class AbcFilter implements Filter {


    @Override // 拦截到前端请求，可以调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Abc拦截到了请求，放行之前的逻辑....");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Abc拦截到了请求，放行之后的逻辑....");
    }


}
