package com.lzumetal.springboot.delaytask.enums;

import com.lzumetal.springboot.delaytask.service.impl.PayTimeoutCallable;
import com.lzumetal.springboot.delaytask.service.TimeoutCallable;

/**
 * 使用这个枚举类当做一个配置类，实际上此枚举类对应的功能通过配置处理
 *
 * @author liaosi
 * @date 2021-11-18
 */
public enum ETaskInfo {

    ORDER_PAY_TIMEOUT("order_pay_timeout", PayTimeoutCallable.class);

    private String key;

    private Class<? extends TimeoutCallable> callable;

    ETaskInfo(String key, Class<? extends TimeoutCallable> callable) {
        this.key = key;
        this.callable = callable;
    }

    public String getKey() {
        return key;
    }

    public Class<? extends TimeoutCallable> getCallable() {
        return callable;
    }

    public static ETaskInfo getByKey(String key) {
        if (key == null) {
            return null;
        }
        for (ETaskInfo value : ETaskInfo.values()) {
            if (value.key.equals(key)) {
                return value;
            }
        }
        return null;
    }
}
