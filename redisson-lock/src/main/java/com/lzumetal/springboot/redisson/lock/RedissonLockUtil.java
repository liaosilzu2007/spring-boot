package com.lzumetal.springboot.redisson.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-08-06
 */
@Component
@Slf4j
public class RedissonLockUtil {

    /*
     * 直接注入RedissonClient就可以直接使用.
     */
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取锁
     *
     * @param key
     * @param waitSeconds 等待获取锁的阻塞时间，单位：秒
     * @return
     */
    public boolean tryLock(String key, long waitSeconds) {
        RLock lock = redissonClient.getLock(key);
        try {
            //60s后强制释放锁
            boolean result = lock.tryLock(waitSeconds, 60L, TimeUnit.SECONDS);
            log.info("lock|END|key={},waitSeconds={}s|{}", key, waitSeconds, result);
            return result;
        } catch (InterruptedException e) {
            log.error("lock|ERROR|key={},waitSeconds={}s", key, waitSeconds);
            return false;
        }
    }


    /**
     * 释放锁
     *
     * @param key
     */
    public void unlock(String key) {
        redissonClient.getLock(key).unlock();
        log.info("unlock|END|key={}", key);
    }
}
