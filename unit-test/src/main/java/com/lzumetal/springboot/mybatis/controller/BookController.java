package com.lzumetal.springboot.mybatis.controller;

import com.google.gson.Gson;
import com.lzumetal.springboot.mybatis.entity.Book;
import com.lzumetal.springboot.mybatis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liaosi on 2017/9/26.
 */
@RestController
public class BookController {

    private static Gson gson = new Gson();

    @Autowired
    private BookService bookService;

    /**
     * GET请求+@PathVariable
     * @param id
     * @return
     */
    @RequestMapping(value = "/getBook/{id}", method = RequestMethod.GET)
    public String getBookInfo(@PathVariable("id") Integer id) {
        return gson.toJson(bookService.getById(id));
    }


    /**
     * GET请求
     * @param id
     * @return
     */
    @RequestMapping(value = "/getBookInfo2", method = RequestMethod.GET)
    public String getBoodInfo2(Integer id, String name) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        return gson.toJson(book);
    }


    /**
     * 普通form表单POST请求
     * @param id
     * @return
     */
    @RequestMapping(value = "/postBookInfo", method = RequestMethod.POST)
    public String postBoodInfo(Integer id) {
        return gson.toJson(bookService.getById(id));
    }


    /**
     * POST请求，参数为json格式
     * @param book
     * @return
     */
    @RequestMapping(value = "/postJson", method = RequestMethod.POST)
    public Book postJson(@RequestBody Book book) {
        return book;
    }



}
