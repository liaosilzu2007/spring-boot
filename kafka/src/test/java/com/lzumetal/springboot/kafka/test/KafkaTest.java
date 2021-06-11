package com.lzumetal.springboot.kafka.test;

import com.lzumetal.springboot.kafka.KafkaMain;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author liaosi
 * @date 2021-06-11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaMain.class)
@Slf4j
public class KafkaTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Test
    public void send() throws JSONException {
        String topic = "test-status";
        JSONObject json = new JSONObject();
        json.put("id", "1234");
        json.put("name", "zhangsan");
        json.put("age", 10);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, null, json.toString());
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(record);
        log.info("发送消息完成|send={}", send);
    }




}
