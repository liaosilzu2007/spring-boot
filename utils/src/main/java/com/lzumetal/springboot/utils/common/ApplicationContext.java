package com.lzumetal.springboot.utils.common;

import com.lzumetal.springboot.utils.JsonUtils;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * @author liaosi
 */
public class ApplicationContext {


    @Getter
    private transient final Map<String, Object> attributes = Collections.synchronizedMap(new HashMap<>(4));


    public void setAttribute(String attrName, Object attrValue) {
        attributes.put(attrName, attrValue);
    }

    public Object getAttribute(String atterName) {
        return attributes.get(atterName);
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
