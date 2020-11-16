package com.myblog.mapper;

import com.myblog.model.CommentReply;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmd
 */
@Repository
public interface CommentReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentReply record);

    CommentReply selectByPrimaryKey(Integer id);

    List<CommentReply> selectAll();

    int updateByPrimaryKey(CommentReply record);

    /**
     * 分页查询评论回复
     *
     * @param commentReply
     * @return
     */
    List<CommentReply> pageAll(CommentReply commentReply);

    /**
     * 计算总条数
     *
     * @param commentReply
     * @return
     */
    Long countAll(CommentReply commentReply);

    /**
     * 删除评论回复
     *
     * @param commentReply
     * @return
     */
    Integer deleteCommentReply(CommentReply commentReply);
}