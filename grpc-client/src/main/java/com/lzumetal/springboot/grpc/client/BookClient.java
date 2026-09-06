package com.lzumetal.springboot.grpc.client;

import com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest;
import com.lzumetal.springboot.grpc.protocol.BookResponse;
import com.lzumetal.springboot.grpc.protocol.BookServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BookClient {

    @GrpcClient("local-grpc-server")
    private BookServiceGrpc.BookServiceBlockingStub stub;


    public BookResponse getById(Long id) {
        return stub.getById(BookGetByIdRequest.newBuilder().setId(id).build());
    }

}
