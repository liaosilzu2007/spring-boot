package com.lzumetal.springboot.annotation.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzumetal.springboot.annotation.AnnotationBootstrap;
import com.lzumetal.springboot.annotation.bean.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author liaosi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnnotationBootstrap.class)
@Slf4j
public class IocBeanTest {

//    @Autowired
//    @Qualifier("user1")
    @Resource(name = "user1")
    private UserBean userBean;

    @Autowired
    protected ObjectMapper objectMapper;


    @Test
    public void userBeanGetTest() throws JsonProcessingException {
        log.info(objectMapper.writeValueAsString(userBean));
    }


}
