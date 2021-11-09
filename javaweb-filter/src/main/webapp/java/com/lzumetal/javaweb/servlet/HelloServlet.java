package com.lzumetal.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liaosi
 * @date 2021-02-03
 */
@WebServlet("/helloServlet")    //在servlet3.0以后，web.xml中对Servlet配置，同样可以在@WebServlet注解中配置。
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("执行HelloServlet的目标方法");
    }

}
