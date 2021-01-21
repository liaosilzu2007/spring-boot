package com.lzumetal.springboot.filter.controller;

import com.lzumetal.springboot.utils.response.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaosi
 * @date 2021-01-21
 */
@RestController
public class TestController {


    @RequestMapping(value = "/testFilter", method = {RequestMethod.GET, RequestMethod.GET})
    public ResponseData testFilter() {
        return ResponseData.success();
    }

}
