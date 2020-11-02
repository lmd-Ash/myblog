package com.myblog.utils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/31
 * 去重List<对象>
 */
public class ListObjectNoRepeatUtils {
    /**
     * 去重字符串重复
     *
     * @param stringList 字符串List
     * @return 返回去重字符串
     */
    public static List<String> removeStringListDuplicate(List<String> stringList) {
        Set<String> set = new LinkedHashSet<>();
        set.addAll(stringList);
        stringList.clear();
        stringList.addAll(set);
        return stringList;
    }

    /**
     * 根据对象属性去重  属性：userId
     *
     * @param persons
     * @return
     */
    public static List<BaseInfo> removeDuplicateByIdOld(List<BaseInfo> persons) {
        Set<BaseInfo> personSet = new TreeSet<>((o1, o2) -> o1.getId().compareTo(o2.getId()));
        personSet.addAll(persons);
        return new ArrayList<>(personSet);
    }

    /**
     * 根据对象多个属性去重 属性：id + name
     *
     * @param persons
     * @return
     */
    public static List<BaseInfo> removeDuplicateByMorePro(List<BaseInfo> persons) {
        List<BaseInfo> personList = persons.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(o -> {
            // 根据useId和userName进行去重
            return o.getId() + "," + o.getName();
        }))), ArrayList::new));
        return personList;
    }

    /**
     * 根据对象单个属性去重 属性：userId
     *
     * @param persons
     * @return
     */
    public static List<BaseInfo> removeDuplicateById(List<BaseInfo> persons) {
        List<BaseInfo> unique = persons.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparingLong(BaseInfo::getId))), ArrayList::new)
        );
        return unique;
    }

    /**
     * 根据对象单个属性去重 属性：userId
     *
     * @param persons
     * @return
     */
    public static List<BaseInfo> removeDuplicateByIdNew(List<BaseInfo> persons) {

        List<BaseInfo> personList = new ArrayList<>();
        persons.stream().filter(distinctByKey(p -> p.getId()))
                .forEach(p -> personList.add(p));
        return personList;
    }

    /**
     * 根据key去重重复
     *
     * @param keyExtractor key执行器
     * @param <T>          泛型
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    static class BaseInfo {
        private Long id;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
