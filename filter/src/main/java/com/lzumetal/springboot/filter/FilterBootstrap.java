package com.lzumetal.springboot.filter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liaosi
 * @date 2021-01-21
 */
@SpringBootApplication
public class FilterBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FilterBootstrap.class)
                .registerShutdownHook(true)
                .build(args)
                .run();
    }

}
