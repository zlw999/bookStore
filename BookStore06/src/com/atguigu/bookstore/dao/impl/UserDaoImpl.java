package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.BasicDao;
import com.atguigu.bookstore.dao.UserDao;

public class UserDaoImpl extends BasicDao implements UserDao {
    @Override
    public User checkUsernameAndPassword(String username, String password) {
        //写sql语句
        String sql = "select id,username,password,email from users where username = ? and password = ?";
        //调用BasicDao获取一个对象的方法
        User bean = getBean(User.class, sql,username, password);
        return bean;
    }

    @Override
    public boolean checkUsername(String username) {
        //写sql语句
        String sql = "select id,username,password,email from users where username = ?";
        //调用BasicDao获取一个对象的方法
        User bean = getBean(User.class,sql, username);
        return bean != null;
    }

    @Override
    public void saveUser(String username, String password, String email) {
        //写sql语句
        String sql = "insert into users(username,password,email) values(?,?,?)";
        //调用BasicDao中通用的增删改的方法
        update(sql,username,password,email);
    }
}
