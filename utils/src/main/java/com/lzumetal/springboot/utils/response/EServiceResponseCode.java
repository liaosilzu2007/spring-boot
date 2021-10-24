package com.lzumetal.springboot.utils.response;

/**
 * @author liaosi
 * @date 2021-10-24
 */
public enum EServiceResponseCode implements ResponseCode {

    CONTENT_UNAPPROVED(1014, "文本中含有违规内容"),
    CONTENT_CHECK_ERROR(1015, "文本审核异常，请稍候再试"),
    IMAGE_UNAPPROVED(1016, "图片含有违规内容"),
    IMAGE_CHECK_ERROR(1017, "图片审核异常，请稍候再试"),
    NEED_WXACCOUNT(1027, "请输入联系的微信号"),


    ;

    final int code;

    final String msg;

    EServiceResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
