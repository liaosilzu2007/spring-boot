package com.lzumetal.springboot.utils.test;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author liaosi
 * @date 2020-03-03
 */
public class MD5Test {

    public static void main(String[] args) {
        String source = "中国";
        String encode = DigestUtils.md5Hex(source);
        System.out.println("========>" + encode);
    }

}
