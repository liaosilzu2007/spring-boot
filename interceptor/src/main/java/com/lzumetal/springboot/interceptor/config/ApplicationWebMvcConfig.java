package com.lzumetal.springboot.interceptor.config;

import com.lzumetal.springboot.interceptor.LoginInterceptor;
import com.lzumetal.springboot.interceptor.ReqThreadLocalInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liaosi
 * @date 2021-02-20
 */
@Configuration
public class ApplicationWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ReqThreadLocalInterceptor reqThreadLocalInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").order(2);
        registry.addInterceptor(reqThreadLocalInterceptor).addPathPatterns("/**").order(1);
    }

}
