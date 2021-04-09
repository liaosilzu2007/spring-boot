package com.lzumetal.springboot.annotation.config;

import com.lzumetal.springboot.annotation.bean.TestBean3;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author liaosi
 * @date 2021-04-09
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //指定bean定义信息（包括bean的类型、作用域...）
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(TestBean3.class);
        //注册一个bean指定bean名字（id）
        registry.registerBeanDefinition("testBean3333",rootBeanDefinition);
    }

}
