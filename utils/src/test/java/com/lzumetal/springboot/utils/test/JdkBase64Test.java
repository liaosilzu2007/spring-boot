package com.lzumetal.springboot.utils.test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author liaosi
 * @date 2020-03-01
 */
public class JdkBase64Test {

    public static void main(String[] args) {
        String source = "java";
        String encode = Base64.getEncoder().encodeToString(source.getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);

        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println(new String(decode, StandardCharsets.UTF_8));
    }
}
