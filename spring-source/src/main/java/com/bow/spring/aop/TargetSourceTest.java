package com.bow.spring.aop;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.target.SingletonTargetSource;

/**
 * proxy代理的不是target，而是TargetSource,通过TargetSource对target进行了包装
 *
 * @author vv
 * @since 2017/1/30.
 */
public class TargetSourceTest {

    public void test1() {
        UserService target = new UserServiceImpl();
        TargetSource targetSource = new SingletonTargetSource(target);
        UserService proxy = (UserService) ProxyFactory.getProxy(targetSource);
        System.out.println(proxy.getClass().getName());
    }

    /**
     * 自定义TargetSource  UserServiceTargetSource
     */
    public void test2() {
        UserService service1 = new UserServiceImpl();
        UserService service2 = new UserServiceImpl("domain2");
        TargetSource userService = new UserServiceTargetSource(service1, service2);
        UserService proxy = (UserService) ProxyFactory.getProxy(userService);
        for (int i = 0; i < 5; i++) {
            proxy.addUser();
        }
    }
}
