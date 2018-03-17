package com.bow.biz.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vv
 * @since 2018/3/17.
 */
@RestController
public class IndexController {

    @RequestMapping("/index/{name}")
    @ResponseBody
    public String index(@PathVariable String name) {
        if (null == name) {
            name = "boy";
        }
        return "hello " + name;
    }
}
