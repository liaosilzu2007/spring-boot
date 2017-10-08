package com.ddcx.springboot.democonfig.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liaosi on 2017/10/7.
 */
@Component  //因为要使用自动注入，所以要扫描注册到ioc容器中
@ConfigurationProperties(prefix="book")
public class Book {

    private String name;
    private String author;
    private String publisher;
    private double price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
