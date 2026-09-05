package com.lzumetal.springboot.grpc.server.impl;

import com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest;
import com.lzumetal.springboot.grpc.protocol.BookResponse;
import com.lzumetal.springboot.grpc.protocol.BookServiceGrpc;
import com.lzumetal.springboot.grpc.server.dao.BookDao;
import io.grpc.stub.StreamObserver;
import lombok.extern.java.Log;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liaosi
 */
@GrpcService
@Log
public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {


    @Autowired
    private BookDao bookDao;


    @Override
    public void getById(BookGetByIdRequest request, StreamObserver<BookResponse> responseObserver) {
        int id = request.getId();
        //log.info("Received request to get book by id: " + id);
    }
}
