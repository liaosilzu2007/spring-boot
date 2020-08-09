package com.lzumetal.springboot.utils.test;

import com.lzumetal.springboot.utils.ZxingUtil;
import org.junit.Test;

import java.io.File;

/**
 * @author liaosi
 * @date 2020-08-09
 */
public class ZxingTest {

    @Test
    public void testQrcode() {
        File file = new File("d:/test.png");
        ZxingUtil.encodeQrcodeForFile("http://localhost:8090/shop/getOrder?id=100", file);
    }


    @Test
    public void testBarcode() {
        File file = new File("d:/abc.png");
        ZxingUtil.encodeBarcodeForFile("http://localhost:8090/shop/getOrder?id=100",null, file);
    }

}
