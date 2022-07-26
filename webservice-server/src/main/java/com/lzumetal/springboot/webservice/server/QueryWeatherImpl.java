package com.lzumetal.springboot.webservice.server;


import javax.jws.WebService;

/**
 * 2.编写SEI实现类，此类作为webservice提供服务的类，实现类要加上@WebService注解。
 * 注意：SEI实现类中至少要有一个非静态的公开方法作为webservice的服务方法。
 * <p>
 * <p>
 * 气温查询实现类
 */
@WebService
public class QueryWeatherImpl implements QueryWeather {

    @Override
    public String queryNowTemperature() {
        return "28度";
    }

}
