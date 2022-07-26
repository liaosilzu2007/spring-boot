package com.lzumetal.springboot.webservice.client;


import com.lzumetal.springboot.webservice.server.QueryWeatherImpl;
import com.lzumetal.springboot.webservice.server.QueryWeatherImplService;


/**
 * @author liaosi
 */
public class QueryWeatherClient {


    public static void main(String[] args) {
        QueryWeatherImplService queryWeatherImplService = new QueryWeatherImplService();
        QueryWeatherImpl queryWeatherImpl = queryWeatherImplService.getQueryWeatherImplPort();
        System.out.println("查询当前温度为：" + queryWeatherImpl.queryNowTemperature());
    }


}
