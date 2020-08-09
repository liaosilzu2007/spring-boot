package com.lzumetal.springboot.http.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzumetal.springboot.utils.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author liaosi
 * @date 2020-08-08
 */
@RestController
@RequestMapping(value = "/httpclient", method = {RequestMethod.GET, RequestMethod.POST})
@Slf4j
public class TestController {

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/testHttpRequest")
    public ResponseData testHttpRequest(HttpServletRequest request) throws JsonProcessingException {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            log.info("Header|{}:  {}", headerName, headerValue);
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            log.info("cookie|{}={}", cookie.getName(), cookie.getValue());
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            log.info("请求参数|{}:{}", entry.getKey(), objectMapper.writeValueAsString(entry.getValue()));
        }
        return ResponseData.data("");
    }

}
