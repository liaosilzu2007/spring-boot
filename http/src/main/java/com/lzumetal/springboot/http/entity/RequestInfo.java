package com.lzumetal.springboot.http.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @author liaosi
 * @date 2020-08-10
 */
@Getter
@Setter
@ToString
public class RequestInfo implements Serializable {

    private Map<String, String> headerMap;

    private Map<String, String> cookieMap;

    private Map<String, String> paramMap;


}
