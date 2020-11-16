package com.myblog.mapper;

import com.myblog.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmd
 */
@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    /**
     * 分页查询评论
     *
     * @param comment
     * @return
     */
    List<Comment> pageAll(Comment comment);

    /**
     * 计算总条数
     *
     * @param comment
     * @return
     */
    Long countAll(Comment comment);

    /**
     * 删除评论
     *
     * @param comment
     * @return
     */
    Integer deleteComment(Comment comment);
}