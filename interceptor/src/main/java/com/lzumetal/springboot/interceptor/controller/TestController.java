package com.lzumetal.springboot.interceptor.controller;

import com.lzumetal.springboot.interceptor.annotation.CurrentUser;
import com.lzumetal.springboot.interceptor.annotation.NotRequireLogin;
import com.lzumetal.springboot.interceptor.entity.LoginUser;
import com.lzumetal.springboot.utils.response.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaosi
 * @date 2021-02-20
 */
@RestController
@RequestMapping(method = {RequestMethod.GET, RequestMethod.GET})
public class TestController {


    @RequestMapping(value = "/testInterceptor")
    @NotRequireLogin
    public ResponseData testFilter() {
        throw new RuntimeException();
        //return ResponseData.success();
    }


    @RequestMapping(value = "/testToken")
    public ResponseData testToken(@CurrentUser LoginUser loginUser) {
        return ResponseData.data(loginUser);
    }

}

