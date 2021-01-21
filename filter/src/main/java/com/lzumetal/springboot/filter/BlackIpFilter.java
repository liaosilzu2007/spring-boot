package com.lzumetal.springboot.filter;

import com.lzumetal.springboot.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author liaosi
 * @date 2021-01-21
 */
@Slf4j
@Component
public class BlackIpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String ipAddr = IPUtil.getIpAddr(request);
        String requestURI = request.getRequestURI();
        log.info("{}|访问ip|{}", requestURI, ipAddr);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
