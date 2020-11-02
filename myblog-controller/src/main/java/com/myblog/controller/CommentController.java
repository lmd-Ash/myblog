package com.myblog.controller;

import com.myblog.common.BeanMapper;
import com.myblog.common.MallPage;
import com.myblog.common.Msg;
import com.myblog.common.Result;
import com.myblog.model.Comment;
import com.myblog.model.User;
import com.myblog.req.CommentReq;
import com.myblog.resp.CommentResp;
import com.myblog.service.CommentService;
import com.myblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/31
 */
@RestController
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    /**
     * session用户Key
     */
    @Value("${session.user.key}")
    private String userSession;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    /**
     * 保存评论
     *
     * @param commentReq
     * @param session
     * @return
     */
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody CommentReq commentReq, HttpSession session) {
        User user = (User) session.getAttribute(userSession);
        user = userService.findById(user.getId());
        if (Objects.isNull(user)) {
            return Result.build(Msg.DATA_FAIL, Msg.TEXT_USER_DATA_FAIL);
        }
        if (Objects.isNull(commentReq.getType())) {
            return Result.build(Msg.COMMENT_FAIL, Msg.TEXT_COMMENT_TYPE_FAIL);
        }
        Comment comment = BeanMapper.map(commentReq, Comment.class);
        Integer integer = commentService.saveComment(comment, user);
        if (integer < 1) {
            return Result.buildSaveFail();
        }
        return Result.buildSaveOk();
    }

    /**
     * 分页查询评论
     *
     * @param commentReq
     * @return
     */
    @GetMapping("/pageAll")
    public Result<MallPage<CommentResp>> pageAll(CommentReq commentReq) {
        MallPage<CommentResp> mallPage = new MallPage<>();
        Comment comment = BeanMapper.map(commentReq, Comment.class);
        List<Comment> blogs = commentService.pageAll(comment, commentReq.getPage(), commentReq.getPageSize());
        //查询总条数
        Long integer = commentService.countAll(comment);
        mallPage.setContent(BeanMapper.mapList(blogs, CommentResp.class));
        mallPage.setPage(commentReq.getPage());
        mallPage.setPageSize(commentReq.getPageSize());
        mallPage.setTotalNumber(integer);
        //总页数
        mallPage.setTotalPages(Integer.parseInt(integer.toString()) / commentReq.getPageSize());
        return Result.buildQueryOk(mallPage);
    }
}
