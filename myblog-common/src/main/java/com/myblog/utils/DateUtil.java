package com.myblog.utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lmd
 * @date 2020/4/26
 * 日期处理工具类
 */
public class DateUtil {
    /**
     * 日期类型 yyyy-MM-dd
     */
    public final static String DATE_TYPE = "yyyy-MM-dd";
    /**
     * 日期时间类型 yyyy-MM-dd HH:mm:ss
     */
    public final static String DATE_TIME_TYPE = "yyyy-MM-dd HH:mm:ss";

    /**
     * 按照yyyy-MM-dd格式转换日期
     *
     * @param date 日期
     * @return
     */
    public static final String formatDate(Object date) {
        if (date == null) {
            return null;
        } else {
            return formatDate(date, DATE_TIME_TYPE);
        }
    }

    /**
     * 将时间转换为字符串（xx天，xx时，xx分，xx秒，大于360天显示日期时间）
     */
    public static String formatDateAgo(long millisecond) {
        StringBuilder sb = new StringBuilder();
        if (millisecond < 1000) {
            sb.append(millisecond).append("毫秒");
        } else {
            Integer ss = 1000;
            Integer mi = ss * 60;
            Integer hh = mi * 60;
            Integer dd = hh * 24;

            Long day = millisecond / dd;
            Long hour = (millisecond - day * dd) / hh;
            Long minute = (millisecond - day * dd - hour * hh) / mi;
            Long second = (millisecond - day * dd - hour * hh - minute * mi) / ss;
            if (day > 365) {
                return DateUtil.formatDate(new Date(millisecond), "yyyy年MM月dd日 HH时mm分ss秒");
            }
            if (day > 0) {
                sb.append(day).append("天");
            }
            if (hour > 0) {
                sb.append(hour).append("小时");
            }
            if (minute > 0) {
                sb.append(minute).append("分钟");
            }
            if (second > 0) {
                sb.append(second).append("秒");
            }
        }
        return sb.toString();
    }


    /**
     * 按照指定格式转换日期
     *
     * @param date       日期
     * @param formatType 格式化类型
     * @return
     */
    public static final String formatDate(Object date, String formatType) {
        if (date == null) {
            return null;
        } else {
            if (StringUtil.isNotEmpty(formatType)) {
                SimpleDateFormat format = new SimpleDateFormat(formatType);
                return format.format(date);
            } else {
                return formatDate(date);
            }
        }
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static final String getDateNow() {
        return formatDate(new Date());
    }

    /**
     * 获取当前年度
     *
     * @return
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }


    /**
     * 判断当前时间在时间区间内
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return true在范围内，false不在范围内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 线程安全的string转date
     *
     * @param timeString 时间字符串
     * @return date类型时间
     */
    public static Date stringToDate(String timeString) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_TIME_TYPE);
        LocalDateTime time = LocalDateTime.parse(timeString, fmt);
        return localDateTimeToDate(time);
    }

    /**
     * 线程安全的date转string
     *
     * @param date 时间
     * @return string 字符串
     */
    public static String dateToString(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_TIME_TYPE);
        return localDateTime.format(fmt);
    }

    /**
     * 获取几天后的当前时间
     *
     * @param days 为正数则往后推，为负数则往前推
     * @return date类型时间
     */
    public static Date afterToNow(Integer days) {
        // 获取LocalDateTime类，LocalDate.now()当前年月日
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        // 为正数则往后推，为负数则往前推
        localDateTime = localDateTime.plusDays(days);
        // 获取时间
        return localDateTimeToDate(localDateTime);
    }

    /**
     * 获取几天后的指定时间
     *
     * @param date 传入的时间
     * @param days 为正数则往后推，为负数则往前推
     * @return date类型时间
     */
    public static Date afterToDays(Date date, Integer days) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        // 为正数则往后推，为负数则往前推
        localDateTime = localDateTime.plusDays(days);
        // 获取时间
        return localDateTimeToDate(localDateTime);
    }

    /**
     * ZoneId: 时区ID，用来确定Instant和LocalDateTime互相转换的规则
     * Instant: 用来表示时间线上的一个点（瞬时）
     * LocalDate: 表示没有时区的日期, LocalDate是不可变并且线程安全的
     * LocalTime: 表示没有时区的时间, LocalTime是不可变并且线程安全的
     * LocalDateTime: 表示没有时区的日期时间, LocalDateTime是不可变并且线程安全的
     */
    public static Date localDateTimeToDate(LocalDateTime time) {
        ZoneId zoneId = ZoneId.systemDefault();
        // LocalDateTime转Date
        Instant instant = time.atZone(zoneId).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取几小时后的指定时间
     *
     * @param date  传入的时间
     * @param hours 为正数则往后推，为负数则往前推
     * @return date类型时间
     */
    public static Date afterToHours(Date date, Integer hours) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        // 当前时间往前推 hours 个小时
        localDateTime = LocalDateTime.from(localDateTime.plusHours(hours)
                .toLocalDate()
                .atTime(localDateTime.plusHours(hours).getHour(), 0, 0));
        // 获取时间
        return localDateTimeToDate(localDateTime);
    }

    /**
     * Date转LocalDateTime
     *
     * @param date 传入的Date
     * @return 返回的LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 获取当月第一天
     *
     * @return 返回当月月初Date
     */
    public static Date firstToNowMonth() {
        // 获取当月第一天的localDate
        LocalDate firstMonthLocalDate = LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()));
        // 获取零点
        LocalDateTime localDateTime = LocalDateTime.of(firstMonthLocalDate, LocalTime.MIN);
        return localDateTimeToDate(localDateTime);
    }

    /**
     * 获取给定时间的月初
     *
     * @param date 给定时间Date
     * @return 返回给定时间月初Date
     */
    public static Date firstMounthDayToTime(Date date) {
        // Date转成LocalDateTime
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        // 获取给定时间月初的LocalDate
        LocalDate firstMonthLocalDate = LocalDate.from(localDateTime.with(TemporalAdjusters.firstDayOfMonth()));
        // 获取零点
        localDateTime = LocalDateTime.of(firstMonthLocalDate, LocalTime.MIN);
        return localDateTimeToDate(localDateTime);
    }

    /**
     * 获取当月最后一天
     *
     * @return 返回当月月末Date
     */
    public static Date lastToNowMounth() {
        // 获取当月最后一天的localDate
        LocalDate firstMonthLocalDate = LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth()));
        // 获取23:59:59
        LocalDateTime localDateTime = LocalDateTime.of(firstMonthLocalDate, LocalTime.MAX);
        return localDateTimeToDate(localDateTime);
    }
}
