package com.lzumetal.springboot.demodatabase.controller;

import com.lzumetal.springboot.demodatabase.service.BookService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liaosi on 2017/9/26.
 */
@RestController
public class BookController {

    private static Gson gson = new Gson();

    @Autowired
    private BookService bookService;


    @RequestMapping(value = "/getBook/{id}", method = RequestMethod.GET)
    public String bookInfo(@PathVariable("id") Integer id) {
        return gson.toJson(bookService.getById(id));
    }

    @RequestMapping("/allBooks")
    public String allBooks() {
        return gson.toJson(bookService.getAllBooks());
    }

}
