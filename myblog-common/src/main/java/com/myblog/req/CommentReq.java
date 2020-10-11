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
public class CommentReq extends BaseReq implements Serializable {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 评论类型
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer type;

    /**
     * 博客id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer blogId;

    /**
     * 评论人ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer createUserId;

    /**
     * 评论人名字
     */
    private String createUserName;

    /**
     * 评论时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 是否可用：1：是，0：否
     */
    private Boolean isUsable;
}