package com.lzumetal.springboot.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 */
@Slf4j
public class GuavaCacheUtil {


    private final static Cache<Integer, String> cache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(10)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
            //设置cache中的数据在写入之后的存活时间为10秒
            .expireAfterWrite(10, TimeUnit.SECONDS)
            //构建cache实例
            .build();


    public static void main(String[] args) throws Exception {
        new Thread(() -> cache.put(1, "Hello")).start();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cache.put(2, "world");
        }).start();


        for (int i = 0; i < 100; i++) {
            log.info("get from cache|key:1,value:{}", cache.getIfPresent(1));
            log.info("get from cache|key:2,value:{}", cache.getIfPresent(2));
            Thread.sleep(1000);
        }

    }

}
