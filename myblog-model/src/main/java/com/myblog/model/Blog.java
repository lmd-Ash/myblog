package com.myblog.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lmd
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class Blog implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 博客标题
     */
    private String blogTitle;

    /**
     * 博客分类
     */
    private String blogType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 博客被观看次数
     */
    private Integer seeNum;

    /**
     * 博客被评论量
     */
    private Integer commentNum;

    /**
     * 博客被点赞数
     */
    private Integer praiseNum;

    /**
     * 博客关键词
     */
    private String blogKeyword;

    /**
     * 博客简介摘要
     */
    private String blogAbstract;

    /**
     * 是否可用：1：是，0：否
     */
    private Boolean isUsable;

}