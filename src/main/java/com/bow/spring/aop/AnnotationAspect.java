package com.bow.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 完全通过注解来配置aop,注意这些连接点的触发顺序
 *
 * @author acer
 * @since 2016年4月24日
 */
@Aspect
public class AnnotationAspect {
    @Pointcut("execution(* com.bow.spring.aop.UserServiceImpl.*(..))")
    public void anyMethod() {

    }

    @Around("anyMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("进入环绕通知");
        Object object = pjp.proceed();// 执行该方法
        System.out.println("退出环绕通知");
        return object;
    }

    @Before(value = "anyMethod()")
    public void doAccessCheck() {
        System.out.println("前置通知");
    }

    @After("anyMethod()")
    public void after() {
        System.out.println("后置通知");
    }

    @AfterReturning("anyMethod()")
    public void doAfter() {
        System.out.println("AfterReturning");
    }

    @AfterThrowing("anyMethod()")
    public void doAfterThrow() {
        System.out.println("例外通知");
    }

}
