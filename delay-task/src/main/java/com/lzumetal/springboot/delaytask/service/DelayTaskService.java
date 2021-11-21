package com.lzumetal.springboot.delaytask.service;

import com.lzumetal.springboot.delaytask.enums.ETaskInfo;

/**
 * @author liaosi
 * @date 2021-11-18
 */
public interface DelayTaskService {

    void dealDelay(ETaskInfo eTaskInfo) throws IllegalAccessException, InstantiationException;

    boolean commit(String taskType, String bizId);

}
