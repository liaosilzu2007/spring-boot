package com.lzumetal.springboot.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liaosi
 * @date 2021-03-31
 */
public class BeanMapUtil {

    public static <T> Map<String, String> objectToStringMap(T bean) {
        Map<String, String> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                Object value = beanMap.get(key);
                if (value != null) {
                    map.put(key.toString(), value.toString());
                }
            }
        }
        return map;
    }
}
