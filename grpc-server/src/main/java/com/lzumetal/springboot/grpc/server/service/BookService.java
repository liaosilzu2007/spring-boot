package com.lzumetal.springboot.grpc.server.service;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest;
import com.lzumetal.springboot.grpc.protocol.BookResponse;
import com.lzumetal.springboot.grpc.server.dao.BookDao;
import com.lzumetal.springboot.grpc.server.pojo.Book;
import com.lzumetal.springboot.utils.JsonUtils;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookDao bookDao;



    public Book getById(Long id) {
        if (id == null) {
            return null;
        }
        Book book = bookDao.selectById(id);
        log.info("查询完成|{}|{}", id, JsonUtils.gsonPrettyFormat(book));
        return book;
    }


    public List<Book> listByIds(java.util.List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return bookDao.ListByIds(ids);
    }


    public long count(Map<String, ?> params) {
        return bookDao.count(params);
    }
}
