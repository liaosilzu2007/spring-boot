package com.lzumetal.springboot.grpc.server.dao;

import com.lzumetal.springboot.grpc.server.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * @author liaosi
 */
@Mapper
public interface BookDao {



    Book selectById(Long id);


    List<Book> ListByIds(List<Long> ids);


    List<Book> listAll();


    long count(Map<String, ?> params);
}
