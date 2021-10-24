package com.lzumetal.springboot.redisson.enums;

/**
 * @author liaosi
 * @date 2020-08-06
 */
public enum ERedisKey {

    WECHAT_ACCESS_TOKEN("WECHAT_ACCESS_TOKEN", "微信AccessToken"),

    //==============================================================

    LOCK_ORDER_REFUND("LOCK_ORDER_REFUND:", "订单退款"),

    LOCK_WECHAT_ACCESS_TOKEN("LOCK_WECHAT_ACCESS_TOKEN", "获取微信AccessToken"),
    ;

    final String code;

    final String desc;

    ERedisKey(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
