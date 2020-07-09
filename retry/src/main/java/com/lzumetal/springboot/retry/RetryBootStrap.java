package com.lzumetal.springboot.retry;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author liaosi
 * @date 2020-07-08
 */
@SpringBootApplication
@EnableRetry
public class RetryBootStrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RetryBootStrap.class)
                .registerShutdownHook(true)
                .build()
                .run(args);
    }

}
