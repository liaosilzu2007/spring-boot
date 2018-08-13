package com.lzumetal.springboot.apidoc.common;

/**
 * 异常code
 */
public interface ResponseCode {

    String getCode();

    String getMsg();

    void setCode(String code);

    void setMsg(String msg);
}
