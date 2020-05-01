package com.lzumetal.springboot.redis;

import redis.clients.jedis.Jedis;

/**
 * @author liaosi
 * @date 2020-04-25
 */
public class Main {

    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("192.168.0.100", 6380)) {
            jedis.auth("123456");
            String result = jedis.get("k1");
            System.out.println(result);
        }
    }
}
