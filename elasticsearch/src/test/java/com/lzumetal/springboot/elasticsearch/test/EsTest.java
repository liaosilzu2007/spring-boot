package com.lzumetal.springboot.elasticsearch.test;

import com.lzumetal.springboot.elasticsearch.EsBootstrap;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liaosi
 * @date 2021-03-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EsBootstrap.class})
@Slf4j
public class EsTest {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


}
