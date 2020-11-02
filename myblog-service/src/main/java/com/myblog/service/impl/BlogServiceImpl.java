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
    public Integer saveBlog(Blog blog, User user) {
        blog.setId(null);
        blog.setIsUsable(true);
        //新写的blog，观看数，评论数，被赞数都为0
        blog.setCreateTime(new Date());
        blog.setCreateUserId(user.getId());
        blog.setCreateUserName(user.getUserName());
        blog.setCommentNum(0);
        blog.setPraiseNum(0);
        blog.setSeeNum(0);
        int insert = blogMapper.insert(blog);
        return insert;
    }

    @Override
    public List<Blog> pageAll(Blog blog, Integer page, Integer pageSize) {
        blog.setPage(page * pageSize);
        blog.setPageSize((page + 1) * pageSize);
        List<Blog> list = blogMapper.pageAll(blog);
        return list;
    }

    @Override
    public Blog findById(Integer id) {
        Blog blog = blogMapper.selectByPrimaryKey(id);
        return blog;
    }

    @Override
    public Long countAll(Blog blog) {
        Long counts = blogMapper.countAll(blog);
        return counts;
    }

    @Override
    public List<Blog> selectAll() {
        List<Blog> blogs = blogMapper.selectAll();
        return blogs;
    }

    @Override
    public Boolean likeBlog(Blog blog) {
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
    public Integer deleteBlog(Blog blog, User user) {
        blog.setUpdateUserId(user.getId());
        blog.setUpdateTime(new Date());
        blog.setUpdateUserName(user.getUserName());
        int delete = blogMapper.deleteBlog(blog);
        return delete;
    }
}
