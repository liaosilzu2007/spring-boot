package com.lzumetal.springboot.aop.exception;

/**
 * @author liaosi
 * @date 2020-07-07
 */
public class OrderServiceException extends RuntimeException {

    public OrderServiceException(String message) {
        super(message);
    }

}
