package com.lzumetal.clustersession.test;

import redis.clients.jedis.Jedis;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-02-02
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("119.23.246.206", 6379);
        jedis.select(0);
        jedis.get("k1");

    }
}
