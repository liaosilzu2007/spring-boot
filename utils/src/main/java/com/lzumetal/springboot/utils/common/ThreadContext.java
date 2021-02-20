package com.lzumetal.springboot.utils.common;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 当前线程上下文
 *
 */
@Slf4j
public final class ThreadContext implements Serializable {

    private static final long serialVersionUID = 1L;

    private final static ThreadLocal<ApplicationContext> contextThreadLocal = new ThreadLocal<>();



    public static void setContext(ApplicationContext context) {
        contextThreadLocal.set(context);
        log.debug("ThreadContext|setContext:{}", context == null ? null : context.toString());
    }


    public static ApplicationContext getContext() {
        return contextThreadLocal.get();
    }


    public static void removeContext() {
        contextThreadLocal.remove();
        log.debug("ThreadContext|removeContext...");
    }



}
