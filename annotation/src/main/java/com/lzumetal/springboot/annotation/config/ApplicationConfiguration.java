package com.lzumetal.springboot.annotation.config;

import com.lzumetal.springboot.annotation.bean.TestBean1;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author liaosi
 * @date 2021-04-09
 */
@Configuration
@Import({TestBean1.class,
        MyImportSelector.class,
        MyImportBeanDefinitionRegistrar.class})
public class ApplicationConfiguration {


}
