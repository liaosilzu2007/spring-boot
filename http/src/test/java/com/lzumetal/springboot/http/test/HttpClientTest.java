package com.lzumetal.springboot.http.test;

import com.lzumetal.springboot.http.HttpBootstrap;
import com.lzumetal.springboot.utils.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liaosi on 2017/7/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HttpBootstrap.class})
@Slf4j
public class HttpClientTest {

    private static final String TEST_URL = "http://localhost:8081/httpclient/testHttpRequest";


    @Test
    public void testGet() throws IOException {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Referer", "http://www.baidu.com");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headerMap.put("Cookie", "token=EA595A8958B81E7B113F9AEE785453AF;loginSession=1;loginType=BUYER");

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("province", "广东省");
        paramMap.put("city", "深圳市");
        String resp = new HttpRequest().addHeads(headerMap).addParams(paramMap).get(TEST_URL);
        log.info("testGet|resp={}", resp);


    }

    @Test
    public void setCookie() {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            //设置cookie
            BasicCookieStore cookieStore = new BasicCookieStore();
            BasicClientCookie cookie = new BasicClientCookie("token1", "ab37gbfv65eeq19o54cd986lefg");
            cookie.setVersion(0);
            cookie.setPath("/");
            cookie.setDomain("localhost");
            cookieStore.addCookie(cookie);

            //HttpClients.custom()方法创建一个builder对象用于CloseableHttpClient对象的创建
            httpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore) //在客户端添加多个cookie
                    .build();


            //get请求设置参数，可以直接将参数键值对进行url编码后加载请求路径后即可
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("province", "广东省"));
            nameValuePairs.add(new BasicNameValuePair("city", "深圳市"));

            String reqUrl = TEST_URL + "?" + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, Charset.forName("utf-8")));
            System.out.println("请求的URL:" + reqUrl);

            HttpGet httpGet = new HttpGet(reqUrl);
            response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();

            System.out.println(new BasicResponseHandler().handleEntity(entity));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
