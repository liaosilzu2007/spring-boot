package com.ddcx.springboot.helloworld;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liaosi on 2017/9/25.
 */
@RestController
@EnableAutoConfiguration
public class HelloController {


    @RequestMapping("/home")
    String home() {
        return "Hello World!";
    }


    @RequestMapping("/hello/{name}")
    String hello(@PathVariable("name") String name) {
        return "hello " + name + "!!!";
    }

}
