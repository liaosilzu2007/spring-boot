package com.lzumetal.springboot.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liaosi
 * @date 2020-06-13
 */
public class StringUtil {


    private static final Pattern MOBILE_PATTERN = Pattern.compile("^1[3456789]\\d{9}$");

    private static final String SPECIAL_CHARACTERS_PATTERN_STR = "[^\\u4e00-\\u9fa5_a-zA-Z0-9\\s·~！@#￥%…&*（）—\\-+=【】{}、|；‘’：“”《》？，。`!$^()\\[\\]\\\\;':\",./<>?]*";


    public static void main(String[] args) {
        String source = "-（鞋 ？子——\uD83D\uDC5F，四川内江市东兴区胜利街道汉安大道东\uD861\uDC82线红兴苑小区6栋2单元.";
        System.out.println(source);
        System.out.println(filterEmoji(source));
        System.out.println(filterSpecialCharacters(source));
    }

    /**
     * 过滤特殊字符
     *
     * @return
     */
    public static String filterSpecialCharacters(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        return source.replaceAll(SPECIAL_CHARACTERS_PATTERN_STR, "");
    }

    /**
     * 过滤特殊表情字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        if (source == null) {
            return source;
        }
        return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
    }


    /**
     * 是否是数值（可以包含小数点）
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        if (str.contains(".")) {
            str = str.replace(".", "");
        }
        return StringUtils.isNumeric(str);
    }


    /**
     * 是否是手机号
     *
     * @param str
     * @return
     */
    public static boolean isMobile(String str) {
        if (StringUtils.length(str) != 11) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(str);
        return m.matches();
    }


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


    /**
     * 去除所有空白符。包括' '、\n、\r、\t等
     *
     * @param source
     * @return
     */
    public static String removeBlank(String source) {
        return source.replaceAll("\\s", "");
    }
}
