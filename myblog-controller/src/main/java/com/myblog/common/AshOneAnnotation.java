package com.myblog.common;

import java.lang.annotation.*;

/**
 * 自定义的注解
 * @author lmd
 * @version 1.0.0
 * @date 2020/12/5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AshOneAnnotation {
}
