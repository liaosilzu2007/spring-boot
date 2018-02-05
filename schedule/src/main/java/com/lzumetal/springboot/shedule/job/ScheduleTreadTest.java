package com.lzumetal.springboot.shedule.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-02-05
 */
@Component
public class ScheduleTreadTest {

    @Scheduled(cron = "0/3 * * * * ?")
    public void task01() {
        System.out.println(Thread.currentThread().getName() + "----> task01");
    }

    @Scheduled(cron = "0/2 * * * * ?")
    public void task02() {
        System.out.println(Thread.currentThread().getName() + "----> task02");
    }

    @Scheduled(cron = "0/3 * * * * ?")
    public void task03() {
        System.out.println(Thread.currentThread().getName() + "----> task03");
    }

}
