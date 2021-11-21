package com.lzumetal.springboot.delaytask.config;

import com.lzumetal.springboot.utils.common.CommonRejectedExecutionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author liaosi
 * @date 2021-11-18
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 配置线程池
     */
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor asyncExecutor = new ThreadPoolTaskExecutor();
        asyncExecutor.setCorePoolSize(5);
        asyncExecutor.setQueueCapacity(10000);
        asyncExecutor.setKeepAliveSeconds(60);
        asyncExecutor.setMaxPoolSize(10);
        asyncExecutor.setThreadNamePrefix("customTaskExecutor-");
        asyncExecutor.setRejectedExecutionHandler(new CommonRejectedExecutionHandler());
        //关闭线程池时，先等待任务执行完
        asyncExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //关闭线程池时，最多等待60s
        asyncExecutor.setAwaitTerminationSeconds(20);
        asyncExecutor.initialize();
        return asyncExecutor;
    }


    /**
     * 定时任务线程池
     *
     * @return
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(5);
        taskScheduler.setThreadNamePrefix("customScheduleThreadPool-");
        taskScheduler.setRejectedExecutionHandler(new CommonRejectedExecutionHandler());
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        taskScheduler.setAwaitTerminationSeconds(5);
        return taskScheduler;
    }

}
