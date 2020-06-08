package com.lzumetal.springboot.redis.lock;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liaosi
 * @date 2020-04-25
 */
@SpringBootApplication
public class RedisLockMain {

    public static void main(String[] args) {
        SpringApplication.run(RedisLockMain.class, args);
    }

}
