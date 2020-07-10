package com.lzumetal.springboot.utils.test;

import com.lzumetal.springboot.utils.HttpRequest;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

/**
 * @author liaosi
 * @date 2020-07-10
 */
public class HttpTest {


    @Test
    public void test() throws IOException {
        String resp = new HttpRequest().post("https://www.baidu.com", Collections.emptyMap(), 5000, 30000);
        System.out.println(resp);
    }

}
