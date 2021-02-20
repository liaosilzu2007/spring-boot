package com.lzumetal.springboot.interceptor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liaosi
 * @date 2021-02-20
 */
@SpringBootApplication
public class InterceptorBootStrap {


    public static void main(String[] args) {
        new SpringApplicationBuilder(InterceptorBootStrap.class)
                .registerShutdownHook(true)
                .build(args)
                .run();
    }


}
