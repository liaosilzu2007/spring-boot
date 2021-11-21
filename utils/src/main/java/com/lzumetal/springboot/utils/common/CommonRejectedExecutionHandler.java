package com.lzumetal.springboot.utils.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liaosi
 * @date 2021-11-18
 */
@Slf4j
public class CommonRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.error("当前线程池队列已满！任务 {} 被线程池 {} 拒绝", r.toString(), executor.toString());
    }

}
