package com.myblog.mapper;

import com.myblog.model.Blog;
import org.apache.ibatis.annotations.Param;
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
     * @return
     */
    List<Blog> pageAll(Blog blog);

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

    /**
     * 删除博客
     *
     * @param blog
     * @return
     */
    Integer deleteBlog(Blog blog);
}