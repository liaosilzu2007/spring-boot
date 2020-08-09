package com.lzumetal.springboot.utils.common;

/**
 * @author liaosi
 * @date 2020-08-09
 */
public class ServiceException extends Exception {

    private int code;

    private String msg;



    public ServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
