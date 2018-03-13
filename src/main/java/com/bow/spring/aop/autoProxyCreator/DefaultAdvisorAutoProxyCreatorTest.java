package com.bow.spring.aop.autoProxyCreator;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bow.spring.aop.DemoMethodInterceptor;
import com.bow.spring.aop.UserService;
import com.bow.spring.aop.UserServiceImpl;

/**
 * 通过配置{@link DefaultAdvisorAutoProxyCreator} 为满足条件的切点自动的生成代理
 *
 * @see BeanNameAutoProxyCreatorTest
 * @see AnnotationAwareAspectJAutoProxyCreatorTest
 * @author vv
 * @since 2017/2/1.
 */
@Configuration
public class DefaultAdvisorAutoProxyCreatorTest {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean(name = "demoInterceptor")
    public Advice interceptor() {
        return new DemoMethodInterceptor();
    }

    @Bean
    public NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor() {
        // 只要方法名匹配 add* 就调用切面
        NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor = new NameMatchMethodPointcutAdvisor();
        nameMatchMethodPointcutAdvisor.setMappedName("add*");
        nameMatchMethodPointcutAdvisor.setAdvice(interceptor());
        return nameMatchMethodPointcutAdvisor;
    }

    /**
     * 配置DefaultAdvisorAutoProxyCreator
     * 
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                DefaultAdvisorAutoProxyCreatorTest.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.addUser();
    }
}
