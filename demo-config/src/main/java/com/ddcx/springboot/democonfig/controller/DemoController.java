package com.ddcx.springboot.democonfig.controller;

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

}
