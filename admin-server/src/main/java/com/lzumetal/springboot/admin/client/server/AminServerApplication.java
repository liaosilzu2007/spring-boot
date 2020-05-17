package com.lzumetal.springboot.admin.client.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liaosi
 * @date 2020-05-16
 */
@SpringBootApplication
@EnableAdminServer
public class AminServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AminServerApplication.class)
                .run(args);
    }

}
