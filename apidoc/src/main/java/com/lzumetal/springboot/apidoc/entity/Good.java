package com.lzumetal.springboot.apidoc.entity;

import java.math.BigDecimal;

/**
 * 商品
 */
public class Good {


    private long id;

    private String name;

    private BigDecimal price;

    public Good() {
    }

    public Good(long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
