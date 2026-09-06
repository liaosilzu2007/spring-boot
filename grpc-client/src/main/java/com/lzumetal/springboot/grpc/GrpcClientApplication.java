package com.lzumetal.springboot.grpc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GrpcClientApplication.class)
                .run(args);
    }

}
