package com.lzumetal.springboot.redisson.enums;

/**
 * @author liaosi
 * @date 2020-08-06
 */
public enum ERedisKey {

    LOCK_ORDER_REFUND("LOCK_ORDER_REFUND:", "订单退款"),
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
