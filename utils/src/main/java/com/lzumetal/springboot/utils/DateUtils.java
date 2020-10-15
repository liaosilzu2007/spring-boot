package com.lzumetal.springboot.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @author liaosi
 * @date 2020-10-15
 */
public class DateUtils {

    public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormat.forPattern(DEFAULT_DATE_FORMAT);

    public static final DateTimeFormatter DATE_TIME_FORMAT_FORMATER = DateTimeFormat.forPattern(DEFAULT_DATE_TIME_FORMAT);


    public static String format(Date date, String pattern) {
        return new DateTime(date).toString(pattern);
    }

    public static DateTime parseToDateTime(String dateStr, String pattern) {
        return DateTime.parse(dateStr, DateTimeFormat.forPattern(pattern));
    }

    public static Date parseToDate(String dateStr, String pattern) {
        return parseToDateTime(dateStr, pattern).toDate();
    }


}
