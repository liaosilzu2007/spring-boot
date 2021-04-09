package com.lzumetal.springboot.annotation.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author liaosi
 * @date 2021-04-09
 */
public class MyImportSelector implements ImportSelector {

    /**
     * 通过指定全类名导入一个或多个实例至IOC容器
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.lzumetal.springboot.annotation.bean.TestBean2"};
    }

}
