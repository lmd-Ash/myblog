package com.myblog.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 注解@order，使用注解方式使bean的加载顺序得到控制
 * 值越小，越先被加载。
 *
 * @author lmd
 * @version 1.0.0
 * @date 2020/12/5
 */
@Aspect
@Component
@Order(1)
public class AshOneAdvice {
    //定义一个切面，括号内写自定义注解的路径
    @Pointcut("@annotation(com.myblog.common.AshOneAnnotation)")
    public void AshCheck() {
    }

    public Object AshCheckOne(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("================第一个切面=================" + System.currentTimeMillis());
        //获取请求参数
        Object[] objects = joinPoint.getArgs();
        Long id = ((JSONObject) objects[0]).getLong("id");
        String name = ((JSONObject) objects[0]).getString("name");
        System.out.println("id--->" + id);
        System.out.println("name--->" + name);

        //id小于0则抛出非法id的异常
        if (id < 0) {
            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
        }
        return joinPoint.proceed();
    }
}
