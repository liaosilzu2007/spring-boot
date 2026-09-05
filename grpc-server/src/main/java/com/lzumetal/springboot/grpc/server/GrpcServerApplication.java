package com.lzumetal.springboot.grpc.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liaosi
 */
@SpringBootApplication
public class GrpcServerApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder(GrpcServerApplication.class)
                .run(args);
    }


}