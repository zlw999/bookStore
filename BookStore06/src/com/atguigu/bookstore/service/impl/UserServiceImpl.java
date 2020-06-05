package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = userDao.checkUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public boolean regist(String username) {
        return userDao.checkUsername(username);
    }

    @Override
    public void saveUser(String username, String passwrod, String email) {
        userDao.saveUser(username,passwrod,email);
    }
}
