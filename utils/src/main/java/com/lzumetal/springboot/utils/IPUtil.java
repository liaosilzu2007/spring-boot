package com.lzumetal.springboot.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liaosi
 * @date 2020-07-09
 */
public class IPUtil {

    public static void main(String[] args) {
        System.out.println(isInnerIp("172.21.19.40"));
    }

    /**
     * 通过request 获得用户的真实的Ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.length() != 0) {
            if (ip.contains(",")) {
                String[] array = ip.split(",");
                for (int i = 0; i < array.length; i++) {
                    if (array[i] != null && !("unknown".equalsIgnoreCase(array[i]))) {
                        ip = array[i];
                        break;
                    }
                }
            }
        }
        if (ip == null) {
            ip = "";
        }
        return ip.trim();
    }

    /**
     * 是否是内网IP
     * <p>
     * IPV4网络中内网ip网段一般为：
     * 10.0.0.0 - 10.255.255.255
     * 172.16.0.0 - 172.31.255.255
     * 192.168.0.0 - 192.168.255.255
     *
     * @param ipAddress
     * @return
     */
    public static boolean isInnerIp(String ipAddress) {
        if (Objects.equals("0:0:0:0:0:0:0:1", ipAddress) || Objects.equals("127:0:0:1", ipAddress)) {
            return true;
        }
        //匹配10.0.0.0 - 10.255.255.255的网段
        String pattern_10 = "^(\\D)*10(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){3}";

        //匹配172.16.0.0 - 172.31.255.255的网段
        String pattern_172 = "172\\.([1][6-9]|[2]\\d|3[01])(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){2}";

        //匹配192.168.0.0 - 192.168.255.255的网段
        String pattern_192 = "192\\.168(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){2}";

        //合起来写
        String pattern = "((192\\.168|172\\.([1][6-9]|[2]\\d|3[01]))"
                + "(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){2}|"
                + "^(\\D)*10(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){3})";

        Pattern reg = Pattern.compile(pattern);
        Matcher match = reg.matcher(ipAddress);

        return match.find();
    }



}
