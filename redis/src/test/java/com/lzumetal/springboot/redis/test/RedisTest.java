package com.lzumetal.springboot.redis.test;

import com.lzumetal.springboot.redis.RedisMain;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liaosi
 * @date 2020-06-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisMain.class)
@Slf4j
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void get() {
        String key = "19335244";
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
        System.err.println(ops.get());
        System.err.println(ops.getExpire());
    }

    @Test
    public void del() {
        String key = "19335244";
        System.out.println( redisTemplate.delete(key));
    }
}
