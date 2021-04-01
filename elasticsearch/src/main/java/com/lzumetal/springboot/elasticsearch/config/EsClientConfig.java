package com.lzumetal.springboot.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaosi
 * @date 2021-03-24
 */
@Configuration
public class EsClientConfig {

    @Value("${elasticsearch.cluster-http-hosts}")
    private String httpHosts;


    @Bean // 高版本客户端
    public RestHighLevelClient restHighLevelClient() {
        // 解析 hostlist 配置信息
        String[] split = httpHosts.split(",");
        HttpHost[] httpHostArray = new HttpHost[split.length];
        for (int i = 0; i < split.length; i++) {
            String item = split[i];
            httpHostArray[i] = new HttpHost(item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
        }
        // 创建RestHighLevelClient客户端
        return new RestHighLevelClient(RestClient.builder(httpHostArray).setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(2000);
                requestConfigBuilder.setSocketTimeout(8000);
                requestConfigBuilder.setConnectionRequestTimeout(5000);
                return requestConfigBuilder;

            }
        }));
    }



}
