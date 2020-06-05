package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 将订单信息插入到数据库中
     * @param order
     */
    void saveOrder(Order order);

    /**
     * 获取数据库中所有的订单
     * @return
     */
    List<Order> getOrders();

    /**
     * 根据用户的id查询用户的所有订单
     * @param userId
     * @return
     */
    List<Order> getMyOrders(int userId);

    /**
     * 更新订单的状态，及修改数据库中state的字段的值
     * @param orderId
     * @param state
     */
    void updateOrderState(String orderId , int state);
}
