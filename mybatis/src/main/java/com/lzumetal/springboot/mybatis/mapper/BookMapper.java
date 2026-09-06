package com.lzumetal.springboot.mybatis.mapper;


import com.lzumetal.springboot.mybatis.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {

    int insert(Book record);

    List<Book> selectAll();

    List<Book> listByParam(@Param(value = "param") Map<String, ?> param);

    Book getById(@Param(value = "id") Integer id);

    void batchInsert(List<Book> books);
}