package com.lzumetal.springboot.grpc.client.test;


import com.lzumetal.springboot.grpc.GrpcClientApplication;
import com.lzumetal.springboot.grpc.client.BookClient;
import com.lzumetal.springboot.grpc.protocol.BookResponse;
import com.lzumetal.springboot.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrpcClientApplication.class)
public class BookClientTest {


    @Autowired
    private BookClient bookClient;

    @Test
    public void getByIdTest() {
        BookResponse bookResponse = bookClient.getById(2L);
        log.info("bookResponse:{}", JsonUtils.gsonPrettyFormat(bookResponse));
    }


}
