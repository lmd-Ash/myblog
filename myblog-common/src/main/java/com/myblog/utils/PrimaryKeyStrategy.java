package com.myblog.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/**
 * 主键生成策略
 *
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/11
 */
@Slf4j
public class PrimaryKeyStrategy {
    /**
     * ZoneId: 时区ID，用来确定Instant和LocalDateTime互相转换的规则
     * Instant: 用来表示时间线上的一个点（瞬时）
     * LocalDate: 表示没有时区的日期（年月日）, LocalDate是不可变并且线程安全的
     * LocalTime: 表示没有时区的时间（时分秒）, LocalTime是不可变并且线程安全的
     * LocalDateTime: 表示没有时区的日期时间, LocalDateTime是不可变并且线程安全的
     */
    LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
    ZoneId zoneId = ZoneId.systemDefault();
    Instant instant = localDateTime.atZone(zoneId).toInstant();
    Date date = Date.from(instant);

    /**
     * 获取当前时间加{num}天的时间
     * num可为负数
     */
    static {
        //获取LocalDateTime类，LocalDate.now()当前年月日，LocalTime.MIN获取时间的最小值
        LocalDateTime localDateTimeMin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        //往前推15天
        localDateTimeMin = localDateTimeMin.plusDays(-16);
        //LocalDateTime转Date
        ZoneId zoneMin = ZoneId.systemDefault();
        Instant instantMin = localDateTimeMin.atZone(zoneMin).toInstant();
        //获取开始时间
        Date startTime = Date.from(instantMin);
    }


    //不推荐使用，因为线程不安全
//    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    /**
     * 每天重新开始增长
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    /**
     * 获取当前日期时间 年月日
     */
    private static final LocalDate LOCAL_DATE = LocalDate.now();
    private static final String DATE_STRING = LOCAL_DATE.format(FORMATTER);
    ;

    /**
     * 生成int类型主键
     */
    public static Integer getId() {
        Random random = new Random();
        int nextInt1 = random.nextInt(9);
        int nextInt2 = random.nextInt(9);
        int id = Integer.parseInt(DATE_STRING + nextInt1 + nextInt2);
        log.info("Id自增序号为：[{}]", id);
        return id;
    }

    /**
     * 生成long类型主键
     */
    public static Long getBigId() {
        Random random = new Random();
        int nextInt1 = random.nextInt(9);
        int nextInt2 = random.nextInt(9);
        int nextInt3 = random.nextInt(9);
        long id = Long.parseLong(DATE_STRING + nextInt1 + nextInt2 + nextInt3);
        log.info("Id自增序号为：[{}]", id);
        return id;
    }
}
