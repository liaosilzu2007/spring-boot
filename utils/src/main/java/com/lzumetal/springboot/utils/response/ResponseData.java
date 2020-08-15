package com.lzumetal.springboot.utils.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

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

    private Object data;

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

    private ResponseData(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static ResponseData data(Object data) {
        return new ResponseData(200, "请求成功", data);
    }


    public static ResponseData success() {
        return new ResponseData(200, "请求成功", null);
    }


    public static void main(String[] args) {
        ResponseData data = ResponseData.data(new ArrayList<>());
        System.out.println(data);
    }


}
