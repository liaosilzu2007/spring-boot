package com.lzumetal.springboot.redis.test;

import com.lzumetal.springboot.redis.RedisMain;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liaosi
 * @date 2021-11-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisMain.class)
@Slf4j
public class KeyExpireTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void keyExpireTest() {

    }


    static class RedisSub extends JedisPubSub {
        @Override
        public void onMessage(String channel, String message) {
            System.out.println(System.currentTimeMillis()+"ms:"+message+"订单取消");
        }
    }
}
