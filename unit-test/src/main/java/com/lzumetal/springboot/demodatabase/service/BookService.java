package com.lzumetal.springboot.demodatabase.service;

import com.lzumetal.springboot.demodatabase.entity.Book;
import com.lzumetal.springboot.demodatabase.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liaosi on 2017/9/26.
 */
@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<Book> getAllBooks() {
        return bookMapper.selectAll();
    }

    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }
}
