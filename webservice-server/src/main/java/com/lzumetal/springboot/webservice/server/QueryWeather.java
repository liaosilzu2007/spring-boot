package com.lzumetal.springboot.webservice.server;

/**
 * 1.编写SEI(Service Endpoint Interface)，
 * SEI在webservice中称为portType，在java中称为接口。
 * <p>
 * <p>
 * 气温查询接口
 */
public interface QueryWeather {

    /**
     * 查询当前的气温
     */
    String queryNowTemperature();


}
