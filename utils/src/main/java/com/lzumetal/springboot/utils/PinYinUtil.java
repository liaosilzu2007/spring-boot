package com.lzumetal.springboot.utils;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 汉语拼音工具类
 *
 * @author liaosi
 * @date 2020-10-15
 */
@Slf4j
public class PinYinUtil {

    public static void main(String[] args) {
        String source = "张三 haha";
        for (char c : source.toCharArray()) {
            System.out.println(c);
        }
        System.err.println(toPinyinSplit(source));
    }


    /**
     * 将汉字隔开并转为拼音，每个汉字的拼音首字母大写。比如："张三 haha"——>"Zhang San  haha"
     * 非汉字字符不做处理
     *
     * @param chinese
     * @return
     */
    public static String toPinyinSplit(String chinese) {
        StringBuilder pinyinStr = new StringBuilder();
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        try {
            for (char c : newChar) {
                if (c <= 128) {
                    pinyinStr.append(c);
                    continue;
                }
                String[] arr = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat);
                if (arr == null) {
                    continue;
                }
                String word = arr[0];
                if (word == null) {
                    pinyinStr.append(c);
                    continue;
                }
                for (int i = 0; i < word.toCharArray().length; i++) {
                    if (i == 0) {
                        pinyinStr.append(String.valueOf(word.charAt(i)).toUpperCase());
                    } else {
                        pinyinStr.append(word.charAt(i));
                    }
                }
                pinyinStr.append(" ");
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("toPinyinSplit|汉字转化为拼音异常|{}", chinese, e);
            throw new RuntimeException("汉字转化为拼音异常");
        }
        return pinyinStr.toString();
    }


    /**
     * 汉字转为全小写拼音。比如："国际订单 haha"——>"guojidingdan haha"
     * 非汉字字符不做处理
     *
     * @param chinese
     * @return
     */
    public static String toPinyin(String chinese) {
        StringBuilder pinyinStr = new StringBuilder();
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        try {
            for (char c : newChar) {
                if (c <= 128) {
                    pinyinStr.append(c);
                    continue;
                }
                String word = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat)[0];
                if (word == null) {
                    pinyinStr.append(c);
                    continue;
                }
                pinyinStr.append(word);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("toPinyin|汉字转化为拼音异常|{}", chinese, e);
            throw new RuntimeException("汉字转化为拼音异常");
        }
        return pinyinStr.toString();
    }


    /**
     * 对中文名称按照拼音的字母表排序
     */
    @Test
    public void sort() {
        List<String> list = new ArrayList<>();
        list.add("刘洋");
        list.add("冯利");
        list.add("朱凡");
        list.add("刘萌");
        //使用Collator进行本地化不敏感的字符串比较
        Collator collator = Collator.getInstance(Locale.CHINA);
        //设置为按首字母排序
        collator.setStrength(Collator.PRIMARY);
        list = list.stream().sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return collator.getCollationKey(o1).compareTo(collator.getCollationKey(o2));
                    }
                })
                .collect(Collectors.toList());
        System.out.println(JsonUtils.toJson(list));
    }
    
}
