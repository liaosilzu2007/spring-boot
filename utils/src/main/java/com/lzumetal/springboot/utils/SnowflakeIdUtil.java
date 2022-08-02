package com.lzumetal.springboot.utils;

import cn.hutool.core.lang.Snowflake;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.Inet4Address;

/**
 * @author liaosi
 */
public class SnowflakeIdUtil {

    public static void main(String[] args) {
//        Set<Long> set = new HashSet<>();
//        for (int i = 0; i < 1000000; i++) {
//            set.add(SnowflakeIdUtil.nextId());
//        }
//        System.out.println("============>" + set.size());[50,49,51,52,50,51,49,52]
        System.out.println(JsonUtils.toJson(StringUtils.toCodePoints("21342313ab")));

    }


    private SnowflakeIdUtil() {
    }

    private static class SnowflakeHolder {
        static Snowflake instance = new Snowflake(getWorkId(), getDateCenterId());
    }

    private static Snowflake getSnowflakeInstance() {
        return SnowflakeHolder.instance;
    }


    public static synchronized long nextId() {
        return getSnowflakeInstance().nextId();
    }


    private static long getWorkId() {
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for (int i : ints) {
                sums = sums + i;
            }
            return sums % 32;
        } catch (Exception e) {
            //失败就随机一个数
            return RandomUtils.nextLong(0, 31);
        }
    }


    private static long getDateCenterId() {
        try {
            String hostName = Inet4Address.getLocalHost().getHostName();
            int[] ints = StringUtils.toCodePoints(hostName);
            int sums = 0;
            for (int i : ints) {
                sums = sums + i;
            }
            return sums % 32;
        } catch (Exception e) {
            //失败就随机一个数
            return RandomUtils.nextLong(0, 31);
        }
    }


}
