package com.lzumetal.springboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lzumetal.springboot.utils.common.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author liaosi
 * @date 2021-02-20
 */
@Slf4j
public class JsonUtils {


    public static void main(String[] args) {
        System.out.println(toJson(null));
    }



    public static String toJson(Object object) {
        try {
            return Constants.OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("toJson(Object object)|异常|", e);
        }
        return null;
    }

    public static <T> T fromJSON(String json, Class<T> clazz) {
        try {
            return Constants.OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
