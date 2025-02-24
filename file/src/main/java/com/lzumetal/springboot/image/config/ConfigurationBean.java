package com.lzumetal.springboot.image.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author liaosi
 * @date 2020-08-09
 */
@Setter
@Getter
@Configuration
public class ConfigurationBean {

    @Value("${env}")
    private String env;

    @Value("${xcx_app_id}")
    private String xcxAppId;

    @Value("${xcx_secret}")
    private String xcxSecret;

}
