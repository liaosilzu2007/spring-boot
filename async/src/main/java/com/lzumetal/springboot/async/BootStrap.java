package com.lzumetal.springboot.async;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author liaosi
 * @date 2020-05-01
 */
@SpringBootApplication
@EnableAsync
public class BootStrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder().run(args);
    }
}
