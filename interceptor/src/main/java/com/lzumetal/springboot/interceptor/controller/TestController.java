package com.lzumetal.springboot.interceptor.controller;

import com.lzumetal.springboot.utils.response.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaosi
 * @date 2021-02-20
 */
@RestController
public class TestController {


    @RequestMapping(value = "/testInterceptor", method = {RequestMethod.GET, RequestMethod.GET})
    public ResponseData testFilter() {
        throw new RuntimeException();
        //return ResponseData.success();
    }

}

