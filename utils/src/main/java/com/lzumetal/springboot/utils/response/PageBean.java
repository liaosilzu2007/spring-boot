package com.lzumetal.springboot.utils.response;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liaosi
 * @date 2020-08-09
 */
@Getter
@ToString
public class PageBean<E extends Serializable> implements Serializable {

    /* 总录总条数 */
    private long total;

    /*当前页中存放的数据*/
    private Iterable<E> data;


    public PageBean(long total, Iterable<E> data) {
        this.total = total;
        this.data = data;
    }


    public PageBean(Iterable<E> data) {
        if (data != null) {
            long i = 0;
            for (E d : data) {
                i++;
            }
            this.total = i;
        }
        this.data = data;
    }

}
