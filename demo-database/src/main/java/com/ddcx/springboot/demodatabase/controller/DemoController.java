package com.ddcx.springboot.demodatabase.controller;

import com.ddcx.springboot.demodatabase.service.BookService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liaosi on 2017/9/26.
 */
@RestController
public class DemoController {

    private static Gson gson = new Gson();

    @Autowired
    private BookService bookService;


    @RequestMapping("/getBook/{id}")
    String bookInfo(@PathVariable("id") Integer id) {
        return gson.toJson(bookService.getById(id));
    }

}
