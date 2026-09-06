package com.lzumetal.springboot.grpc.server.test;

import com.lzumetal.springboot.grpc.server.GrpcServerApplication;
import com.lzumetal.springboot.grpc.server.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrpcServerApplication.class)
public class BookTest {


    @Autowired
    private BookService bookService;

    @Test
    public void getByIdTest() {
        bookService.getById(1L);
    }

}
