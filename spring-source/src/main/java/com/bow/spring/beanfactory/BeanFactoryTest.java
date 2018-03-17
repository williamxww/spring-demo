package com.bow.spring.beanfactory;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * 代码创建IOC容器
 * 
 * @author ViVi
 * @date 2015年8月22日 下午2:45:36
 */

public class BeanFactoryTest {

    /**
     * 创建IOC容器
     */
    public void testCreate() {
        ClassPathResource resource = new ClassPathResource("com/bow/spring/beanfactory/postprocessor.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
    }

    /**
     * 编程式构造容器
     */
    public void testCreate2() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // new bean
        AbstractBeanDefinition newBeanDefinition = new RootBeanDefinition(SimpleBean.class);
        newBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        // set property
        MutablePropertyValues beanProps = new MutablePropertyValues();
        beanProps.add("username", "vv").add("password", "***");
        newBeanDefinition.setPropertyValues(beanProps);
        // register
        beanFactory.registerBeanDefinition("simpleBean", newBeanDefinition);
        // usage
        SimpleBean bean = beanFactory.getBean(SimpleBean.class);
        System.out.println(bean.getUsername());
    }

    /**
     * 测试{@link DemoBeanPostProcessor} 在bean的初始化前后放置扩展点
     */
    public void testBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        DemoBeanPostProcessor beanPostProcessor = new DemoBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);
        // new bean
        AbstractBeanDefinition newBeanDefinition = new RootBeanDefinition(SimpleBean.class);
        newBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        // set property
        MutablePropertyValues beanProps = new MutablePropertyValues();
        beanProps.add("username", "vv").add("password", "***");
        newBeanDefinition.setPropertyValues(beanProps);
        // register
        beanFactory.registerBeanDefinition("simpleBean", newBeanDefinition);
        // usage
        SimpleBean bean = beanFactory.getBean(SimpleBean.class);
        System.out.println(bean.getUsername());
    }

    /**
     * 测试{@link DemoInstantiationAwareBeanPostProcessor} 在bean的实例化和初始化前后放置扩展点
     */
    public void testBeanPostProcessor2() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        DemoInstantiationAwareBeanPostProcessor beanPostProcessor = new DemoInstantiationAwareBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);
        // new bean
        AbstractBeanDefinition newBeanDefinition = new RootBeanDefinition(SimpleBean.class);
        newBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        // set property
        MutablePropertyValues beanProps = new MutablePropertyValues();
        beanProps.add("username", "vv").add("password", "***");
        newBeanDefinition.setPropertyValues(beanProps);
        // register
        beanFactory.registerBeanDefinition("simpleBean", newBeanDefinition);
        // usage
        SimpleBean bean = beanFactory.getBean(SimpleBean.class);
        System.out.println(bean.getUsername());
    }
}
