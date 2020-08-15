package com.lzumetal.springboot.utils.response;

/**
 * @author liaosi
 * @date 2020-08-15
 */
public enum EBaseResponseCode implements ResponseCode {

    C200(200, "成功"),
    C401(401, "参数错误"),
    C403(403, "FORBIDDEN"),
    C430(430, "非法请求"),
    C500(500, "服务器错误"),
    C515(515, "无权限"),
    ;

    final int code;
    final String message;

    EBaseResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
