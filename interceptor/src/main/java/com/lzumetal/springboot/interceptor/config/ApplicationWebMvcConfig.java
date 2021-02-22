package com.lzumetal.springboot.interceptor.config;

import com.lzumetal.springboot.interceptor.interceptor.LoginInterceptor;
import com.lzumetal.springboot.interceptor.interceptor.ReqThreadLocalInterceptor;
import com.lzumetal.springboot.interceptor.resolver.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").order(2);
        registry.addInterceptor(reqThreadLocalInterceptor).addPathPatterns("/**").order(1);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserMethodArgumentResolver);
    }
}
