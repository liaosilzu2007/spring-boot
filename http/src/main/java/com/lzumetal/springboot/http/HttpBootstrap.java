package com.lzumetal.springboot.http;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liaosi
 * @date 2020-08-08
 */
@SpringBootApplication
public class HttpBootstrap {


    public static void main(String[] args) {
        new SpringApplicationBuilder(HttpBootstrap.class)
                .registerShutdownHook(true)
                .build(args)
                .run();
    }

}
