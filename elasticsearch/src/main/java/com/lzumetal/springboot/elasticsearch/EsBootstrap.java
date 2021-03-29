package com.lzumetal.springboot.elasticsearch;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liaosi
 * @date 2021-03-24
 */
@SpringBootApplication
public class EsBootstrap {


    public static void main(String[] args) {
        new SpringApplicationBuilder(EsBootstrap.class)
                .registerShutdownHook(true)
                .build(args)
                .run();
    }

}
