package com.lzumetal.springboot.utils.test;

import java.util.function.Consumer;

/**
 * @author liaosi
 */
public class FunctionalExample {


    public static void main(String[] args) {

        Consumer<String> saveToHdfs = new Consumer<String>() {

            private int num = 0;

            @Override
            public void accept(String content) {
                System.out.println("数据保存至【HDFS】,保存内容为：" + content);
                num++;
                System.out.println("今日已保存数据条数：" + num);
            }
        };

        saveToHdfs.accept("一条订单记录");
        saveToHdfs.accept("个人信息变更");
    }


}
