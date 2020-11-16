package com.myblog.service.impl;

import com.myblog.mapper.CommentMapper;
import com.myblog.model.Comment;
import com.myblog.model.User;
import com.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/31
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Integer saveComment(Comment comment, User user) {
        comment.setIsUsable(true);
        comment.setCreateTime(new Date());
        comment.setCreateUserId(user.getId());
        comment.setCreateUserName(user.getUserName());
        int insert = commentMapper.insert(comment);
        return insert;
    }

    @Override
    public List<Comment> pageAll(Comment comment, Integer page, Integer pageSize) {
        comment.setPage(page * pageSize);
        comment.setPageSize((page + 1) * pageSize);
        List<Comment> comments = commentMapper.pageAll(comment);
        return comments;
    }

    @Override
    public Long countAll(Comment comment) {
        Long countAll = commentMapper.countAll(comment);
        return countAll;
    }

    @Override
    public Comment findById(Integer id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        return comment;
    }

    @Override
    public Integer deleteComment(Comment comment, User user) {
        comment.setCreateTime(new Date());
        comment.setCreateUserId(user.getId());
        comment.setCreateUserName(user.getUserName());
        Integer deleteComment = commentMapper.deleteComment(comment);
        return deleteComment;
    }
}
