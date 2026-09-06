package com.lzumetal.springboot.grpc.server.service.impl.proto;

import com.google.protobuf.Timestamp;
import com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest;
import com.lzumetal.springboot.grpc.protocol.BookListByIdsRequest;
import com.lzumetal.springboot.grpc.protocol.BookResponse;
import com.lzumetal.springboot.grpc.protocol.BookServiceGrpc;
import com.lzumetal.springboot.grpc.server.pojo.Book;
import com.lzumetal.springboot.grpc.server.service.BookService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author liaosi
 */
@GrpcService
@Slf4j
public class BookServiceGrpcImpl extends BookServiceGrpc.BookServiceImplBase {


    @Autowired
    private BookService bookService;

    @Override
    public void getById(BookGetByIdRequest request, StreamObserver<BookResponse> responseObserver) {
        //获取数据，比如从数据库查询
        long id = request.getId();
        Book book = bookService.getById(id);
        //返回数据
        BookResponse bookResponse = buildBookResponse(book);
        //发送数据
        responseObserver.onNext(bookResponse);
        //表示响应数据完成。
        responseObserver.onCompleted();
    }


    @Override
    public void listByIds(BookListByIdsRequest request, StreamObserver<BookResponse> responseObserver) {
        //获取数据，比如从数据库查询
        List<Long> idsList = request.getIdsList();
        List<Book> bookList = bookService.listByIds(idsList);

        //遍历每条数据，逐条推送给客户端（stream模式）
        for (Book book : bookList) {
            responseObserver.onNext(buildBookResponse(book));
        }
        //表示响应数据完成
        responseObserver.onCompleted();
    }

    private static BookResponse buildBookResponse(Book book) {
        if (book == null) {
            return null;
        }
        return BookResponse.newBuilder()
                .setId(book.getId())
                .setBookName(book.getBookName())
                .setAuthor(book.getAuthor())
                .setPrice(book.getPrice() == null ? "" : String.valueOf(book.getPrice()))
                .setCreateTime(book.getCreateTime() == null ? null : Timestamp.newBuilder().setSeconds(book.getCreateTime().getTime() / 1000).build())
                .build();
    }

}
