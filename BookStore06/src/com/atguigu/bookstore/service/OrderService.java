package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.User;

import java.util.List;

public interface OrderService {

    /**
     * 去结账的方法
     * @param cart 购物车
     * @param user 用户
     * @return
     */
    String createOrder(Cart cart , User user);

    /**
     * 获取所有订单的方法
     * @return
     */
    List<Order> getOrders();

    /**
     * 获取我的所有订单的方法
     * @param userId
     * @return
     */
    List<Order> getMyOrders(int userId);

    /**
     * 发货和收货的方法
     * @param orderId
     * @param state
     */
    void updateOrderState(String orderId , int state);
}
