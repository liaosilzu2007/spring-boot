package com.lzumetal.springboot.delaytask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author liaosi
 * @date 2021-11-18
 */
@SpringBootApplication(exclude = {TaskExecutionAutoConfiguration.class})
@EnableScheduling
public class DelayTaskBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(DelayTaskBootstrap.class, args);
    }

}
