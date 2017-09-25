package com.ddcx.springboot.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * Created by liaosi on 2017/9/25.
 */
@SpringBootConfiguration
public class Aplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloController.class, args);
    }
}
