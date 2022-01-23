package com.lzumetal.springboot.i18n.test;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author liaosi
 * @date 2022-01-21
 */
public class MainTest {

    @Test
    public void testMessageFormat() {
        //带占位符的字符串
        String pattern1 = "{0}，你好！你于{1}在工商银行存入{2} 元。";
        String pattern2 = "At {1,time,short} On {1,date,long}，{0} paid {2,number, currency}.";

        //用于动态替换占位符的参数
        Object[] params = {"John", new GregorianCalendar().getTime(), 1000};

        //使用默认本地化对象格式化信息
        String msg1 = MessageFormat.format(pattern1, params);

        //使用指定的本地化对象格式化信息
        MessageFormat mf = new MessageFormat(pattern2, Locale.US);
        String msg2 = mf.format(params);
        System.out.println(msg1);
        System.out.println(msg2);
    }


    @Test
    public void testResourceBundle() {
        ResourceBundle rb1 = ResourceBundle.getBundle("i18n/resource", Locale.US);
        ResourceBundle rb2 = ResourceBundle.getBundle("i18n/resource", Locale.CHINA);
        System.out.println("us:"+rb1.getString("greeting.common"));
        System.out.println("cn:"+rb2.getString("greeting.common"));

    }
}
