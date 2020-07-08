package com.lzumetal.springboot.aop.test;

import com.lzumetal.springboot.aop.AopBootStrap;
import com.lzumetal.springboot.aop.dubbo.OrderInvoker;
import com.lzumetal.springboot.aop.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liaosi
 * @date 2020-07-07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AopBootStrap.class)
@Slf4j
public class MainTest {


    @Autowired
    private OrderInvoker orderInvoker;

    @Test
    public void testQueryOrder() {
        Order order = orderInvoker.queryById(1L);
        log.info("queryOrder|{}", order);
    }


}
