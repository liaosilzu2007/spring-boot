package com.lzumetal.springboot.delaytask.service.impl;

import com.lzumetal.springboot.delaytask.service.TimeoutCallable;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liaosi
 * @date 2021-11-18
 */
@Slf4j
public class PayTimeoutCallable implements TimeoutCallable {

    @Override
    public void call(String bizId) {
        log.info("order pay timeout, close the order|bizId={}", bizId);
    }


}
