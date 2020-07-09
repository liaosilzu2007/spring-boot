package com.lzumetal.springboot.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liaosi
 * @date 2020-07-09
 */
public class HttpUtil {



    /**
     * 获取https协议以及域名 eg. https://www.baidu.com/
     *
     * @param request
     * @return
     */
    public static String getHttpDomain(HttpServletRequest request) {
        StringBuffer fullUrl = request.getRequestURL();
        String tempContextUrl = fullUrl.delete(fullUrl.length() - request.getRequestURI().length(), fullUrl.length()).append("/").toString();
        return tempContextUrl;
    }


    /**
     * 获取http或者https eg. https://
     * @param request
     * @return
     */
    public static String getHttpType(HttpServletRequest request) {
        String httptype = request.getHeader("X-Forwarded-Proto");
        if (StringUtils.isEmpty(httptype)) {
            httptype = "http";
        }
        return httptype + "://";
    }


}
