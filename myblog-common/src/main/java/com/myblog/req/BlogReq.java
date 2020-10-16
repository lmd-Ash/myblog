package com.myblog.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.myblog.common.BaseReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lmd
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogReq extends BaseReq implements Serializable {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer createUserId;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 修改人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer updateUserId;

    /**
     * 博客被观看次数
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer seeNum;

    /**
     * 博客被评论量
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer commentNum;

    /**
     * 博客被点赞数
     */
    @JsonSerialize(using = ToStringSerializer.class)
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