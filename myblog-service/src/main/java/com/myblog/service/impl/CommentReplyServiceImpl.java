package com.myblog.service.impl;

import com.myblog.mapper.CommentReplyMapper;
import com.myblog.model.CommentReply;
import com.myblog.model.User;
import com.myblog.service.CommentReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/11/15
 */
@Service
public class CommentReplyServiceImpl implements CommentReplyService {
    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public Integer saveCommentReply(CommentReply commentReply, User user) {
        commentReply.setIsUsable(true);
        commentReply.setCreateTime(new Date());
        commentReply.setCreateUserId(user.getId());
        commentReply.setCreateUserName(user.getUserName());
        int insert = commentReplyMapper.insert(commentReply);
        return insert;
    }

    @Override
    public List<CommentReply> pageAll(CommentReply commentReply, Integer page, Integer pageSize) {
        commentReply.setPage(page * pageSize);
        commentReply.setPageSize((page + 1) * pageSize);
        List<CommentReply> commentReplies = commentReplyMapper.pageAll(commentReply);
        return commentReplies;
    }

    @Override
    public Long countAll(CommentReply commentReply) {
        Long countAll = commentReplyMapper.countAll(commentReply);
        return countAll;
    }

    @Override
    public CommentReply findById(Integer id) {
        CommentReply commentReply = commentReplyMapper.selectByPrimaryKey(id);
        return commentReply;
    }

    @Override
    public Integer deleteCommentReply(CommentReply commentReply, User user) {
        commentReply.setCreateTime(new Date());
        commentReply.setCreateUserId(user.getId());
        commentReply.setCreateUserName(user.getUserName());
        Integer delete = commentReplyMapper.deleteCommentReply(commentReply);
        return delete;
    }
}
