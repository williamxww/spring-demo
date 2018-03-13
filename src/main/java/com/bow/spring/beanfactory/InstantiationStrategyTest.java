package com.bow.spring.beanfactory;

import java.lang.reflect.Constructor;

import org.springframework.beans.factory.support.InstantiationStrategy;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.support.SimpleInstantiationStrategy;

/**
 * 最简单的实例化策略
 * 
 * @author vv
 * @since 2017/4/30.
 */
public class InstantiationStrategyTest {

    public void test() throws NoSuchMethodException {
        RootBeanDefinition definition = new RootBeanDefinition(SimpleBean.class);
        // 实例化策略
        InstantiationStrategy strategy = new SimpleInstantiationStrategy();
        SimpleBean test = (SimpleBean) strategy.instantiate(definition, null, null);
        System.out.println(test);
        // 指定构造方法
        Constructor<SimpleBean> constructor = SimpleBean.class.getConstructor(String.class, String.class);
        SimpleBean test2 = (SimpleBean) strategy.instantiate(definition, null, null, constructor, "vv", "123");
        System.out.println(test2);
    }

}
