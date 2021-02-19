package com.lzumetal.springboot.filter.config;

import com.lzumetal.springboot.filter.BlackIpFilter;
import com.lzumetal.springboot.filter.RoleFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaosi
 * @date 2021-02-19
 */
@Configuration
public class FilterConfiguration {


    @Bean
    public FilterRegistrationBean blackIpFilter() {
        FilterRegistrationBean<BlackIpFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        BlackIpFilter blackIpFilter = new BlackIpFilter();
        filterRegistrationBean.setFilter(blackIpFilter);
        filterRegistrationBean.addUrlPatterns("/*");//配置过滤规则
        filterRegistrationBean.addInitParameter("name","hahaha");//设置init参数
        filterRegistrationBean.setName("blackIpFilter");//设置过滤器名称
        filterRegistrationBean.setOrder(2);//执行次序
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean roleFilter() {
        FilterRegistrationBean<RoleFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        RoleFilter roleFilter = new RoleFilter();
        filterRegistrationBean.setFilter(roleFilter);
        filterRegistrationBean.addUrlPatterns("/*");//配置过滤规则
        filterRegistrationBean.addInitParameter("key","value001");//设置init参数
        filterRegistrationBean.setName("roleFilter");//设置过滤器名称
        filterRegistrationBean.setOrder(1);//执行次序
        return filterRegistrationBean;
    }

}
