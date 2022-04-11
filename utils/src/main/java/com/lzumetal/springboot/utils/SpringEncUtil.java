package com.lzumetal.springboot.utils;

import org.jasypt.util.text.BasicTextEncryptor;

public class SpringEncUtil {

    public static void main(String[] args) {
        String s = doEncrypt("RFRENQP9I419034324", "hello");
        System.out.println(s);
    }


    //加密方法
    public static String doEncrypt(String key, String words) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(key);
        //加密
        String encryptWords = encryptor.encrypt(words);
        return encryptWords;
    }

    //解密方法
    public static String doDecrypt(String key, String encryptWords) {
        //解密
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(key);
        String decryptWords = encryptor.decrypt(encryptWords);
        return decryptWords;
    }

}
