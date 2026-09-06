package com.lzumetal.springboot.mybatis.test;

import com.github.pagehelper.PageInfo;
import com.lzumetal.springboot.mybatis.StartupApplication;
import com.lzumetal.springboot.mybatis.service.BookService;
import com.lzumetal.springboot.mybatis.entity.Book;
import com.lzumetal.springboot.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplication.class)
public class BookTest {


    @Autowired
    private BookService bookService;


    @Test
    public void testGetAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        log.info("testGetAllBooks：{}", JsonUtils.gsonPrettyFormat(allBooks));
    }


    @Test
    public void testGetById() {
        Book book = bookService.getById(1);
        log.info("testGetById：{}", JsonUtils.gsonPrettyFormat(book));
    }

    @Test
    public void listByPageTest() {
        PageInfo<Book> pageInfo = bookService.listByPage("人民邮电出版社", 2, 5);
        log.info("listByPageTest：{}", JsonUtils.gsonPrettyFormat(pageInfo));
    }
}
