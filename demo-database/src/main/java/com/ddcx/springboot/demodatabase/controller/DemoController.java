package com.ddcx.springboot.demodatabase.controller;

import com.ddcx.springboot.democonfig.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liaosi on 2017/9/26.
 */
@RestController
public class DemoController {

    @Autowired
    private BookService bookService;

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
