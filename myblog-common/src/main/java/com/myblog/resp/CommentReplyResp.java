package com.myblog.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CommentReplyResp implements Serializable {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 评论id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer commentId;

    /**
     * 回复人ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer createUserId;

    /**
     * 回复人名字
     */
    private String createUserName;

    /**
     * 回复时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 被回复人id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer replyUserId;

    /**
     * 是否可用：1：是，0：否
     */
    private Boolean isUsable;
}