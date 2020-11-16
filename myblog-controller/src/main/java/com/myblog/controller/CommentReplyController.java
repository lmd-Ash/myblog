package com.myblog.controller;

import com.myblog.common.BeanMapper;
import com.myblog.common.MallPage;
import com.myblog.common.Msg;
import com.myblog.common.Result;
import com.myblog.model.Comment;
import com.myblog.model.CommentReply;
import com.myblog.model.User;
import com.myblog.req.CommentReplyReq;
import com.myblog.resp.CommentReplyResp;
import com.myblog.service.CommentReplyService;
import com.myblog.service.CommentService;
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
 * @date 2020/10/31
 */
@RestController
@RequestMapping("/commentReply")
@Slf4j
public class CommentReplyController {
    /**
     * session用户Key
     */
    @Value("${session.user.key}")
    private String userSession;
    @Autowired
    private CommentReplyService commentReplyService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    /**
     * 保存评论回复
     *
     * @param commentReplyReq
     * @param session
     * @return
     */
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody CommentReplyReq commentReplyReq, HttpSession session) {
        User user = (User) session.getAttribute(userSession);
        user = userService.findById(user.getId());
        if (Objects.isNull(user)) {
            return Result.build(Msg.DATA_FAIL, Msg.TEXT_USER_DATA_FAIL);
        }
        if (Objects.isNull(commentReplyReq.getCommentId())) {
            return Result.build(Msg.COMMENT_FAIL, Msg.TEXT_COMMENT_ID_FAIL);
        }
        Comment comment = commentService.findById(commentReplyReq.getCommentId());
        if (Objects.isNull(comment)) {
            return Result.build(Msg.COMMENT_FAIL, Msg.TEXT_COMMENT_FAIL);
        }
        if (StringUtils.isBlank(commentReplyReq.getContent())) {
            return Result.build(Msg.COMMENT_FAIL, Msg.TEXT_COMMENT_CONTENT_FAIL);
        }
        CommentReply commentReply = BeanMapper.map(commentReplyReq, CommentReply.class);
        Integer integer = commentReplyService.saveCommentReply(commentReply, user);
        if (integer < 1) {
            return Result.buildSaveFail();
        }
        return Result.buildSaveOk();
    }

    /**
     * 分页查询评论
     *
     * @param commentReplyReq
     * @return
     */
    @GetMapping("/pageAll")
    public Result<MallPage<CommentReplyResp>> pageAll(CommentReplyReq commentReplyReq) {
        MallPage<CommentReplyResp> mallPage = new MallPage<>();
        CommentReply commentReply = BeanMapper.map(commentReplyReq, CommentReply.class);
        List<CommentReply> blogs = commentReplyService.pageAll(commentReply, commentReplyReq.getPage(), commentReplyReq.getPageSize());
        //查询总条数
        Long integer = commentReplyService.countAll(commentReply);
        mallPage.setContent(BeanMapper.mapList(blogs, CommentReplyResp.class));
        mallPage.setPage(commentReplyReq.getPage());
        mallPage.setPageSize(commentReplyReq.getPageSize());
        mallPage.setTotalNumber(integer);
        //总页数
        mallPage.setTotalPages(Integer.parseInt(integer.toString()) / commentReplyReq.getPageSize());
        return Result.buildQueryOk(mallPage);
    }

    /**
     * 删除博客
     *
     * @param commentReplyReq
     * @param session
     * @return
     */
    @PostMapping("/deleteComment")
    public Result<Boolean> deleteComment(@RequestBody CommentReplyReq commentReplyReq, HttpSession session) {
        User user = (User) session.getAttribute(userSession);
        user = userService.findById(user.getId());
        if (Objects.isNull(user)) {
            return Result.build(Msg.DATA_FAIL, Msg.TEXT_USER_DATA_FAIL);
        }
        if (Objects.isNull(commentReplyReq.getId())) {
            return Result.buildParamFail();
        }
        CommentReply commentReply = commentReplyService.findById(commentReplyReq.getId());
        if (Objects.isNull(commentReply)) {
            return Result.build(Msg.COMMENT_FAIL, Msg.TEXT_COMMENT_FAIL);
        }
        Integer deleteCommentReply = commentReplyService.deleteCommentReply(commentReply, user);
        if (deleteCommentReply < 1) {
            return Result.build(Msg.FAIL, Msg.TEXT_COMMENT_DELETE_FAIL);
        }
        return Result.build(Msg.OK, Msg.TEXT_DELETE_OK);
    }
}
