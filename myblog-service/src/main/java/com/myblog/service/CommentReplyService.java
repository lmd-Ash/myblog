package com.myblog.service;

import com.myblog.model.CommentReply;
import com.myblog.model.User;

import java.util.List;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/31
 */
public interface CommentReplyService {
    /**
     * 保存评论回复
     *
     * @param commentReply
     * @param user
     * @return
     */
    Integer saveCommentReply(CommentReply commentReply, User user);

    /**
     * 分页查询评论回复
     *
     * @param commentReply
     * @param page         当前页
     * @param pageSize     每页数量
     * @return
     */
    List<CommentReply> pageAll(CommentReply commentReply, Integer page, Integer pageSize);

    /**
     * 计算总条数
     *
     * @param commentReply
     * @return
     */
    Long countAll(CommentReply commentReply);

    /**
     * 根据id查询评论回复
     *
     * @param id
     * @return
     */
    CommentReply findById(Integer id);

    /**
     * 删除评论回复
     *
     * @param commentReply
     * @param user
     * @return
     */
    Integer deleteCommentReply(CommentReply commentReply, User user);
}
