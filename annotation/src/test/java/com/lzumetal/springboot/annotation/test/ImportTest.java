package com.lzumetal.springboot.annotation.test;

import com.lzumetal.springboot.annotation.AnnotationBootstrap;
import com.lzumetal.springboot.annotation.bean.TestBean1;
import com.lzumetal.springboot.annotation.bean.TestBean2;
import com.lzumetal.springboot.annotation.bean.TestBean3;
import com.lzumetal.springboot.annotation.bean.TestBean4;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liaosi
 * @date 2021-04-09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnnotationBootstrap.class)
@Slf4j
public class ImportTest {


    @Autowired(required = false)
    private TestBean1 testBean1;

    @Autowired(required = false)
    private TestBean2 testBean2;

    @Autowired(required = false)
    private TestBean3 testBean3;

    @Autowired(required = false)
    private TestBean4 testBean4;


    @Test
    public void Import() {
        log.info("testBean1={}", testBean1);
        log.info("testBean2={}", testBean2);
        log.info("testBean3={}", testBean3);
        log.info("testBean4={}", testBean4);
    }


}
