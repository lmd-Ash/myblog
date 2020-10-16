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
     * 根据博客关键词查询博客
     *
     * @param blogKeyword
     * @return
     */
    Blog selectByBlogKeyword(String blogKeyword);

    /**
     * 根据博客分类查询博客
     *
     * @param blogType
     * @return
     */
    Blog selectByBlogType(String blogType);

    /**
     * 根据博客标题查询博客
     *
     * @param blogTitle
     * @return
     */
    Blog selectByBlogTitle(String blogTitle);
}