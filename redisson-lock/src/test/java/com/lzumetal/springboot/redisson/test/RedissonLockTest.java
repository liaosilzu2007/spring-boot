package com.lzumetal.springboot.redisson.test;

import com.lzumetal.springboot.redisson.RedissonBootstrap;
import com.lzumetal.springboot.redisson.service.OrderService;
import com.lzumetal.springboot.redisson.thread.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-08-06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedissonBootstrap.class)
@Slf4j
public class RedissonLockTest {


    @Autowired
    private OrderService orderService;


    @Test
    public void refundTest() {
        final long orderId = 12345;
        int threadCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            ThreadPoolUtil.cachedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        orderService.refund(orderId);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /*
     * 直接注入RedissonClient就可以直接使用.
     */
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void lockTest() throws InterruptedException {
        String key = "anyLock";
        RLock lock = redissonClient.getLock(key);
        lock.lock();
        lock.lock();
        lock.lock();
        TimeUnit.SECONDS.sleep(10L);
        lock.unlock();
        lock.unlock();
        lock.unlock();
    }

    @Test
    public void fairLockTest() throws InterruptedException {
        String key = "anyLock";
        RLock lock = redissonClient.getFairLock(key);
        lock.lock();
        TimeUnit.SECONDS.sleep(5L);
        lock.unlock();
    }
}
