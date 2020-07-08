package com.lzumetal.springboot.retry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author liaosi
 * @date 2020-07-08
 */
@Service
@Slf4j
public class AnnotationRetryService {


    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 500L))
    public void cancelThirdOrder(long orderId) {
        log.info("重试取消第三方订单|START|orderId={}", orderId);
        int i = new Random().nextInt(100);
        if (i != 5) {
            //模拟抛出异常
            throw new RuntimeException("取消订单失败");
        }
        log.info("重试取消第三方订单|SUCC|orderId={}", orderId);
    }


    @Recover
    public void recover(Exception e, long orderId) {
        log.info("重试取消第三方订单失败|执行回退策略...|orderId={}", orderId);
    }
}
