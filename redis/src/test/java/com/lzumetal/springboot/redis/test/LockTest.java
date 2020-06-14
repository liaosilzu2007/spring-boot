package com.lzumetal.springboot.redis.test;

import com.lzumetal.springboot.redis.RedisMain;
import com.lzumetal.springboot.redis.lock.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-06-07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisMain.class)
@Slf4j
public class LockTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisLockUtil redisLockUtil;


    @Test
    public void testRedis() {
        ValueOperations<String, String> opt = redisTemplate.opsForValue();
        String value = opt.get("k1");
        System.out.println("redis value --------> " + value);
    }


    @Test
    public void testLock() throws InterruptedException {
        final String resource = "ORDER_PAYED";
        String requestId = UUID.randomUUID().toString();
        boolean lock = false;
        try {
            lock = redisLockUtil.tryLock(resource, requestId);
            if (!lock) {
                log.error("testLock|获取锁失败|end...");
                return;
            }
            //业务逻辑处理
            log.info("业务处理开始...");
            TimeUnit.MILLISECONDS.sleep(new Random(3000).nextLong());
            log.info("业务处理完成...");
        } finally {
            if (lock) {
                redisLockUtil.unlock(resource, requestId);
            }
        }

    }


}
