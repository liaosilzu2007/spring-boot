package com.lzumetal.springboot.demodatabase.test;

import com.lzumetal.springboot.demodatabase.StartupApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UrlRequestTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testRestReq() {
        ResponseEntity<String> entity = testRestTemplate.getForEntity("/getBook/{id}", String.class, 1);
        System.out.println(entity.getBody());
    }
}
