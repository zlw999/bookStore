package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.OrderItem;

import java.util.List;

public interface OrderItemService {
    /**
     * 获取某个订单中所有的订单项的方法
     * @param orderId
     * @return
     */
    List<OrderItem> getOrderItemsByOrderId(String orderId);
}
