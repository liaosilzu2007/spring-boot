package com.lzumetal.springboot.utils.test;

import org.joda.time.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author liaosi
 * @date 2018-10-14
 */
public class JodaTimeTest {


    @Test
    public void testCalendar() {
        // 以 JDK 的方式向某一个瞬间加上 90 天并输出结果
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.add(Calendar.DAY_OF_MONTH, 90);
        System.out.println("jdk方式：" + sdf.format(calendar.getTime()));

        // 以 Joda 的方式向某一个瞬间加上 90 天并输出结果
        DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0);
        System.out.println("joda-time方式：" + dateTime.plusDays(90).toString("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testDateTime() {
        DateTime dateTime1 = new DateTime();
        System.out.println(dateTime1);
        DateTime dateTime2 = new DateTime(2018, 10, 14, 0, 0, 0);
        System.out.println(dateTime2);
        DateTime dateTime3 = new DateTime(1539514032003L);
        System.out.println(dateTime3);
        DateTime dateTime4 = new DateTime(new Date());
        System.out.println(dateTime4);
    }

    @Test
    public void testWith() {
        DateTime dateTime2000Year = new DateTime(2000,2,29,0,0,0);
        System.out.println(dateTime2000Year);
        DateTime dateTime1997Year =  dateTime2000Year.withYear(1997);
        System.out.println(dateTime1997Year);

        DateTime now = DateTime.now();
        System.out.println(now.toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now.withDayOfMonth(16).toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now.withDayOfWeek(3).toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now.withDayOfYear(10).toString("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testPlusAmdMuis() {
        DateTime dateTime = new DateTime(2018, 5, 31, 9, 0, 0);
        System.out.println(dateTime);
        DateTime tomorrow = dateTime.plusDays(1);
        System.out.println(tomorrow);
        DateTime lastMonthDay = dateTime.minusMonths(1);
        System.out.println(lastMonthDay);

        //5月31日加一个月返回的是6月30日
        DateTime nextMonthDay = dateTime.plusMonths(1);
        System.out.println(nextMonthDay);
    }

    @Test
    public void getNow() {
        DateTime now = DateTime.now();
        System.out.println(now);

        //获取西八区的当前时间
        DateTime now1 = DateTime.now(DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT-8:00")));
        System.out.println(now1);


    }

    @Test
    public void property() {
        DateTime now = DateTime.now();
        System.out.println(now);
        System.out.println(now.monthOfYear().getAsText());
        System.out.println(now.monthOfYear().getAsText(Locale.KOREAN));
        System.out.println(now.dayOfWeek().getAsShortText());
        System.out.println(now.dayOfWeek().getAsShortText(Locale.UK));

    }

    @Test
    public void roundFloorCopy() {
        DateTime now = new DateTime();
        System.out.println(now);
        System.out.println(now.dayOfWeek().roundCeilingCopy());
        System.out.println(now.dayOfWeek().roundFloorCopy());
        System.out.println(now.minuteOfDay().roundFloorCopy());
        System.out.println(now.secondOfMinute().roundFloorCopy());

    }

    @Test
    public void testLocal() {
        LocalDate localDate = new LocalDate();
        System.out.println(localDate);      //2018-10-15

        LocalTime localTime = new LocalTime();
        System.out.println(localTime);      //22:45:53.129
    }

    @Test
    public void testBetween() {
        DateTime now = new DateTime();
        DateTime dateTime = now.minusMonths(1);
        System.out.println(Hours.hoursBetween(dateTime, now).getHours());   //720

        LocalDate localDate = new LocalDate();
        LocalDate start = localDate.minusMonths(1);
        System.out.println(Days.daysBetween(start, localDate).getDays());    //30
    }

    @Test
    public void swithJdk() {
        Calendar c1 = Calendar.getInstance();
        DateTime dateTime = new DateTime(c1);
        System.out.println(dateTime);

        Calendar calendar = new DateTime().toCalendar(Locale.getDefault());
        System.out.println(calendar);
    }

    @Test
    public void testMaxAndMin() {
        DateTime now = DateTime.now();
        System.out.println(now.minuteOfHour().withMaximumValue());  //分钟数变成59分：2018-10-15T23:59:09.134+08:00

        System.out.println(now.secondOfMinute().withMinimumValue());//秒数变成00秒：2018-10-15T23:03:00.117+08:00

        //获取一天的0点
        System.out.println(now.withTimeAtStartOfDay());
        System.out.println(now.millisOfDay().withMinimumValue());

        //获取一天的23:59:59
        System.out.println(now.millisOfDay().withMaximumValue());
    }
}
