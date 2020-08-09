package com.lzumetal.springboot.utils.test;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author liaosi
 * @date 2020-03-01
 */
public class Base64Test {

    public static void main(String[] args) {
        String source = "java";
        Charset charset = StandardCharsets.UTF_8;

        byte[] bytes = Base64.encodeBase64(source.getBytes(charset));
        String encode = new String(bytes, charset);
        System.out.println("encode result: " + encode);

        byte[] decodeBytes = Base64.decodeBase64(encode.getBytes(charset));
        String decode = new String(decodeBytes, charset);
        System.out.println("decode result: " + decode);
    }
}
