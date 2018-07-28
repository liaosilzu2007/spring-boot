package com.lzumetal.springboot.demodatabase.mapper;


import com.lzumetal.springboot.demodatabase.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    int insert(Book record);
    List<Book> selectAll();
    Book getById(@Param(value = "id") Integer id);
}