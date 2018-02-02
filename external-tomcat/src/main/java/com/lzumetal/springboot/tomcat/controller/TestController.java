package com.lzumetal.springboot.tomcat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-02-02
 */
@RestController
public class TestController {


    @RequestMapping("/testExternalTomcat")
    public String hello() {
        return "hello tomcat";
    }
}
