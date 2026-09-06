package com.lzumetal.springboot.grpc.server.impl;

import com.lzumetal.springboot.grpc.protocol.BookGetByIdRequest;
import com.lzumetal.springboot.grpc.protocol.BookResponse;
import com.lzumetal.springboot.grpc.server.dao.BookDao;
import com.lzumetal.springboot.grpc.server.pojo.Book;
import com.lzumetal.springboot.grpc.server.service.impl.proto.BookServiceGrpcImpl;
import io.grpc.stub.StreamObserver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * BookServiceImpl 单元测试
 *
 * @author liaosi
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceGrpcImplTest {

    @Mock
    private BookDao bookDao;

    @Mock
    private StreamObserver<BookResponse> responseObserver;

    @InjectMocks
    private BookServiceGrpcImpl bookService;

    private Book testBook;

    @Before
    public void setUp() {
        testBook = new Book();
        testBook.setId(1L);
        testBook.setBookName("Spring Boot实战");
        testBook.setAuthor("廖思");
        testBook.setPrice(new BigDecimal("59.90"));
        testBook.setCreateTime(new Date());
    }

    /**
     * 测试 getById 方法：当数据库中存在对应书籍时，验证 DAO 被正确调用
     */
    @Test
    public void testGetById_bookExists() {
        // given
        long bookId = 1L;
        BookGetByIdRequest request = BookGetByIdRequest.newBuilder()
                .setId((int) bookId)
                .build();
        when(bookDao.selectById(bookId)).thenReturn(testBook);

        // when
        bookService.getById(request, responseObserver);

        // then
        verify(bookDao, times(1)).selectById(eq(bookId));
    }

    /**
     * 测试 getById 方法：当数据库返回 null 时，验证 DAO 被正确调用
     */
    @Test
    public void testGetById_bookNotFound() {
        // given
        long bookId = 999L;
        BookGetByIdRequest request = BookGetByIdRequest.newBuilder()
                .setId((int) bookId)
                .build();
        when(bookDao.selectById(bookId)).thenReturn(null);

        // when & then - 预期抛出 NullPointerException，因为源码中直接调用了 book.toString()
        try {
            bookService.getById(request, responseObserver);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // 源码第30行 book.toString() 在 book 为 null 时会抛出 NPE
            verify(bookDao, times(1)).selectById(eq(bookId));
        }
    }

    /**
     * 测试 getById 方法：验证请求参数中的 id 被正确传递到 DAO
     */
    @Test
    public void testGetById_verifyIdPassedCorrectly() {
        // given
        int bookId = 42;
        BookGetByIdRequest request = BookGetByIdRequest.newBuilder()
                .setId(bookId)
                .build();
        when(bookDao.selectById((long) bookId)).thenReturn(testBook);

        // when
        bookService.getById(request, responseObserver);

        // then
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(bookDao).selectById(captor.capture());
        assertEquals((long) bookId, captor.getValue().longValue());
    }
}
