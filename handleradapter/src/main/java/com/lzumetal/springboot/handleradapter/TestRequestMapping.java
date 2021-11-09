package com.lzumetal.springboot.handleradapter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liaosi
 * @date 2021-11-09
 */
@Controller
public class TestRequestMapping {

    @RequestMapping("/testRequestMapping")
    public void test(HttpServletResponse response) {
        try {
            response.getWriter().write("Response from TestHttpRequestHandler");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
