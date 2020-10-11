package com.myblog.controller;

import com.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/11
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
}
