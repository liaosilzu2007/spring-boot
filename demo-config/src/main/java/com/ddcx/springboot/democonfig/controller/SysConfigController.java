package com.ddcx.springboot.democonfig.controller;

import com.ddcx.springboot.democonfig.config.AppConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaosi
 */
@Slf4j
@RestController
@RequestMapping(value = "sys-config", method = {RequestMethod.GET, RequestMethod.POST})
public class SysConfigController {

    @Autowired
    private AppConfiguration appConfiguration;

    @RequestMapping("/get-command-line-params")
    public void get() {
        log.info("getProperty my.name={}", System.getProperty("my.name"));
        log.info("getProperty my.address={}", System.getProperty("my.address"));
        log.info("getenv my.name={}", System.getenv("my.name"));
        log.info("getenv my.address={}", System.getenv("my.address"));
        log.info("appConfiguration my.address={}", appConfiguration.getMyAddress());
    }


}
