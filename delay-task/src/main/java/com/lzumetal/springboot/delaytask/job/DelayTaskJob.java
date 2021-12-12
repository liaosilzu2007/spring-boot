package com.lzumetal.springboot.delaytask.job;

import com.lzumetal.springboot.delaytask.enums.ETaskInfo;
import com.lzumetal.springboot.delaytask.service.DelayTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author liaosi
 * @date 2021-11-18
 */
@Component
@Slf4j
public class DelayTaskJob {


    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private DelayTaskService delayTaskService;


    @Scheduled(cron = "0/20 * * * * ?")
    public void delayTask() {
        ETaskInfo[] values = ETaskInfo.values();
        for (ETaskInfo value : values) {
            taskExecutor.execute(() -> {
                try {
                    delayTaskService.dealDelay(value);
                } catch (Exception e) {
                    log.error("deal delay task by key|ERROR|ETaskInfo={}", value, e);
                }
            });
        }
        log.info("延时任务定时任务|END...");
    }



}
