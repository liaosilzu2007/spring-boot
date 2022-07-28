package com.lzumetal.springboot.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author liaosi
 */
public class DESUtil {

    /**
     * 加解密算法
     */
    private final static String ALGORITHM = "DESede";

    /**
     * 加密模式及填充方式
     */
    private final static String PATTERN = "DESede/ECB/pkcs5padding";


    /**
     * 加密
     *
     * @param sourceBytes
     * @param keyBytes
     * @return
     */
    public static byte[] encrypt(byte[] sourceBytes, byte[] keyBytes) {
        try {
            SecretKey secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
            Cipher cipher = Cipher.getInstance(PATTERN);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(sourceBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 解密
     *
     * @param encryptBytes
     * @param keyBytes
     * @return
     */
    public static byte[] decrypt(byte[] encryptBytes, byte[] keyBytes) {
        try {
            SecretKey secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
            Cipher cipher = Cipher.getInstance(PATTERN);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(encryptBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
