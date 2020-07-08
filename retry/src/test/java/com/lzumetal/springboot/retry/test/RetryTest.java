package com.lzumetal.springboot.retry.test;

import com.lzumetal.springboot.retry.RetryBootStrap;
import com.lzumetal.springboot.retry.service.AnnotationRetryService;
import com.lzumetal.springboot.retry.service.RetryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liaosi
 * @date 2020-07-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RetryBootStrap.class)
public class RetryTest {

    @Autowired
    private RetryService retryService;

    @Autowired
    private AnnotationRetryService annotationRetryService;


    @Test
    public void testRetryCancel() {
        retryService.cancelThirdOrder(10000L);
    }


    @Test
    public void testRetryCancelWithRecoveryCallback() {
        retryService.cancelThirdOrderWithRecoveryCallack(10000L);
    }


    @Test
    public void testAnnotationRetryCancel(){
        annotationRetryService.cancelThirdOrder(10000L);
    }
}
