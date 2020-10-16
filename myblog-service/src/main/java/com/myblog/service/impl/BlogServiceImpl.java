package com.myblog.service.impl;

import com.myblog.mapper.BlogMapper;
import com.myblog.model.Blog;
import com.myblog.model.User;
import com.myblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/12
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Integer save(Blog blog, User user) {
        blog.setIsUsable(true);
        //如果id没有，代表是新写的blog，观看数，评论数，被赞数都为0
        if (Objects.isNull(blog.getId())) {
            blog.setCreateTime(new Date());
            blog.setCreateUserId(user.getId());
            blog.setCommentNum(0);
            blog.setPraiseNum(0);
            blog.setSeeNum(0);
            int insert = blogMapper.insert(blog);
            return insert;
        }
        blog.setUpdateTime(new Date());
        blog.setUpdateUserId(user.getId());
        int update = blogMapper.updateByPrimaryKey(blog);
        return update;
    }

    @Override
    public Blog findByBlogKeyword(String blogKeyword) {
        Blog blog = blogMapper.selectByBlogKeyword(blogKeyword);
        return blog;
    }

    @Override
    public Blog findByBlogType(String blogType) {
        Blog blog = blogMapper.selectByBlogType(blogType);
        return blog;
    }

    @Override
    public Blog findByBlogTitle(String blogTitle) {
        Blog blog = blogMapper.selectByBlogTitle(blogTitle);
        return blog;
    }

    @Override
    public List<Blog> selectAll() {
        List<Blog> blogs = blogMapper.selectAll();
        return blogs;
    }

    @Override
    public Boolean likeBlog(Integer blogId) {
        //根据博客id查询博客，判断博客是否存在
        Blog blog = blogMapper.selectByPrimaryKey(blogId);
        if (Objects.isNull(blog)) {
            return false;
        }
        //点赞数+1
        blog.setPraiseNum(blog.getPraiseNum() + 1);
        //更新博客
        int update = blogMapper.updateByPrimaryKey(blog);
        if (update < 1) {
            return false;
        }
        return true;
    }

    @Override
    public Integer delete(Integer id) {
        int delete = blogMapper.deleteByPrimaryKey(id);
        return delete;
    }
}
