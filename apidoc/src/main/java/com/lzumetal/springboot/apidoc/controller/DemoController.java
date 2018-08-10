package com.lzumetal.springboot.apidoc.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzumetal.springboot.apidoc.entity.Good;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("JavaDoc")
public class DemoController {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();


    /**
     * @api {GET} /testApidoc 测试testApidoc的使用
     * @apiVersion 1.0.0
     * @apiGroup shop
     * @apiName apidoc测试
     *
     * @apiParam  {String} name  商品的名字
     * @apiParam  {number} id  商品的id
     * @apiParam  {number} price  商品的price
     *
     *
     *
     * @return
     */
    @RequestMapping(value = "/testApidoc", method = RequestMethod.GET)
    public String testApidoc(Good good) {
        return gson.toJson(good);
    }




}
