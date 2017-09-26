package com.ddcx.springboot.demodatabase.mapper;


import com.ddcx.springboot.democonfig.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    int insert(Book record);
    List<Book> selectAll();
}