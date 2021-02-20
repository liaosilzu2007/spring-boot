package com.lzumetal.springboot.interceptor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liaosi
 * @date 2021-02-20
 */
@Data
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nickname;

    private String loginName;


}
