package com.myblog.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
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
     * 每天重新开始增长
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static final String DATE_STRING = DATE_FORMAT.format(new Date());

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
