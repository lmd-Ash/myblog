package com.myblog.mapper;

import com.myblog.model.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmd
 */
@Repository
public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    Blog selectByPrimaryKey(Integer id);

    List<Blog> selectAll();

    int updateByPrimaryKey(Blog record);

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
     * 计算总条数
     *
     * @param blog
     * @return
     */
    Long countAll(Blog blog);

    /**
     * 根据博客关键词查询博客
     *
     * @param blogKeyword
     * @return
     */
    List<Blog> selectByBlogKeyword(String blogKeyword);

    /**
     * 根据博客分类查询博客
     *
     * @param blogType
     * @return
     */
    List<Blog> selectByBlogType(String blogType);

    /**
     * 根据博客标题查询博客
     *
     * @param blogTitle
     * @return
     */
    List<Blog> selectByBlogTitle(String blogTitle);
}