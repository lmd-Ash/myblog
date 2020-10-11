package com.myblog.controller;

import com.myblog.common.BeanMapper;
import com.myblog.common.Msg;
import com.myblog.common.Result;
import com.myblog.model.User;
import com.myblog.req.UserReq;
import com.myblog.service.UserService;
import com.myblog.utils.MD5Util;
import com.myblog.vo.TokenVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/11
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    /**
     * session用户Key
     */
    @Value("${session.user.key}")
    private String userSession;
    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param userReq
     * @return
     */
    @PostMapping("/register")
    public Result<TokenVo> register(@RequestBody UserReq userReq) {
        if (StringUtils.isBlank(userReq.getEmail())) {
            return Result.build(Msg.PARAM_FAIL, Msg.TEXT_EMAIL_NULL_FAIL);
        }
        User oldUser = userService.findByEmail(userReq.getEmail());
        if (Objects.nonNull(oldUser)) {
            return Result.build(Msg.FAIL, Msg.TEXT_EMAIL_EXIST);
        }
        if (StringUtils.isBlank(userReq.getPassword())) {
            return Result.build(Msg.PARAM_FAIL, Msg.TEXT_PASSWORD_NULL_FAIL);
        }
        User user = BeanMapper.map(userReq, User.class);
        TokenVo tokenVo = userService.register(user);
        if (!tokenVo.getIfSuccess()) {
            return Result.build(Msg.FAIL, Msg.TEXT_REGISTER_FAIL);
        }
        return Result.build(Msg.OK, Msg.TEXT_REGISTER_OK, tokenVo);
    }

    /**
     * 登录
     *
     * @param userReq
     * @return
     */
    @PostMapping("/login")
    public Result<TokenVo> login(@RequestBody UserReq userReq) {
        if (StringUtils.isBlank(userReq.getEmail())) {
            return Result.build(Msg.PARAM_FAIL, Msg.TEXT_EMAIL_NULL_FAIL);
        }
        if (StringUtils.isBlank(userReq.getPassword())) {
            return Result.build(Msg.PARAM_FAIL, Msg.TEXT_PASSWORD_NULL_FAIL);
        }
        User user = userService.findByEmail(userReq.getEmail());
        if (Objects.isNull(user)) {
            return Result.build(Msg.DATA_FAIL, Msg.TEXT_USER_DATA_FAIL);
        }
        TokenVo tokenVo = userService.login(user);
        if (!tokenVo.getIfSuccess()) {
            return Result.build(Msg.FAIL, Msg.TEXT_LOGIN_FAIL);
        }
        return Result.build(Msg.OK, Msg.TEXT_LOGIN_OK, tokenVo);
    }

    /**
     * 修改密码
     *
     * @param userReq
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody UserReq userReq, HttpSession session) {
        User user = (User) session.getAttribute(userSession);
        user = userService.findById(user.getId());
        if (Objects.isNull(user)) {
            return Result.build(Msg.DATA_FAIL, Msg.TEXT_USER_DATA_FAIL);
        }
        if (StringUtils.isBlank(userReq.getPassword())) {
            return Result.build(Msg.PARAM_FAIL, Msg.TEXT_PASSWORD_NULL_FAIL);
        }
        if (StringUtils.isBlank(userReq.getOldPassword())) {
            return Result.build(Msg.PARAM_FAIL, Msg.TEXT_OLD_PASSWORD_NULL_FAIL);
        }
        //加密密码
        String oldPassword = MD5Util.stringMD5(userReq.getOldPassword());
        String password = MD5Util.stringMD5(userReq.getPassword());
        if (!Objects.equals(oldPassword, user.getPassword())) {
            return Result.build(Msg.FAIL, Msg.TEXT_OLD_PASSWORD_FAIL);
        }
        user.setPassword(password);
        Integer save = userService.save(user);
        if (save < 1) {
            return Result.buildSaveFail();
        }
        return Result.buildSaveOk();
    }
}
