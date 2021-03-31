package com.lzumetal.springboot.elasticsearch.service;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liaosi
 * @date 2021-03-29
 */
@Service
public class ClientAccountService {

    @Autowired
    private RestHighLevelClient client;



}
