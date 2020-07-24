package com.lzumetal.springboot.spring.test;

import com.lzumetal.springboot.spring.SpringBootStrap;
import com.lzumetal.springboot.spring.service.DefaultNotifyStrategy;
import com.lzumetal.springboot.spring.util.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liaosi
 * @date 2020-07-24
 */
@SpringBootTest(classes = SpringBootStrap.class)
@RunWith(SpringRunner.class)
public class MainTest {



    @Test
    public void testGetBean() {
        DefaultNotifyStrategy bean = SpringContextUtil.getBean(DefaultNotifyStrategy.class);
        System.out.println(bean);
    }


}
