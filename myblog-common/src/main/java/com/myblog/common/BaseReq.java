package com.myblog.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 基础查询类
 *
 * @author lmd
 * @version 1.0
 * @date 2019-09-23 23:25
 */
@Data
public class BaseReq implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 后台用户Id
     */
    private Integer userId;

    /**
     * 前台用户Id
     */
    private Integer buyerId;

    /**
     * 是否可用 0：否，1：是
     */
    private Boolean isAvailable;

    /**
     * 创建人
     */
    private String createUserName;

    /**
     * 创建人Id
     */
    private Integer createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUserName;

    /**
     * 修改人Id
     */
    private Integer updateUserId;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 当前页
     */
    private Integer page = 1;

    public Integer getPage() {
        return Objects.isNull(page) ? 0 : page < 1 ? 0 : page - 1;
    }

    /**
     * 每页条数
     */
    private Integer pageSize = 10;

    /**
     * 排序条件
     */
    private String clause;
}
