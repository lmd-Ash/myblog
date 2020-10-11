package com.myblog.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lmd
 */
@Data
public class CommentResp implements Serializable {
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