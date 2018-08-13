package com.lzumetal.springboot.apidoc.test;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzumetal.springboot.apidoc.BootStrap;
import com.lzumetal.springboot.apidoc.common.ResponseData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrap.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MainTest {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testRestReq() {
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("id", 101);
        param.add("name", "iphone");
        param.add("price", 4599.00);
        ResponseEntity<ResponseData> entity = testRestTemplate.postForEntity("/testApidoc", param, ResponseData.class);
        System.out.println(gson.toJson(entity.getBody()));
    }
}
