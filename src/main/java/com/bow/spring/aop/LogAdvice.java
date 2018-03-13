package com.bow.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 测试AOP<br/>
 *
 * {@link org.aopalliance.aop.Advice} 又叫做'增强', 因为每一个advice都是对目标方法的增强。
 * 
 * @author ViVi
 * @date 2015年6月7日 下午3:47:04
 */

public class LogAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("MethodBeforeAdvice "+Thread.currentThread().getName());
    }

}
