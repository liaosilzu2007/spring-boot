package com.lzumetal.javaweb.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author liaosi
 * @date 2021-02-03
 */
public class FilterTest02 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterTest02 执行前---");
        filterChain.doFilter(request, response);  //放行
        System.out.println("FilterTest02 执行后---");
    }

    @Override
    public void destroy() {

    }
}
