package com.lzumetal.springboot.webservice.server;

import javax.xml.ws.Endpoint;

/**
 * 3.Endpoint发布服务
 * @author liaosi
 */
public class QueryWeatherServer {

    public static void main(String[] args) {
        /*
         * publish方法的2个参数：
         * address: 服务地址 --> 提供访问的地址
         * implementor: 服务类  --> 提供访问的接口的实现类
         */
        Endpoint.publish("http://localhost:9090/queryWeather", new QueryWeatherImpl());
        System.out.println("服务发布成功");
    }


}
