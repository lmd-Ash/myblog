package com.myblog.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 类转换
 *
 * @author Zhenfeng Li
 * @version 1.0.0
 * @date 2019-09-19 09:02:33
 */
@Slf4j
@Lazy
public class BeanMapper {
    private static Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

    /**
     * 转对象
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        if (Objects.isNull(source)) {
            return null;
        }
        return mapper.map(source, destinationClass);
    }

    /**
     * 转对象
     */
    public static void map(Object source, Object destination) {
        mapper.map(source, destination);
    }

    /**
     * 转List
     */
    public static <T> List<T> mapList(List<?> list, Class<T> destinationClass) {
        List<T> arrayList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return arrayList;
        }
        for (Object obj : list) {
            arrayList.add(mapper.map(obj, destinationClass));
        }
        return arrayList;
    }
}