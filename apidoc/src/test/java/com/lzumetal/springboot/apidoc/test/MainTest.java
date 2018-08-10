package com.lzumetal.springboot.apidoc.test;


import com.lzumetal.springboot.apidoc.BootStrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrap.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MainTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testRestReq() {
        ResponseEntity<String> entity = testRestTemplate.getForEntity("/testApidoc", String.class, 1);
        System.out.println(entity.getBody());
    }
}
