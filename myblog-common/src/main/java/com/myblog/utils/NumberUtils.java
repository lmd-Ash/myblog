package com.myblog.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 数字判断工具类
 *
 * @author lmd
 * @version 1.0.0
 * @date 2020/11/25
 */
public class NumberUtils {
    private static final Pattern PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");
    private static final String DECIMAL_POINT = ".";
    private static final String SPLITS = "\\.";
    private static final Pattern NUMBER = Pattern.compile("^([0-9][0-9]*)+(\\.[0-9]{1,2})?$");
//    ^(([1-9]{1}\d*)|(0{1}))(\.\d{1,2})?$

    /**
     * 判断string是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return PATTERN.matcher(str).matches();
    }

    /**
     * 判断是否是小数
     *
     * @param str
     * @return
     */
    public static boolean isNumberAndPoint(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return NUMBER.matcher(str).matches();
    }

    /**
     * 判断string是否是数字,小数
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        //判断是否有小数点
        if (str.indexOf(DECIMAL_POINT) > 0) {
            //判断是否只有一个小数点
            if (str.indexOf(DECIMAL_POINT) == str.lastIndexOf(DECIMAL_POINT) && str.split(SPLITS).length == 2) {
                return isInteger(str);
            } else {
                return false;
            }
        }
        return isInteger(str);
    }

}
