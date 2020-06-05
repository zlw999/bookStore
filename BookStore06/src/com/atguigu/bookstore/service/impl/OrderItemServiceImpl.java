package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        return orderItemDao.getOrderItemsByOrderId(orderId);
    }
}
