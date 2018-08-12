package com.lzumetal.springboot.apidoc.test;


import com.lzumetal.springboot.apidoc.BootStrap;
import com.lzumetal.springboot.apidoc.entity.Good;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrap.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MainTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testRestReq() {
        Good good = new Good(1, "联想笔记本电脑", new BigDecimal(5000.0));
        ResponseEntity<String> entity = testRestTemplate.getForEntity("/testApidoc", String.class, good);
        System.out.println(entity.getBody());
    }
}
