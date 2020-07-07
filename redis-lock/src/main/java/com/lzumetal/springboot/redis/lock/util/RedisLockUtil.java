package com.lzumetal.springboot.redis.lock.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-06-07
 */
@Service
@Slf4j
public class RedisLockUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /* redis 存储的过期时间 */
    private static final long EXPIRE_SECOND = 30L;

    private static final long LOOP_INTERVAL = 300L;


    /**
     * 尝试获取分布式锁
     * <p>
     * 如果使用原生Jedis或JedisCluster的话，可以写成如下
     * LOCK_SUCCESS = "OK";
     * SET_IF_NOT_EXIST = "NX";
     * SET_WITH_EXPIRE_TIME = "PX";
     * EXPIRE_TIME = 30 * 1000;
     * String result = jedis.set(lockKey, lockValue, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, EXPIRE_TIME);
     * return LOCK_SUCCESS.equalsIgnoreCase(result);
     *
     * @param lockKey   redis锁的key。即需要加锁的资源
     * @param lockValue redis锁的value。不同的客户端不一样
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, String lockValue) {
        if (StringUtils.isBlank(lockKey) || StringUtils.isBlank(lockValue)) {
            throw new RuntimeException("加锁参数不能为空");
        }
        Boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.set(lockKey.getBytes(), lockValue.getBytes(),
                Expiration.from(EXPIRE_SECOND, TimeUnit.SECONDS), RedisStringCommands.SetOption.SET_IF_ABSENT));
        if (Objects.equals(result, Boolean.TRUE)) {
            log.error("RedisLockUtil|lock|SUCC|{}|{}", lockKey, lockValue);
            return true;
        }
        //log.error("RedisLockUtil|lock|FAIL|{}|{}", lockKey, lockValue);
        return false;
    }

    /**
     * 释放锁。此处通过执行lua脚本的方式
     *
     * 如果使用原生Jedis的话，则是调用eval方法。
     * jedis.eval(script, Collections.singletonList(unlockKey), Collections.singletonList(unlockValue));
     * 注意：eval函数在redis集群环境中不支持
     *
     * @param unlockKey   redis锁的key。即需要解锁的资源
     * @param unlockValue redis锁的value。不同的客户端不一样
     */
    public void unlock(String unlockKey, String unlockValue) {
        if (StringUtils.isBlank(unlockKey) || StringUtils.isBlank(unlockValue)) {
            throw new RuntimeException("解锁参数不能为空");
        }
        //执行Lua脚本，删除unlockValue匹配的锁
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Boolean> redisScript = new DefaultRedisScript(script, Boolean.class);
        Boolean result = redisTemplate.execute(redisScript, new StringRedisSerializer(),
                new Jackson2JsonRedisSerializer(Boolean.class), Collections.singletonList(unlockKey), unlockValue);
        if (Objects.equals(result, Boolean.TRUE)) {
            log.info("RedisLockUtil|unlock|SUCC|{}|{}", unlockKey, unlockValue);
            return;
        }
        log.warn("RedisLockUtil|unlock|删除redis锁失败|{}|{}", unlockKey, unlockValue);
    }
}
