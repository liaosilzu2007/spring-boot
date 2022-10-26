package com.ddcx.springboot.democonfig.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaosi
 */
@Getter
@Setter
@Configuration
public class AppConfiguration {

    @Value("${my.address}")
    private String myAddress;

}
