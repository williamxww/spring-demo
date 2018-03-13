package com.bow.spring.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

/**
 * Introduction：引入<br/>
 * 这样编写可以使获取到的UserService代理有SalaryService的功能
 *
 * @see ProxyFactoryTest#test4()
 * 
 * @author vv
 * @since 2017/1/30.
 */
public class UserSalaryIntroduction implements SalaryService, IntroductionInterceptor {
    @Override
    public void addSalary() {
        System.out.println("add salary :) ");
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (implementsInterface(invocation.getMethod().getDeclaringClass())) {
            // 此advice实现了调用接口，执行此实例的方法(addSalary)
            return invocation.getMethod().invoke(this, invocation.getArguments());
        } else {
            // 其他的方法还是走之前的
            return invocation.proceed();
        }
    }

    /**
     * Does this introduction advice implement the given interface?
     * SalaryService advice 实现了 intf 么?
     * 
     * @param intf
     *            the given interface
     * @return
     */
    @Override
    public boolean implementsInterface(Class<?> intf) {
        return intf.isAssignableFrom(SalaryService.class);
    }
}
