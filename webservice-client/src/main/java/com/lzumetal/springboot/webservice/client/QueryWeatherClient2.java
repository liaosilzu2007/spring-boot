package com.lzumetal.springboot.webservice.client;

import com.lzumetal.springboot.webservice.server.QueryWeatherImpl;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * @author liaosi
 */
public class QueryWeatherClient2 {

    private QueryWeatherImpl queryWeatherImpl;

    public static void main(String[] args) throws Exception {

        // 1. 设置访问的服务端地址
        URL url = new URL("http://localhost:9090/queryWeather?wsdl");

        /*
        2.设置服务名称和命名空间
         namespaceURI: wsdl的命名空间（targetNamespace），其实就是服务提供者的包名翻转后的字符串
         localPart: 是服务视图的名称(service的name值)
        */
        QName qName = new QName("http://server.webservice.springboot.lzumetal.com/", "QueryWeatherImplService");

        // 3. 生成服务视图
        Service service = Service.create(url, qName);

        // 4. 得到服务视图的实现类 --> WeatherServiceImpl
        QueryWeatherImpl weatherServiceImpl = service.getPort(QueryWeatherImpl.class);

        // 5. 调用接口方法得到结果
        String temperature = weatherServiceImpl.queryNowTemperature();
        System.out.println("查询当前温度为：" + temperature);
    }


}
