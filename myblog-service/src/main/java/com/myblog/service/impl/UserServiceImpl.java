package com.myblog.service.impl;

import com.myblog.mapper.UserMapper;
import com.myblog.model.User;
import com.myblog.service.UserService;
import com.myblog.utils.JwtUtil;
import com.myblog.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/11
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer save(User user) {
        if (Objects.isNull(user.getId())) {
            int insert = userMapper.insert(user);
            return insert;
        }
        int update = userMapper.updateByPrimaryKey(user);
        return update;
    }

    @Override
    public User findById(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = userMapper.selectByEmail(email);
        return user;
    }

    @Override
    public TokenVo login(User user) {
       return null;
    }

    @Override
    public TokenVo register(User user) {
        TokenVo tokenVo = new TokenVo();
        user.setIsUsable(true);
        user.setCreateTime(new Date());
        int insert = userMapper.insert(user);
        if (insert < 1) {
            return tokenVo.setIfSuccess(false);
        }
        //生成token
        String token = JwtUtil.createToken(user.getId().toString());
        tokenVo.setToken(token);
        tokenVo.setIfSuccess(true);
        return tokenVo;
    }
}
