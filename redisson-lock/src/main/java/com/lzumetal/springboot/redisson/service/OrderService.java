package com.lzumetal.springboot.redisson.service;

import com.lzumetal.springboot.redisson.enums.ERedisKey;
import com.lzumetal.springboot.redisson.lock.RedissonLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liaosi
 * @date 2020-08-06
 */
@Service
@Slf4j
public class OrderService {


    @Autowired
    private RedissonLockUtil redissonLockUtil;

    private static int flag = 0;


    public void refund(long orderId) {
        final String key = ERedisKey.LOCK_ORDER_REFUND.getCode() + orderId;
        boolean lock = false;
        try {
            lock = redissonLockUtil.tryLock(key, 10);
            if (!lock) {
                throw new RuntimeException("服务异常");
            }
            //此处用flag大于0表示退款成功
            if (flag > 0) {
                log.warn("订单已退款|orderId={}", orderId);
                throw new RuntimeException("订单已退款");
            }
            flag++;
            log.info("订单退款成功|orderId={}", orderId);
        } finally {
            if (lock) {
                redissonLockUtil.unlock(key);
            }
        }
    }


}
