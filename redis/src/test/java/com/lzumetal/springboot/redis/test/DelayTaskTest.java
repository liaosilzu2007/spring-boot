package com.lzumetal.springboot.redis.test;

import com.lzumetal.springboot.redis.RedisMain;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2021-11-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisMain.class)
@Slf4j
public class DelayTaskTest {


    @Autowired
    private StringRedisTemplate redisTemplate;


    @Test
    public void orderDelayTaskTest() throws InterruptedException {
        final String key = "test_dalay_order_id";
        //启动一个消费线程，从redis拉取订单
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long currentMillis = System.currentTimeMillis();
                    BoundZSetOperations<String, String> ops = redisTemplate.boundZSetOps(key);
                    Set<String> list = ops.rangeByScore(0, currentMillis);
                    if (!CollectionUtils.isEmpty(list)) {
                        for (String id : list) {
                            Long remove = ops.remove(id);
                            //remove大于0表示移除成功
                            if (remove != null && remove > 0) {
                                log.info("处理超时的订单|订单id={}|maxScore={}", id, currentMillis);
                            }
                        }
                    } else {
                        log.info("当前没有需要处理的超时订单|maxScore={}", currentMillis);
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //生成5个订单保存到redis
        BoundZSetOperations<String, String> ops = redisTemplate.boundZSetOps(key);
        for (int i = 1; i <= 5; i++) {
            long score = System.currentTimeMillis() + 3 * 1000;
            ops.add(String.valueOf(i), score);
            log.info("订单保存至redis的zset中|id={}|score={}", i, score);
            TimeUnit.SECONDS.sleep(new Random().nextInt(3));
        }

        //等待消费线程消费完毕
        TimeUnit.SECONDS.sleep(12L);
    }

}
