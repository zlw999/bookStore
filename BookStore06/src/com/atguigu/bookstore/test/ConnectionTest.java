package com.atguigu.bookstore.test;

import com.atguigu.bookstore.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class ConnectionTest {
    
    /**
     * 
     */
    @Test
    public void testConnection(){
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }
}
