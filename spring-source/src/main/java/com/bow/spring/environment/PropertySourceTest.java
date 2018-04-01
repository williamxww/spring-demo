package com.bow.spring.environment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * PropertySource类似于存储属性的map;<br/>
 * PropertySources即为PropertySource的集合;<br/>
 * PropertyResolver 解析propertySources
 * 
 * @author ViVi
 * @date 2015年8月24日 下午9:33:50
 */

public class PropertySourceTest {

    public void testPropertySource() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("encoding", "gbk");
        PropertySource propertySource1 = new MapPropertySource("map", map);
        ResourcePropertySource propertySource2 = new ResourcePropertySource("resource",
                "classpath:com/bow/spring/environment/resources.properties");
        System.out.println(propertySource2.getProperty("name1"));
        CompositePropertySource compositePropertySource = new CompositePropertySource("composite");
        compositePropertySource.addPropertySource(propertySource1);
        compositePropertySource.addPropertySource(propertySource2);
        System.out.println(compositePropertySource.getProperty("encoding"));

        // 测试PropertySources
        MutablePropertySources propertySources = new MutablePropertySources();
        propertySources.addFirst(propertySource1);
        propertySources.addLast(propertySource2);
        System.out.println(propertySources.get("resource").getProperty("encoding"));
    }

    public void testPropertyResolver() throws IOException {
        ResourcePropertySource propertySource = new ResourcePropertySource("resource",
                "classpath:com/bow/spring/environment/resources.properties");
        MutablePropertySources propertySources = new MutablePropertySources();
        propertySources.addFirst(propertySource);
        PropertyResolver propertyResolver = new PropertySourcesPropertyResolver(propertySources);

        System.out.println(propertyResolver.getProperty("name1", "default"));
        System.out.println(propertyResolver.resolvePlaceholders("value must be ${name1}"));// 占位符
    }

    /**
     * {@link Environment} 用来获取各种配置项的值，包括当前是是dev-profile还是prod-profile.
     * 其extends {@link org.springframework.core.env.PropertyResolver},
     * Environment还可以获取placeholder对应的值
     */
    private static void testEnvironment() {
        Environment env = new StandardEnvironment();
        System.out.println(env.getProperty("file.encoding"));
    }

    public static void main(String[] args) {
        testEnvironment();
    }
}
