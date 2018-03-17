package com.bow.spring.springmvc;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.convert.support.DefaultConversionService;

/**
 * 自定义converter 传入给 conversionService 对输入进行转换
 * 
 * @author ViVi
 * @date 2015年8月24日 下午10:27:17
 */

public class ConverterTest {

    public void testStringToPhoneNumberConvert() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToPhoneNumberConverter());

        String phoneNumberStr = "010-12345678";
        PhoneNumberModel phoneNumber = conversionService.convert(phoneNumberStr, PhoneNumberModel.class);
        System.out.println(phoneNumber.getPhoneNumber());
    }

    public void testOtherConvert() {
        DefaultConversionService conversionService = new DefaultConversionService();

        // "1"--->true（字符串“1”可以转换为布尔值true）
        System.out.println(conversionService.convert("1", Boolean.class));

        // "1,2,3,4"--->List（转换完毕的集合大小为4）
        System.out.println(conversionService.convert("1,2,3,4", List.class).size());
    }

    public void test() {
        Pattern pattern = Pattern.compile("^(\\d{3,4})-(\\d{7,8})$");
        Matcher m = pattern.matcher("010-12345678");
        System.out.println(m.matches());
    }
}
