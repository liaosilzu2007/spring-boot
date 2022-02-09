package com.lzumetal.springboot.redis.test;

import com.lzumetal.springboot.redis.RedisMain;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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


    /**
     * 将有过期时间的key转为永久有效
     */
    @Test
    public void persist() {
        String key = "test_key";
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
        ops.persist();
        log.info("{} will expire after {} seconds", key, ops.getExpire());
    }


    @Test
    public void expire() {
        String key = "test_key";
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
        ops.expire(10, TimeUnit.MINUTES);
        log.info("{} will expire after {} seconds", key, ops.getExpire());
    }


    @Test
    public void valueSet() {
        String key = "test_key";
//        redisTemplate.delete(key);
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
        ops.set("Y");
    }

    @Test
    public void valueGet() {
        String key = "test_key";
        String value = redisTemplate.boundValueOps(key).get();
        System.err.println(value);
        System.err.println(redisTemplate.boundValueOps(key).getExpire());
    }


    @Test
    public void del() {
        String key = "test_key";
        System.out.println(redisTemplate.delete(key));
    }


    @Test
    public void incr() {
        String key = "test_key";
        Long execute = redisTemplate.execute((RedisCallback<Long>) con -> con.incrBy(key.getBytes(), 1L));
        System.out.println(execute);
    }


    @Test
    public void decr() {
        String key = "test_key";
        //BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
        Long execute = redisTemplate.execute((RedisCallback<Long>) con -> con.decrBy(key.getBytes(), 1L));
        System.out.println(execute);
    }


    @Test
    public void lPush() {
        String key = "test_list";
        Long result = redisTemplate.boundListOps(key).leftPush("10001");
        System.out.println(result);
    }

    @Test
    public void lPushAndPopTest() {
        String key = "test_list_ttl";
        Long result = redisTemplate.boundListOps(key).leftPush("l1");
        System.out.println(redisTemplate.boundListOps(key).getExpire());
        System.out.println(result);
    }


    @Test
    public void lList() {
        String key = "test_list";
        List<String> list = redisTemplate.boundListOps(key).range(0, -1);
        System.out.println(list);
        System.out.println(redisTemplate.boundListOps(key).getExpire());
    }


    @Test
    public void zAdd() {
        String key = "test_set";
        BoundZSetOperations<String, String> ops = redisTemplate.boundZSetOps(key);
        long score = System.currentTimeMillis() + 15 * 60 * 1000;
        ops.add("1234565", score);
    }

    @Test
    public void zSetAdd() {
        String key = "test_set";
        BoundZSetOperations<String, String> ops = redisTemplate.boundZSetOps(key);
        long currentTimeMillis = System.currentTimeMillis();
        ops.add(String.valueOf(currentTimeMillis), currentTimeMillis);
        ops.expire(1, TimeUnit.HOURS);
    }


    @Test
    public void zSetRange() {
        String key = "test_set";
        BoundZSetOperations<String, String> ops = redisTemplate.boundZSetOps(key);
        long end = System.currentTimeMillis() + 60 * 60 * 1000;
        System.out.println(end);
        Set<String> strings = ops.rangeByScore(0, end);
        System.out.println(strings);
        System.out.println(ops.getExpire());
    }


    @Test
    public void renameKey() {
        redisTemplate.rename("name", "name_v2");
    }


    @Test
    public void hashGet() {
        String key = "test_hash";
        BoundHashOperations<String, Integer, String> ops = redisTemplate.boundHashOps(key);
        String value = ops.get("25");
        System.out.println(value);
        Long result = ops.delete("25");
        System.out.println(result);
    }


    @Test
    public void hashPut() {
        String key = "test_hash";
        BoundHashOperations<String, String, String> ops = redisTemplate.boundHashOps(key);
        ops.put("123", "123");
        ops.expire(1, TimeUnit.DAYS);
    }


    @Test
    public void hashDelete() {
        String key = "test_hash";
        BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(key);
        Long delete = ops.delete("123");
        System.out.println(delete);
    }



}
