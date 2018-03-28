package com.lzumetal.springboot.shedule.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-02-05
 */
@Component
public class ScheduleJob {


    @Scheduled(fixedRate = 1000 * 1)
    public void fixedRateTask() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("执行 fixedRate 任务的时间：" + new Date(System.currentTimeMillis()));
    }

    //@Scheduled(fixedDelay = 1000 * 1)
    public void fixedDelayTask() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("执行 fixedDelay 任务的时间：" + new Date(System.currentTimeMillis()));
    }

    //@Scheduled(cron = "0/10 * * * * ?")
    public void cronTask() {
        System.out.println("执行 cron 任务的时间：" + new Date(System.currentTimeMillis()));
    }


}
