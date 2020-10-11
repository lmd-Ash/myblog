package com.myblog.config;

import com.alibaba.fastjson.JSONObject;
import com.myblog.common.Msg;
import com.myblog.common.Result;
import com.myblog.mapper.UserMapper;
import com.myblog.model.User;
import com.myblog.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/10/11
 */
@Component
@Slf4j
public class TokenInterceptor extends HandlerInterceptorAdapter {
    /**
     * session用户Key
     */
    @Value("${session.user.key}")
    private String userSession;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.从请求头中获取token
        String token = request.getHeader("Authorization");
        //2.判断 token 是否存在
        if (StringUtils.isBlank(token)) {
            //重置response
            response.reset();
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(Result.build(Msg.TOKEN_FAIL, Msg.TEXT_TOKEN_INVALID_FAIL)));
            log.debug("用户token失效");
            return false;
        }
        // 3、解析token
        Claims claim = JwtUtil.getClaim(token);
        if (Objects.isNull(claim)) {
            //重置response
            response.reset();
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(Result.build(Msg.TOKEN_FAIL, Msg.TEXT_TOKEN_INVALID_FAIL)));
            log.debug("用户token失效");
            return false;
        }
        // 4、判断 token 是否过期
        Date expiration = claim.getExpiration();
        boolean tokenExpired = JwtUtil.isTokenExpired(expiration);
        if (tokenExpired) {
            //重置response
            response.reset();
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(Result.build(Msg.TOKEN_FAIL, "token已过期，请重新登录" + Msg.TEXT_TOKEN_INVALID_FAIL)));
            log.debug("token已过期，请重新登录");
            return false;
        }
        // 5、 从 token 中获取员工信息
        String userId = claim.getSubject();
        User user = userMapper.selectByPrimaryKey(Integer.valueOf(userId));
        if (Objects.isNull(user)) {
            //重置response
            response.reset();
            //设置编码格式
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(Result.build(Msg.TOKEN_FAIL, Msg.TEXT_TOKEN_INVALID_FAIL)));
            log.debug("token已过期，请重新登录");
            return false;
        }
        HttpSession session = request.getSession();
        log.debug("token验证通过用户信息为：{}", user);
        session.setAttribute(userSession, user);
        return true;
    }
}
