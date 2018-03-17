package com.bow.spring.aop;

/**
 * JDK动态代理
 * @author ViVi
 * @date 2015年6月3日 下午10:52:51
 */

public interface UserService {

    void addUser();
    
    void deleteUser(Integer userId);
}
