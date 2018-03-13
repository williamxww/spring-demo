package com.bow.spring.aop;

/**
 * JDK动态代理
 * 
 * @author ViVi
 * @date 2015年6月3日 下午10:54:04
 */

public class UserServiceImpl implements UserService {

    private String domain = "";

    public UserServiceImpl() {

    }

    public UserServiceImpl(String domain) {
        this.domain = domain;
    }

    @Override
    public void addUser() {
        String message = domain + "增加用户";
        System.out.println(message);

    }

    @Override
    public void deleteUser(Integer userId) {
        String message = domain + "删除用户" + userId;
        System.out.println(message);
    }

}
