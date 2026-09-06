package com.lzumetal.springboot.grpc.server.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liaosi
 */
@Getter
@Setter
@ToString
public class Book {

    private Long id;

    private String bookName;

    private String author;

    private BigDecimal price;

    private Date createTime;

}
