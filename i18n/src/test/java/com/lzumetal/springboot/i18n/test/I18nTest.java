package com.lzumetal.springboot.i18n.test;

import com.lzumetal.springboot.i18n.I18nBootstrap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

/**
 * @author liaosi
 * @date 2022-01-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = I18nBootstrap.class)
@Slf4j
public class I18nTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    public void getMessage() {
        String usMsg = messageSource.getMessage("greeting.morning", null, Locale.US);
        String cnMsg = messageSource.getMessage("greeting.morning", null, Locale.CHINA);
        log.info("us:{}", usMsg);
        log.info("cn:{}", cnMsg);
    }

}
