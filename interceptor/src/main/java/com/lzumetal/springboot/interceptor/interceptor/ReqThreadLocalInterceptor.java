package com.lzumetal.springboot.interceptor.interceptor;

import com.lzumetal.springboot.utils.common.ApplicationContext;
import com.lzumetal.springboot.utils.common.ThreadContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置请求的线程上下文
 */
@Slf4j
@Component
public class ReqThreadLocalInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ThreadContext.setContext(new ApplicationContext());
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("do nothing");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ApplicationContext context = ThreadContext.getContext();
        if (context != null) {
            ThreadContext.removeContext();
        }
    }
}
