package com.lzumetal.springboot.aop.exception;

/**
 * @author liaosi
 * @date 2020-07-07
 */
public class ServiceException extends Exception {

    private int code;

    private String msg;

    public ServiceException(int code, String msg) {
        super(msg);
        this.code = code;
    }

}
