package com.lzumetal.springboot.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * @author liaosi
 * @date 2021-03-29
 */
@Configuration
public class EsTemplateConfig {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        return new ElasticsearchRestTemplate(restHighLevelClient);
    }

}
