package com.lzumetal.springboot.admin.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liaosi
 * @date 2020-05-16
 */
@SpringBootApplication
public class AdminClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminClientApplication.class)
                .run(args);
    }
}
