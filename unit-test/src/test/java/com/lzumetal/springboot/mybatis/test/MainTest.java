package com.lzumetal.springboot.mybatis.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzumetal.springboot.mybatis.StartupApplication;
import com.lzumetal.springboot.mybatis.controller.BookController;
import com.lzumetal.springboot.mybatis.entity.Book;
import com.lzumetal.springboot.mybatis.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/*
https://spring.io/blog/2016/04/15/testing-improvements-in-spring-boot-1-4
MOCK —提供一个Mock的Servlet环境，内置的Servlet容器并没有真实的启动，主要搭配使用@AutoConfigureMockMvc

RANDOM_PORT — 提供一个真实的Servlet环境，也就是说会启动内置容器，然后使用的是随机端口
DEFINED_PORT — 这个配置也是提供一个真实的Servlet环境，使用的默认的端口，如果没有配置就是8080
NONE — 这是个神奇的配置，跟Mock一样也不提供真实的Servlet环境。
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplication.class)
public class MainTest {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private BookService bookService;

    @Autowired
    private BookController bookController;


    @Test
    public void testBookService() {
        List<Book> allBooks = bookService.getAllBooks();
        System.out.println(gson.toJson(allBooks));
    }

    @Test
    public void testBookController() {
        String s = bookController.getBookInfo(1);
        System.out.println(s);
    }

}
