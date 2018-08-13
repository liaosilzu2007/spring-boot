package com.lzumetal.springboot.apidoc.common;

public class ResponseData {


    private boolean success;

    private String code;

    private String message;

    private Object data;

    public ResponseData() {
    }

    public ResponseData(Object data) {
        this.success = true;
        this.code = EBaseResponseCode.C200.getCode();
        this.message = EBaseResponseCode.C200.getMsg();
        this.data = data;
    }

    public ResponseData(ResponseCode responseCode) {
        this.success = "200".equals(responseCode.getCode());
        this.code = responseCode.getCode();
        this.message = responseCode.getMsg();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
