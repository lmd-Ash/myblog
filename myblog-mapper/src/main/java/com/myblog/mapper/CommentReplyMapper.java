package com.myblog.mapper;

import com.myblog.model.CommentReply;

import java.util.List;

/**
 * @author lmd
 */
public interface CommentReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentReply record);

    CommentReply selectByPrimaryKey(Integer id);

    List<CommentReply> selectAll();

    int updateByPrimaryKey(CommentReply record);
}