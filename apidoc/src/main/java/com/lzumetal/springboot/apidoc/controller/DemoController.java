package com.lzumetal.springboot.apidoc.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzumetal.springboot.apidoc.common.ResponseData;
import com.lzumetal.springboot.apidoc.entity.Good;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("JavaDoc")
public class DemoController {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();


    /**
     *
     * @api {POST} /testApidoc 测试testApidoc的使用
     * @apiVersion 1.0.0
     * @apiGroup shop
     * @apiName apidoc测试
     * @apiParam {String} name  商品的名字
     * @apiParam {number} id  商品的id
     * @apiParam {number} [price]  商品的price
     *
     * @apiSuccess {boolean} success 请求结果是否成功
     * @apiSuccess {string} code 响应码
     * @apiSuccess {string} message 响应信息
     * @apiSuccess {object} data 响应数据对象
     * @apiSuccess {string} data.name 商品名称
     * @apiSuccess {object} data.price 商品价格
     * @apiSuccessExample 响应示例
     *
     * {
     *  "success": true,
     *  "code": "200",
     *  "message": "成功",
     *  "data": {
     *      "id": 101,
     *      "name": "iphone",
     *      "price": 4599.0
     *  }
     * }
     *
     *  安装：npm install apidoc -g
     *  验证是否安装成功：apidoc -h
     *  生成文档：apidoc -i ./ -o ./src/main/webapp/WEB-INF/doc
     */
    @RequestMapping(value = "/testApidoc", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData testApidoc(Good good) {
        return new ResponseData(good);
    }


}
