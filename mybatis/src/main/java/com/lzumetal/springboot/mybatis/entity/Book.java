package com.lzumetal.springboot.mybatis.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Book {

    private Integer id;

    /* 书名 */
    private String bookName;

    /* 作者 */
    private String author;

    /* 价格 */
    private BigDecimal price;

    /* 类别 */
    private Integer classify;

    /* 出版社 */
    private String publisher;

    /* 创建时间 */
    private Date createTime;



}