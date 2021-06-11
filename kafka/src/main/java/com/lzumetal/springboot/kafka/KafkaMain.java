package com.lzumetal.springboot.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @author liaosi
 * @date 2021-06-11
 */
@SpringBootApplication
@EnableKafka
public class KafkaMain {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMain.class, args);
    }


}
