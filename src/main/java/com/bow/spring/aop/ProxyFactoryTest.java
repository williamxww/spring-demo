package com.bow.spring.aop;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * 编程实现aop功能
 * 
 * @author vv
 * @since 2017/1/30.
 */
public class ProxyFactoryTest {

    public void test1() {
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(new LogAdvice());
        UserService target = new UserServiceImpl();
        factory.setTarget(target);
        factory.setProxyTargetClass(false);
        factory.setInterfaces(UserService.class);
        UserService proxy = (UserService) factory.getProxy();
        proxy.addUser();
    }

    /**
     * 通过此入口可以大致调试查看AOP源码
     */
    public void test2() {
        UserService target = new UserServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new LogAdvice());
        UserService proxy = (UserService) factory.getProxy();
        proxy.addUser();
    }

    public void test2_1() {
        UserService target = new UserServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        // 使用MethodInterceptor
        DemoMethodInterceptor interceptor = new DemoMethodInterceptor();
        factory.addAdvice(interceptor);
        UserService proxy = (UserService) factory.getProxy();
        proxy.addUser();
    }

    /**
     * 监听器
     */
    public void test2_2() {
        UserService target = new UserServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvice(new LogAdvice());
        factory.addListener(new AdvisedSupportListener() {
            @Override
            public void activated(AdvisedSupport advised) {
                System.out.println("activated " + advised.getProxiedInterfaces());
            }

            @Override
            public void adviceChanged(AdvisedSupport advised) {
                System.out.println("adviceChanged " + advised.getAdvisors());
            }
        });
        UserService proxy = (UserService) factory.getProxy();
        proxy.addUser();
    }

    /**
     * 测试{@link LogAdvisor}
     */
    public void test3() {
        UserService target = new UserServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        LogAdvisor advisor = new LogAdvisor();
        // 给advisor设置advice
        advisor.setAdvice(new LogAdvice());
        factory.addAdvisor(advisor);
        UserService proxy = (UserService) factory.getProxy();
        // 注意：delete是没有增强的，只有addUser增强了
        proxy.deleteUser(1);
        proxy.addUser();
    }

    /**
     * 通过{@link UserSalaryIntroduction} 给{@link UserService}增加
     * {@link SalaryService}功能
     */
    public void test4() {

        // 编写一个salaryService的advice
        UserSalaryIntroduction userSalaryIntroduction = new UserSalaryIntroduction();
        DefaultIntroductionAdvisor advisor = new DefaultIntroductionAdvisor(userSalaryIntroduction,
                SalaryService.class);

        // 创建UserService的代理
        UserService target = new UserServiceImpl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(advisor);
        UserService proxy = (UserService) factory.getProxy();
        proxy.addUser();
        // 注意这个代理现在可以强转为SalaryService了
        SalaryService salaryService = (SalaryService) proxy;
        salaryService.addSalary();
    }
}
