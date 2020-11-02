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
     * 保存博客
     *
     * @param blog
     * @param user
     * @return
     */
    Integer saveBlog(Blog blog, User user);

    /**
     * 分页查询博客
     *
     * @param blog
     * @param page     当前页
     * @param pageSize 每页数量
     * @return
     */
    List<Blog> pageAll(Blog blog, Integer page, Integer pageSize);

    /**
     * 根据id查询博客
     *
     * @param id
     * @return
     */
    Blog findById(Integer id);

    /**
     * 计算总条数
     *
     * @param blog
     * @return
     */
    Long countAll(Blog blog);

    /**
     * 查询所有博客
     *
     * @return
     */
    List<Blog> selectAll();

    /**
     * 对该博客点赞
     *
     * @param blog
     * @return
     */
    Boolean likeBlog(Blog blog);

    /**
     * 删除博客
     *
     * @param blog
     * @param user
     * @return
     */
    Integer deleteBlog(Blog blog, User user);
}
