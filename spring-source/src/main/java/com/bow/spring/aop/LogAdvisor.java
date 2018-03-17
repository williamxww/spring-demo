package com.bow.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

/**
 * {@link PointcutAdvisor} 将advice和pointcut结合起来
 * 
 * @author vv
 * @since 2017/1/30.
 */
public class LogAdvisor extends StaticMethodMatcherPointcutAdvisor {

    /**
     * 只匹配addUser方法
     * 
     * @param method
     * @param targetClass
     * @return
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return "addUser".equals(method.getName());
    }

    /**
     * 默认情况下是匹配所有类，在此只是匹配{@link UserService}及其子类
     * 
     * @see StaticMethodMatcherPointcut#classFilter
     * 
     * @return ClassFilter
     */
    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return UserService.class.isAssignableFrom(clazz);
            }
        };
    }

}
