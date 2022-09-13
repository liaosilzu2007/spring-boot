package com.lzumetal.springboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaosi
 */
@Slf4j
public class HttpServletRequestUtil {




    public static void getRequestInfo(HttpServletRequest request) throws JsonProcessingException {

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            log.info("Header|{}:{}", headerName, headerValue);
        }

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            log.info("cookie|{}={}", cookie.getName(), cookie.getValue());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String value = objectMapper.writeValueAsString(entry.getValue());
            log.info("请求参数|{}:{}", key, value);
        }

    }
}
