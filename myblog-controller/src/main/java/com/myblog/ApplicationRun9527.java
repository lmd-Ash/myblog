package com.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/11
 */
@SpringBootApplication
@MapperScan("com.myblog.mapper")
public class ApplicationRun9527 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun9527.class, args);
    }
}
