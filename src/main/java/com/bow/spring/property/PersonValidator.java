package com.bow.spring.property;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/** 
 * @author ViVi
 * @date 2015年8月26日 下午8:18:26  
 */

public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "名字不能为空");
        Person p = (Person) target;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "年龄不能为负");
        } else if (p.getAge() > 110) {
            errors.rejectValue("age", "这是成妖的节奏啊");
        }

    }

}
