package com.myblog.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表
 *
 * @author lmd
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class Comment implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 评论类型
     */
    private Integer type;

    /**
     * 博客id
     */
    private Integer blogId;

    /**
     * 评论人ID
     */
    private Integer createUserId;

    /**
     * 评论人名字
     */
    private String createUserName;

    /**
     * 评论时间
     */
    private Date createTime;

    /**
     * 评论内容
     */
    private String content;

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