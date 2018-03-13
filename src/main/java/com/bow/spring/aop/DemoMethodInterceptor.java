package com.bow.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author vv
 * @since 2017/2/1.
 */
public class DemoMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before proceed");
        Object ret = invocation.proceed();
        System.out.println("after proceed");
        return ret;
    }
}
