package com.lzumetal.springboot.http.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzumetal.springboot.http.entity.Employee;
import com.lzumetal.springboot.http.entity.RequestInfo;
import com.lzumetal.springboot.utils.JsonUtils;
import com.lzumetal.springboot.utils.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaosi
 * @date 2020-08-08
 */
@RestController
@RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
@Slf4j
public class TestController {

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/testHttpRequest")
    public ResponseData testHttpRequest(HttpServletRequest request) throws JsonProcessingException {
        RequestInfo requestInfo = new RequestInfo();

        Enumeration<String> headerNames = request.getHeaderNames();
        HashMap<String, String> headerMap = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headerMap.put(headerName, headerValue);
            log.info("Header|{}:{}", headerName, headerValue);
        }
        requestInfo.setHeaderMap(headerMap);

        HashMap<String, String> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie.getValue());
            log.info("cookie|{}={}", cookie.getName(), cookie.getValue());
        }
        requestInfo.setCookieMap(cookieMap);

        HashMap<String, String> paramMap = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String value = objectMapper.writeValueAsString(entry.getValue());
            paramMap.put(key, value);
            log.info("请求参数|{}:{}", key, value);
        }
        requestInfo.setParamMap(paramMap);

        //return ResponseData.data(requestInfo);
        return ResponseData.success();
    }


    @RequestMapping("/addEmployee")
    public ResponseData add(@RequestParam String employeeNum, @RequestParam String employeeName) {
        log.info("addEmployee|employeeNum={},employeeName={}", employeeNum, employeeName);
        return ResponseData.success();
    }

    @RequestMapping("/addEmployeeJson")
    public ResponseData addJson(@RequestBody Employee employee) {
        log.info("addEmployeeJson|employee={}", JsonUtils.toJson(employee));
        return ResponseData.success();
    }


    @RequestMapping(value = "/testUpload")
    public ResponseData testUpload(@RequestParam("file") MultipartFile imageFile,
                                   @RequestParam("userid") Long userid) {
        log.info("测试图片上传|PARAM|userid={},fileName={}", userid, imageFile.getOriginalFilename());
        return ResponseData.success();
    }



}
