package com.lzumetal.springboot.grpc.server.impl;

import com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest;
import com.lzumetal.springboot.grpc.protocol.BookResponse;
import com.lzumetal.springboot.grpc.protocol.BookServiceGrpc;
import com.lzumetal.springboot.grpc.server.dao.BookDao;
import com.lzumetal.springboot.grpc.server.pojo.Book;
import io.grpc.stub.StreamObserver;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liaosi
 */
@GrpcService
@Slf4j
public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {


    @Autowired
    private BookDao bookDao;


    @Override
    public void getById(BookGetByIdRequest request, StreamObserver<BookResponse> responseObserver) {
        long id = request.getId();
        Book book = bookDao.selectById(id);
        log.info("查询完成|{}|{}", id, book.toString());
        //log.info("Received request to get book by id: " + id);
    }
}
