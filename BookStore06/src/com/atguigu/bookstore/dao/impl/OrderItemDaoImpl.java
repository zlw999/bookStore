package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.dao.BasicDao;
import com.atguigu.bookstore.dao.OrderItemDao;

import java.util.List;

public class OrderItemDaoImpl extends BasicDao implements OrderItemDao {
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        //写sql语句
        String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
        //调用BasicDao中通用的增删改的方法
        update(sql,orderItem.getCount(),orderItem.getAmount(),orderItem.getTitle(),orderItem.getAuthor(),orderItem.getPrice(),orderItem.getImgPath(),orderItem.getOrderId());
    }

    @Override
    public void batchSaveOrderItems(Object[][] args) {
        //写sql语句
        String sql = "insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)";
        //调用BasicDao中批处理的方法
        batchUpdate(sql,args);
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        //写sql语句
        String sql = "select id,count,amount,title,author,price,img_path imgPath,order_id orderId from order_items where order_id = ?";
        //调用BasicDao中获取一个List的方法
        List<OrderItem> beanList = getBeanList(OrderItem.class, sql, orderId);
        return beanList;
    }
}
