package com.myblog.service;

import com.myblog.model.User;
import com.myblog.vo.TokenVo;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/11
 */
public interface UserService {
    /**
     * 保存/修改 用户
     *
     * @param user
     * @return
     */
    Integer save(User user);

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 登录
     *
     * @param user
     * @return
     */
    TokenVo login(User user);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    TokenVo register(User user);
}
