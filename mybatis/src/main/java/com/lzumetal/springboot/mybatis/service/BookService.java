package com.lzumetal.springboot.mybatis.service;

import com.google.common.collect.Lists;
import com.lzumetal.springboot.mybatis.entity.Book;
import com.lzumetal.springboot.mybatis.mapper.BookMapper;
import com.lzumetal.springboot.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void batchAdd1(List<Book> books) {
        int toIndex;
        int capacity = 200;
        List<Book> tempHolder;
        for (int i = 0; i <= books.size() / capacity; i++) {
            toIndex = (i + 1) * capacity;
            tempHolder = books.subList(i * capacity, Math.min(toIndex, books.size()));
            bookMapper.batchInsert(tempHolder);
        }
    }


    public void batchAdd2(List<Book> books) {
        List<List<Book>> partition = Lists.partition(books, 200);
        for (List<Book> partionBooks:partition) {
            bookMapper.batchInsert(partionBooks);
        }
    }


    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a", "ab", "c", "de", "f", "g","hi");
        List<List<String>> partition = Lists.partition(list, 3);
        for (List<String> strings : partition) {
            System.out.println(JsonUtils.toJson(strings));
        }
    }

}
