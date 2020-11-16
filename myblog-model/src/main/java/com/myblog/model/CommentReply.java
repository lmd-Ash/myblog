package com.myblog.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论回复表
 *
 * @author lmd
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class CommentReply implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 回复人ID
     */
    private Integer createUserId;

    /**
     * 回复人名字
     */
    private String createUserName;

    /**
     * 回复时间
     */
    private Date createTime;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 被回复人id
     */
    private Integer replyUserId;

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