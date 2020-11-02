package com.myblog.controller;

import com.myblog.common.BeanMapper;
import com.myblog.common.MallPage;
import com.myblog.common.Msg;
import com.myblog.common.Result;
import com.myblog.model.Blog;
import com.myblog.model.User;
import com.myblog.req.BlogReq;
import com.myblog.resp.BlogResp;
import com.myblog.service.BlogService;
import com.myblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/12
 */
@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogController {
    /**
     * session用户Key
     */
    @Value("${session.user.key}")
    private String userSession;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    /**
     * 保存博客
     *
     * @param blogReq
     * @param session
     * @return
     */
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody BlogReq blogReq, HttpSession session) {
        User user = (User) session.getAttribute(userSession);
        user = userService.findById(user.getId());
        if (Objects.isNull(user)) {
            return Result.build(Msg.DATA_FAIL, Msg.TEXT_USER_DATA_FAIL);
        }
        if (StringUtils.isBlank(blogReq.getBlogTitle())) {
            return Result.build(Msg.BLOG_FAIL, Msg.TEXT_BLOG_TITLE_FAIL);
        }
        if (Objects.isNull(blogReq.getBlogType())) {
            return Result.build(Msg.BLOG_FAIL, Msg.TEXT_BLOG_TYPE_FAIL);
        }
        if (StringUtils.isBlank(blogReq.getBlogKeyword())) {
            return Result.build(Msg.BLOG_FAIL, Msg.TEXT_BLOG_KEYWORD_FAIL);
        }
        if (StringUtils.isBlank(blogReq.getBlogAbstract())) {
            return Result.build(Msg.BLOG_FAIL, Msg.TEXT_BLOG_ABSTRACT_FAIL);
        }
        Blog blog = BeanMapper.map(blogReq, Blog.class);
        Integer integer = blogService.saveBlog(blog, user);
        if (integer < 1) {
            return Result.buildSaveFail();
        }
        return Result.buildSaveOk();
    }

    /**
     * 分页查询博客
     *
     * @param blogReq
     * @return
     */
    @GetMapping("/pageAll")
    public Result<MallPage<BlogResp>> pageAll(BlogReq blogReq) {
        MallPage<BlogResp> mallPage = new MallPage<>();
        Blog blog = BeanMapper.map(blogReq, Blog.class);
        List<Blog> blogs = blogService.pageAll(blog, blogReq.getPage(), blogReq.getPageSize());
        //查询总条数
        Long integer = blogService.countAll(blog);
        mallPage.setContent(BeanMapper.mapList(blogs, BlogResp.class));
        mallPage.setPage(blogReq.getPage());
        mallPage.setPageSize(blogReq.getPageSize());
        mallPage.setTotalNumber(integer);
        //总页数
        mallPage.setTotalPages(Integer.parseInt(integer.toString()) / blogReq.getPageSize());
        return Result.buildQueryOk(mallPage);
    }

    /**
     * 对博客点赞
     *
     * @param blogReq
     * @param session
     * @return
     */
    @PostMapping("/likeBlog")
    public Result<Boolean> likeBlog(@RequestBody BlogReq blogReq, HttpSession session) {
        if (Objects.isNull(blogReq.getId())) {
            return Result.buildParamFail();
        }
        Blog blog = blogService.findById(blogReq.getId());
        if (Objects.isNull(blog)) {
            return Result.build(Msg.BLOG_FAIL, Msg.TEXT_BLOG_DELETE_FAIL);
        }
        Boolean likeBlog = blogService.likeBlog(blog);
        if (!likeBlog) {
            return Result.build(Msg.FAIL, Msg.TEXT_LIKE_FAIL);
        }
        return Result.build(Msg.OK, Msg.TEXT_LIKE_OK);
    }

    /**
     * 删除博客
     *
     * @param blogReq
     * @param session
     * @return
     */
    @PostMapping("/deleteBlog")
    public Result<Boolean> deleteBlog(@RequestBody BlogReq blogReq, HttpSession session) {
        User user = (User) session.getAttribute(userSession);
        user = userService.findById(user.getId());
        if (Objects.isNull(user)) {
            return Result.build(Msg.DATA_FAIL, Msg.TEXT_USER_DATA_FAIL);
        }
        if (Objects.isNull(blogReq.getId())) {
            return Result.buildParamFail();
        }
        Blog blog = blogService.findById(blogReq.getId());
        if (Objects.isNull(blog)) {
            return Result.build(Msg.BLOG_FAIL, Msg.TEXT_BLOG_DELETE_FAIL);
        }
        Integer deleteBlog = blogService.deleteBlog(blog, user);
        if (deleteBlog < 1) {
            return Result.build(Msg.FAIL, Msg.TEXT_BLOG_DELETE_FAIL);
        }
        return Result.build(Msg.OK, Msg.TEXT_DELETE_OK);
    }
}
