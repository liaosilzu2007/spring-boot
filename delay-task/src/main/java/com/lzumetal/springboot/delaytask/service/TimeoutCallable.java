package com.lzumetal.springboot.delaytask.service;

/**
 * @author liaosi
 * @date 2021-11-18
 */
public interface TimeoutCallable {

    void call(String bizId);

}
