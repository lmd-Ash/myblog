package com.myblog.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * AOP切面类
 *      注解@Aspect用来描述一个切面类，
 * @author lmd
 * @version 1.0.0
 * @date 2020/12/5
 */
@Aspect
@Component
public class LogAdvice {
    //定义一个切点，所有被GetMapping注解修饰的方法会织入advice
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void logAdvicePointcut(){}

    //Before表示logAdvice将在目标方法执行前执行
    @Before("logAdvicePointcut()")
    public void logAdvice(){
        //这里写处理的业务逻辑
        System.out.println("get请求的advice触发了");
    }
}
