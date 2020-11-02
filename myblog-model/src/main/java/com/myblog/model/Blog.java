package com.myblog.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客表
 *
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
    private Integer blogType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createUserId;

    /**
     * 创建人
     */
    private String createUserName;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Integer updateUserId;

    /**
     * 修改人
     */
    private String updateUserName;

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

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页条数
     */
    private Integer pageSize;
}