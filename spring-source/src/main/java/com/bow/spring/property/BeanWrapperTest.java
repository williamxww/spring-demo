/**  
 * @FileName: BeanWrapperTest.java 
 * @Package spring.property 
 * all rights reserved by Hill team
 * @version v1.3  
 */
package com.bow.spring.property;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: BeanWrapperTest
 * @author ViVi
 * @date 2015年8月26日 下午8:30:24
 */

public class BeanWrapperTest {

    public void testBeanWrapper() {
        BeanWrapper company = new BeanWrapperImpl(new Company());
        // 给company 的name设置
        company.setPropertyValue("name", "BOW");
        // 另一种设置方式
        PropertyValue value = new PropertyValue("name", "bow.com");
        company.setPropertyValue(value);

        // 创建一个Person
        Person p = new Person();
        p.setAge(25);
        BeanWrapper vv = new BeanWrapperImpl(p);
        vv.setPropertyValue("name", "vv");

        // 将person设置到company的employee属性上
        company.setPropertyValue("employee", vv.getWrappedInstance());

        // 级联获取属性值
        int age = (int) company.getPropertyValue("employee.age");
        System.out.println(age);
    }

    /**
     * 
     * xml中给Company的employee注入的是 字符串，通过propertyEditor转换成Person对象然后注入
     * 
     * 注意：在此过程中是通过PropertyEditorRegistrar进行editor注册的
     */
    public void testPropertyEditor() {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "com/bow/spring/property/property.xml" });
        Company company = (Company) context.getBean("sample");
        System.out.println(company.getEmployee().getName());
    }

}
