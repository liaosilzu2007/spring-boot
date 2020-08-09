package com.lzumetal.springboot.utils.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liaosi
 * @date 2020-08-08
 */
@Getter
@Setter
@ToString
public class ResponseData implements Serializable {

    private int code;

    private String message;

    private Serializable data;

    public ResponseData() {
    }

    public ResponseData(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResponseData(int code, String message, Serializable data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static ResponseData data(Serializable data) {
        return new ResponseData(200, "请求成功", data);
    }


    public static void main(String[] args) {
        ResponseData data = ResponseData.data(4);
        System.out.println(data);
    }


}
