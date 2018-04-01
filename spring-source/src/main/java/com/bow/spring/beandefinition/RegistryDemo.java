package com.bow.spring.beandefinition;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.stereotype.Component;

/**
 * @author vv
 * @since 2018/4/1.
 */
public class RegistryDemo {

    /**
     * BeanDefinition实现了{@link BeanMetadataElement},说明其具有访问source（配置源）的能力。
     */
    public void test1(){
        SimpleBeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();

        //注册两个BeanDefinition
        BeanDefinition bd1 = new GenericBeanDefinition();
        registry.registerBeanDefinition("d1", bd1);

        BeanDefinition bd2 = new RootBeanDefinition();
        registry.registerBeanDefinition("d2", bd2);

        //方法测试
        System.out.println(registry.containsBeanDefinition("d1"));//true
        System.out.println(registry.getBeanDefinitionCount());//2
        System.out.println(Arrays.toString(registry.getBeanDefinitionNames()));//[d1, d2]
    }

    public void test2(){
        AnnotatedGenericBeanDefinition bd=new AnnotatedGenericBeanDefinition(Config.class);
        System.out.println(bd.getMetadata().getAnnotationTypes());
        System.out.println(bd.isSingleton());
        System.out.println(bd.getBeanClassName());
    }

    /**
     * {@link BeanDefinitionReader} 持有{@link BeanDefinitionRegistry}, 他可以将配置源转化为多个BeanDefinition
     * 并注册到BeanDefinitionRegistry中， BeanDefinitionReader帮助registry实现了高效的注册。
     * @see XmlBeanDefinitionReader 把xml配置文件转化成beanDefinition
     * @see PropertiesBeanDefinitionReader 可以从Properties文件读取BeanDefinition
     */
    public void test3(){
        SimpleBeanDefinitionRegistry registry= new SimpleBeanDefinitionRegistry();
        // 注意此reader没有实现BeanDefinitionReader
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(registry);
        reader.register(Config.class);
        System.out.println( registry.getBeanDefinitionCount());
    }

    public void test4(){
        ClassPathScanningCandidateComponentProvider provider=new ClassPathScanningCandidateComponentProvider(true);
        Set<BeanDefinition> bds= provider.findCandidateComponents("com.bow.spring.beandefinition");
        for(BeanDefinition bd: bds){
            System.out.println(bd.getBeanClassName());
        }
        System.out.println(bds.size());
    }

    public static void main(String[] args) {
        RegistryDemo demo = new RegistryDemo();
//        demo.test1();
        demo.test2();
        demo.test4();
    }



    @Component("conf")
    public static class Config{

    }
}
