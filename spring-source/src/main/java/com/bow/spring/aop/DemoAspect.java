package com.bow.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

/**
 * 编程式切面
 *
 * @author acer
 * @since 2016年2月21日
 */
public class DemoAspect {
    public void handleAround(ProceedingJoinPoint pjp) {
        Signature signature = pjp.getSignature();
        String methodName = signature.getName();
        System.out.println("methodName:" + methodName);
        try {
            Object result = pjp.proceed();
            System.out.println("result:" + result);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
