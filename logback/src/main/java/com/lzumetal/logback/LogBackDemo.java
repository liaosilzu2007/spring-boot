package com.lzumetal.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaosi
 * @date 2020-03-21
 */
public class LogBackDemo {

    private static final Logger log = LoggerFactory.getLogger(LogBackDemo.class);

    public static void main(String[] args) {
        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("======error");
    }

}
