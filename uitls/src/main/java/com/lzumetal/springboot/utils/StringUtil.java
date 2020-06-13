package com.lzumetal.springboot.utils;

/**
 * @author liaosi
 * @date 2020-06-13
 */
public class StringUtil {


    /**
     * 手机号脱敏处理。13800138000 ----> 138****8000
     *
     * @param mobile
     * @return
     */
    public static String privaryMobile(String mobile) {
        if (mobile == null) {
            return null;
        }
        char[] chars = mobile.trim().toCharArray();
        StringBuilder sb = new StringBuilder();
        final char hide = '*';
        int length = chars.length;
        for (int n = 0; n < length; n++) {
            char c = chars[n];
            if (length > 7 && n > 2 && n < length - 4) {
                c = hide;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
