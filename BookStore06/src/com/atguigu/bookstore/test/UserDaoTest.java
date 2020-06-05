package com.atguigu.bookstore.test;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import org.junit.Test;

public class UserDaoTest {
    /**
     *
     */
    @Test
    public void testUserDao(){
        //创建UserDao
        UserDao userDao = new UserDaoImpl();
        //测试验证用户名和密码的方法
        User user = userDao.checkUsernameAndPassword("admin", "666888");
        System.out.println(user);
        //测试验证用户名是否存在
        boolean flag = userDao.checkUsername("admin2");
        System.out.println(flag);
        //测试保存用户信息的方法
        userDao.saveUser("admin2","888888","admin2@sina.com");
    }
}
