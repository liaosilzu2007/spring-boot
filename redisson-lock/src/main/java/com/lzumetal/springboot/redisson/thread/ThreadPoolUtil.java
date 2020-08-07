package com.lzumetal.springboot.redisson.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-08-06
 */
public class ThreadPoolUtil {


    public static ExecutorService cachedThreadPool = new ThreadPoolExecutor(5, 10,
            5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(50000));

}
