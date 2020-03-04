package com.lzumetal.springboot.demodatabase.test;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * @author liaosi
 * @date 2020-03-04
 */
public class AESUtil {


    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;


    /**
     * 加密
     * CBC模式需要指定初始化向量iv，而ECB模式是不需要的
     *
     * @param content    二进制数据原始内容
     * @param aesKeyByte 二进制密钥
     * @param ivByte     二进制初始化向量
     * @return 加密后的二进制数据
     */
    public static byte[] encrypt(byte[] content, byte[] aesKeyByte, byte[] ivByte) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec skeySpec = new SecretKeySpec(aesKeyByte, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivByte);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密
     *
     * @param content    二进制数据原始内容
     * @param aesKeyByte 二进制密钥
     * @return 加密后的二进制数据
     */
    public static byte[] encrypt(byte[] content, byte[] aesKeyByte) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            SecretKeySpec skeySpec = new SecretKeySpec(aesKeyByte, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 加密
     *
     * @param sourceText 需要加密的文本
     * @param key        密钥
     * @return 加密后的密文
     */
    public static String encrypt(String sourceText, String key) {
        Charset charset = DEFAULT_CHARSET;
        byte[] encrypt = encrypt(sourceText.getBytes(charset), key.getBytes(charset));
        return Base64.encodeBase64String(encrypt);
    }

    /**
     * 加密
     *
     * @param sourceText 需要加密的明文
     * @param key        密钥
     * @param initVector 加密的初始化向量
     * @return 加密后的密文
     */
    public static String encrypt(String sourceText, String key, String initVector) {
        Charset charset = DEFAULT_CHARSET;
        byte[] encrypt = encrypt(sourceText.getBytes(charset), key.getBytes(charset), initVector.getBytes());
        return Base64.encodeBase64String(encrypt);
    }


    /**
     * 解密
     * CBC模式需要指定初始化向量iv，而ECB模式是不需要的
     *
     * @param content    加密后的二进制数据
     * @param aesKeyByte 二进制密钥
     * @param ivByte     二进制的初始向量
     * @return 解密后的二进制数据
     */
    public static byte[] decrypt(byte[] content, byte[] aesKeyByte, byte[] ivByte) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            Key sKeySpec = new SecretKeySpec(aesKeyByte, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivByte);
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, ivParameterSpec);
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 解密
     *
     * @param content    加密后的二进制数据
     * @param aesKeyByte 二进制密钥
     * @return 解密后的二进制数据
     */
    public static byte[] decrypt(byte[] content, byte[] aesKeyByte) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            Key sKeySpec = new SecretKeySpec(aesKeyByte, "AES");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
            return cipher.doFinal(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 解密
     *
     * @param sourceText 加密后的密文
     * @param key        密钥
     * @return 解密后的原文
     */
    public static String decrypt(String sourceText, String key) {
        Charset charset = DEFAULT_CHARSET;
        byte[] bytes = Base64.decodeBase64(sourceText);
        byte[] encrypt = decrypt(bytes, key.getBytes(charset));
        return new String(encrypt, charset);
    }


    /**
     * 根据密码生成一个128位的AES密钥
     *
     * @param password 密码
     * @return Base64编码的AES密钥
     * @throws NoSuchAlgorithmException
     */
    public static String generateSecretKey(String password) throws NoSuchAlgorithmException {

        //构造密钥生成器，指定AES算法
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

        //根据传入的密钥字节生成一个可信任的随机数据源
        SecureRandom secureRandom = new SecureRandom(password.getBytes(DEFAULT_CHARSET));

        //初始化一个128的密钥生成器
        keyGenerator.init(128, secureRandom);

        //生成一个原始的对称密钥
        SecretKey originSecretKey = keyGenerator.generateKey();

        //根据原始对称密钥生成一个AES二进制密钥
        byte[] keyEncoded = originSecretKey.getEncoded();

        //AES二进制密钥用Base64编码
        return Base64.encodeBase64String(keyEncoded);
    }


    public static void main(String[] args) throws Exception {
        String text = "hello world";
        String password = UUID.randomUUID().toString();
        String secretKey = generateSecretKey(password);
        System.err.println("secretKey:" + secretKey);

        //encrypt方法加密后的明文使用了Base64编码，decrypt解密时先用Base64解密
        String encrypt = encrypt(text, secretKey);
        System.out.println("encrypt:" + encrypt);
        String decrypt = decrypt(encrypt, secretKey);
        System.out.println("decrypt:" + decrypt);

        System.out.println(new String(text.getBytes()));
        System.out.println(new String(Base64.decodeBase64(text)));
    }
}
