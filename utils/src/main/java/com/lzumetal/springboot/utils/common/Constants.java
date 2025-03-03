package com.lzumetal.springboot.utils.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author liaosi
 * @date 2021-02-20
 */
public class Constants {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final String EMPTY_STR = "";

}
