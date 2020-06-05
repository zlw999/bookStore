package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.User;

public interface UserService {
    /**
     * 处理用户登录的方法
     * @param username
     * @param password
     * @return User 登录成功  false登录失败
     */
    User login(String username , String password);

    /**
     * 处理用户注册的方法
     * @param username
     * @return true 注册失败    false 注册成功
     */
    boolean regist(String username);

    /**
     * 保存用户信息的方法
     * @param username
     * @param passwrod
     * @param email
     */
    void saveUser(String username , String passwrod , String email);
}
