package com.lzumetal.springboot.retry.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 重试服务类
 *
 * @author liaosi
 * @date 2020-07-08
 */
@Service
@Slf4j
public class RetryService implements InitializingBean {


    private static RetryTemplate retryTemplate;


    @Override
    public void afterPropertiesSet() {
        retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(5)); //重试5次
        FixedBackOffPolicy fbop = new FixedBackOffPolicy();
        fbop.setBackOffPeriod(300);   //重试间隔300毫秒
        retryTemplate.setBackOffPolicy(fbop);
    }


    /**
     * 重试取消订单
     *
     * @param orderId
     */
    public void cancelThirdOrder(long orderId) {
        final RetryCallback<Void, Throwable> retry = context -> {
            log.info("重试取消第三方订单|START|orderId={}", orderId);
            int i = new Random().nextInt(10);
            if (i != 5) {
                //模拟抛出异常
                throw new RuntimeException("取消订单失败");
            }
            log.info("重试取消第三方订单|SUCC|orderId={}", orderId);
            return null;
        };
        try {
            retryTemplate.execute(retry);
        } catch (Throwable throwable) {
            log.error("重试取消第三方订单|ERROR|orderId={}", orderId, throwable);
        }
    }

    /**
     * 重试取消订单，并有失败回退策略
     *
     * @param orderId
     */
    public void cancelThirdOrderWithRecoveryCallack(long orderId) {
        final RetryCallback<Void, Throwable> retry = context -> {
            log.info("重试取消第三方订单|START|orderId={}", orderId);
            int i = new Random().nextInt(10);
            if (i != 5) {
                //模拟抛出异常
                throw new RuntimeException("取消订单失败");
            }
            log.info("重试取消第三方订单|SUCC|orderId={}", orderId);
            return null;
        };
        try {
            retryTemplate.execute(retry, context -> {
                log.info("重试取消第三方订单失败|执行回退策略...");
                return null;
            });
        } catch (Throwable throwable) {
            log.error("重试取消第三方订单|ERROR|orderId={}", orderId, throwable);
        }
    }

}
