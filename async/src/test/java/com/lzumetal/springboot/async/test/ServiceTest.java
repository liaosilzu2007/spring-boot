package com.lzumetal.springboot.async.test;

import com.lzumetal.springboot.async.BootStrap;
import com.lzumetal.springboot.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-05-03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrap.class)
@Slf4j
public class ServiceTest {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void testAsync() throws InterruptedException {
        log.info("do some bussiness|start...");
        asyncService.saveToCache();
        TimeUnit.SECONDS.sleep(1);
        log.info("do some bussiness|end...");
        TimeUnit.SECONDS.sleep(2);
    }

}
