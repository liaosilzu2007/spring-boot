package com.lzumetal.springboot.utils.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author liaosi
 */
public class Sha256Test {


    @Test
    public void sha256() {
        String source = "test12345678";
        String encode = DigestUtils.sha256Hex(source);
        System.out.println("========>" + encode);
    }


}
