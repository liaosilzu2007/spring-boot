package com.lzumetal.springboot.mybatis.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author liaosi
 */
public class TestMySQLConnection {

        public static void main(String[] args) {
            //String url = "jdbc:mysql://localhost:3306/test_db";
            String url = "jdbc:mysql://localhost:3306/test_db?serverTimezone=GMT%2B8&useSSL=false";
            String user = "root";
            String password = "123456";
            try {
                //创建数据库连接
                Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("连接数据成功！");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
