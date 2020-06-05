package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.OrderItem;

import java.util.List;

public interface OrderItemDao {
    /**
     * 将订单中的各个订单下插入到数据库中
     * @param orderItem
     */
    void saveOrderItem(OrderItem orderItem);

    /**
     * 批量插入订单项的方法
     * @param args
     */
    void batchSaveOrderItems(Object[][] args);

    /**
     * 根据订单号查询对应的订单项
     * @param orderId
     * @return
     */
    List<OrderItem> getOrderItemsByOrderId(String orderId);
}
