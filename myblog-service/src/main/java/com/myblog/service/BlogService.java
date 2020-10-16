package com.myblog.service;

import com.myblog.model.Blog;
import com.myblog.model.User;

import java.util.List;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/12
 */
public interface BlogService {
    /**
     * 保存/修改 博客
     *
     * @param blog
     * @param user
     * @return
     */
    Integer save(Blog blog, User user);

    /**
     * 根据博客关键词查询博客
     *
     * @param blogKeyword
     * @return
     */
    Blog findByBlogKeyword(String blogKeyword);

    /**
     * 根据博客分类查询博客
     *
     * @param blogType
     * @return
     */
    Blog findByBlogType(String blogType);

    /**
     * 根据博客标题查询博客
     *
     * @param blogTitle
     * @return
     */
    Blog findByBlogTitle(String blogTitle);

    /**
     * 查询所有博客
     *
     * @return
     */
    List<Blog> selectAll();

    /**
     * 对该博客点赞
     *
     * @param blogId
     * @return
     */
    Boolean
    likeBlog(Integer blogId);

    /**
     * 删除博客
     *
     * @param id
     * @return
     */
    Integer delete(Integer id);
}
