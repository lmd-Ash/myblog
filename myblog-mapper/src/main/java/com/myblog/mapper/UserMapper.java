package com.myblog.mapper;

import com.myblog.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmd
 */
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    User selectByEmail(String email);
}