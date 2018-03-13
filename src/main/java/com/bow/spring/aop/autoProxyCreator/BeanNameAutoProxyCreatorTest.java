package com.bow.spring.aop.autoProxyCreator;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bow.spring.aop.DemoMethodInterceptor;
import com.bow.spring.aop.ProxyFactoryTest;
import com.bow.spring.aop.UserService;
import com.bow.spring.aop.UserServiceImpl;

/**
 * 通过AnnotationConfigApplicationContext配置容器测试{@link BeanNameAutoProxyCreator}
 *
 * @see DefaultAdvisorAutoProxyCreatorTest
 * @see AnnotationAwareAspectJAutoProxyCreatorTest
 * @author vv
 * @since 2017/2/1.
 */
@Configuration
public class BeanNameAutoProxyCreatorTest {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean(name = "demoInterceptor")
    public Advice interceptor() {
        return new DemoMethodInterceptor();
    }

    /**
     * 注意：主要靠
     * {@link AbstractAutoProxyCreator#postProcessAfterInitialization(Object, String)}
     * 实现给bean增加代理的。在此方法内发现bean的名字符合userSer*，就new
     * {@link org.springframework.aop.framework.ProxyFactory}
     * 然后增加interceptor,最后返回代理给容器。具体参照{@link ProxyFactoryTest#test2()}
     *
     * @return beanNameAutoProxyCreator
     */
    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        // 设置要创建代理的那些Bean的名字
        beanNameAutoProxyCreator.setBeanNames("userSer*");
        // 设置拦截链名字(这些拦截器是有先后顺序的)
        beanNameAutoProxyCreator.setInterceptorNames("demoInterceptor");
        return beanNameAutoProxyCreator;
    }

    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                BeanNameAutoProxyCreatorTest.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.addUser();
    }
}
