package com.lzumetal.springboot.image;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author liaosi
 * @date 2020-08-09
 */
@SpringBootApplication
public class ImageBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ImageBootstrap.class)
                .registerShutdownHook(true)
                .build(args)
                .run();
    }

}
