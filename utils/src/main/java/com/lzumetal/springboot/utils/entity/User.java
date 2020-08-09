package com.lzumetal.springboot.utils.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author liaosi
 * @date 2019-02-24
 */
@Getter
@Setter
@ToString
public class User implements Serializable {

    private long id;

    private Date createTime;

    private String name;

    private String avatar;

    private List<Address> receiveAddrs;



}
