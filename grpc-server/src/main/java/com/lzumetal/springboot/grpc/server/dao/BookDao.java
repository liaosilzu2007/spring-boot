package com.lzumetal.springboot.grpc.server.dao;

import com.lzumetal.springboot.grpc.server.pojo.Book;
import org.apache.ibatis.annotations.Mapper;



/**
 * @author liaosi
 */
@Mapper
public interface BookDao {



    Book selectById(Long id);



}
