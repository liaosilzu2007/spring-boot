package com.lzumetal.springboot.demodatabase.test;

import com.lzumetal.springboot.demodatabase.StartupApplication;
import com.lzumetal.springboot.demodatabase.entity.Book;
import com.lzumetal.springboot.demodatabase.service.BookService;
import com.lzumetal.springboot.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartupApplication.class)
public class BookTest {


    @Autowired
    private BookService bookService;



    @Test
    public void testBookService() {
        List<Book> allBooks = bookService.getAllBooks();
        System.out.println(JsonUtils.toJson(allBooks));
    }

    @Test
    public void testBookController() {
        Book book = bookService.getById(1);
        System.out.println(JsonUtils.toJson(book));
    }

}
