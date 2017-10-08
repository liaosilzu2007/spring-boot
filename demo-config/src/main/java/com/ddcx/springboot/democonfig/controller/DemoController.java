package com.ddcx.springboot.democonfig.controller;

import com.ddcx.springboot.democonfig.entity.Book;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liaosi on 2017/9/26.
 */
@RestController
@EnableAutoConfiguration
public class DemoController {

    private static Gson gson = new Gson();
    @Autowired
    private Book book;

    @Value("${book.name}")
    private String bookName;

    @Value("${book.author}")
    private String bookAuthor;

    @Value("${book.publisher}")
    private String publisher;

    @Value("${book.price}")
    private String price;

    @RequestMapping("/bookInfo")
    String bookInfo() {
        return "bookName-->" + bookName + "\nbookAuthor-->" + bookAuthor +
                "\npublisher-->" + publisher + "\nprice-->" + price ;
    }

    @RequestMapping("/prefixConfigTest")
    String prefixConfigTest() {
        return gson.toJson(book);
    }
}
