package com.lzumetal.springboot.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-05-03
 */
@Slf4j
@Service
public class AsyncService {

    @Async(value = "myexecutor")
    public void saveToCache() throws InterruptedException {
        log.info("将数据存入缓存|start...");
        TimeUnit.SECONDS.sleep(2L);
        log.info("将数据存入缓存|end...");
    }

}
