package com.lzumetal.springboot.elasticsearch.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author liaosi
 * @date 2021-03-31
 */
@Getter
@Setter
@ToString
public class AccountRequest {

    private String nameKey;

    private String addressKey;

    private String preAddress;

    private String gender;

    private List<Integer> ages;

    private Long startBalance;

    private Long endBalance;

    private String notState;

    private String[] fields;

    private int from;

    private int size;

}
