package com.bow.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ViVi
 * @date 2015年6月7日 下午4:06:34
 */

public class AopTest {

    /**
     * 用代理的方式做aop
     */
    public void testAop() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath:com/bow/spring/aop/aopContext.xml" });
        UserService service = (UserService) context.getBean("proxy");
        service.addUser();
    }

    /**
     * 测试编程式切面{@link DemoAspect}
     */
    @SuppressWarnings("resource")
    public void testAroundAop() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath:com/bow/spring/aop/aopContext.xml" });
        SalaryService service = context.getBean("salaryServiceImpl", SalaryService.class);
        service.addSalary();
    }

    /**
     * 使用注解做切面
     */
    public void testAnnotationAop() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath:com/bow/spring/aop/aopContext.xml" });
        UserService service = context.getBean("userServiceImpl", UserService.class);
        service.deleteUser(1);
    }
}
