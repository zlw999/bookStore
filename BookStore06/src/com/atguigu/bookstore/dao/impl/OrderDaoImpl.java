package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.dao.BasicDao;
import com.atguigu.bookstore.dao.OrderDao;

import java.util.List;

public class OrderDaoImpl extends BasicDao implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        //写sql语句
        String sql = "insert into orders(id,order_time,total_count,total_amount,state,user_id) values(?,?,?,?,?,?)";
        //调用BasicDao中通用的增删改的方法
        update(sql,order.getId(),order.getOrderTime(),order.getTotalCount(),order.getTotalAmount(),order.getState(),order.getUserId());
    }

    @Override
    public List<Order> getOrders() {
        //写sql语句
        String sql = "select id,order_time orderTime,total_count totalCount,total_amount totalAmount,state,user_id userId from orders";
        //调用BasicDao中获取一个List的方法
        List<Order> beanList = getBeanList(Order.class, sql);
        return beanList;
    }

    @Override
    public List<Order> getMyOrders(int userId) {
        //写sql语句
        String sql = "select id,order_time orderTime,total_count totalCount,total_amount totalAmount,state,user_id userId from orders where user_id = ?";
        //调用BasicDao中获取一个List的方法
        List<Order> beanList = getBeanList(Order.class, sql,userId);
        return beanList;
    }

    @Override
    public void updateOrderState(String orderId, int state) {
        //写sql语句
        String sql = "update orders set state = ? where id = ?";
        //调用BasicDao中通用的增删改的方法
        update(sql,state,orderId);
    }
}
