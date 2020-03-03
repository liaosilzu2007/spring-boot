package com.lzumetal.springboot.im4java.test;

import com.lzumetal.springboot.im4java.utils.Im4JavaUtil;

import java.util.Map;

/**
 * @author liaosi
 * @date 2018-12-29
 */
public class Im4javaTest {

    public static void main(String[] args) {

        System.out.println("=======获取图片信息=======");
        Map<String, String> imageInfo = Im4JavaUtil.getImageInfo("C:\\Users\\Administrator\\Desktop\\backgroud.jpg");
        for (Map.Entry<String, String> entry : imageInfo.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
        }
        System.out.println("=======获取图片信息=======");

    }
}
