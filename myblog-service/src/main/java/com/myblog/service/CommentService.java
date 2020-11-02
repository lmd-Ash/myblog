package com.myblog.service;

import com.myblog.model.Comment;
import com.myblog.model.User;

import java.util.List;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/31
 */
public interface CommentService {
    /**
     * 保存评论
     *
     * @param comment
     * @param user
     * @return
     */
    Integer saveComment(Comment comment, User user);

    /**
     * 分页查询评论
     *
     * @param comment
     * @param page     当前页
     * @param pageSize 每页数量
     * @return
     */
    List<Comment> pageAll(Comment comment, Integer page, Integer pageSize);

    /**
     * 计算总条数
     *
     * @param comment
     * @return
     */
    Long countAll(Comment comment);
}
