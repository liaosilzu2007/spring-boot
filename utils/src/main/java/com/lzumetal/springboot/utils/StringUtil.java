package com.lzumetal.springboot.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liaosi
 * @date 2020-06-13
 */
public class StringUtil {

    /* 因为o和0,l和1很难区分,去掉大小写的o和小写l */
    private static final String SEED = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";


    private static final Pattern MOBILE_PATTERN = Pattern.compile("^1[3456789]\\d{9}$");

    private static final String SPECIAL_CHARACTERS_PATTERN_STR = "[^\\u4e00-\\u9fa5_a-zA-Z0-9\\s·~！@#￥%…&*（）—\\-+=【】{}、|；‘’：“”《》？，。`!$^()\\[\\]\\\\;':\",./<>?]*";


    public static void main(String[] args) {
        String source = "-（鞋 ？子——\uD83D\uDC5F，四川内江市东兴区胜利街道汉安大道东\uD861\uDC82线红兴苑小区6栋2单元.";
        System.out.println(source);
        System.out.println(filterEmoji(source));
        System.out.println(filterSpecialCharacters(source));
        System.out.println(randomString(20));

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
     * 判断是否是数值。
     * <p>
     * 和'isNumber'方法不同在于'1.2.1'这种格式的字符串此方法会返回false
     *
     * @param str
     * @return
     */
    public static boolean isStrictNumber(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        if (!str.contains(".")) {
            return StringUtils.isNumeric(str);
        }
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '.') {
                count++;
            }
        }
        if (count > 1) {
            return false;
        }
        return isNumber(str);
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


    public static String randomString(int bit) {
        if (bit <= 0) {
            bit = 6; // 默认6位
        }
        return RandomStringUtils.random(bit, SEED);// 返回6位的字符串
    }

    /**
     * 获取字符串中结尾的数字，比如字符串：a2b09，获得9这个数字
     *
     * @param source
     * @return
     */
    public static Integer getEndNumber(String source) {
        if (source == null) {
            return null;
        }
        Integer index = null;
        char[] chars = source.toCharArray();
        int i = chars.length - 1;
        while (i < chars.length) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                index = i;
            } else {
                break;
            }
            i--;
        }
        if (index != null) {
            return Integer.parseInt(source.substring(index));
        }
        return null;
    }

}
