package com.atguigu.bookstore.test;

import com.atguigu.bookstore.beans.*;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class OrderTest {
    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    OrderService orderService = new OrderServiceImpl();

    /**
     * 测试插入订单和订单项
     */
    @Test
    public void testSaveOrderAndOrderItems(){
        //创建订单
        Order order = new Order("13838381438",new Date(),3,90.00,0,1);
        //创建订单中对应的订单项
        OrderItem orderItem = new OrderItem(null,2,60.00,"金瓶梅","贾子强",30.00,"static/img/jpm.jpg","13838381438");
        OrderItem orderItem2 = new OrderItem(null,1,30.00,"少年阿宾","贾小强",30.00,"static/img/snab.jpg","13838381438");
        //保存订单和订单项
        orderDao.saveOrder(order);
        orderItemDao.saveOrderItem(orderItem);
        orderItemDao.saveOrderItem(orderItem2);
    }

    /**
     * 测试去结账的方法
     */
    @Test
    public void testOrderService(){
        //创建购物车
        Cart cart = new Cart();
        //创建用户
        User user = new User();
        user.setId(1);
        //创建Book对象
        Book book = new Book(1,"解忧杂货店","东野圭吾",27.2,100,100);
        Book book2 = new Book(2,"边城","沈从文",23,100,10);
        //给购物车中添加图书
        cart.addBook2Cart(book);
        cart.addBook2Cart(book);
        cart.addBook2Cart(book2);

        //去结账
        orderService.createOrder(cart,user);

    }

    /**
     * 测试获取所有的订单
     */
    @Test
    public void testGetOrders(){
        List<Order> orders = orderDao.getOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    /**
     * 测试获取我的所有订单
     */
    @Test
    public void testGetMyOrders(){
        List<Order> myOrders = orderDao.getMyOrders(2);
        for (Order myOrder : myOrders) {
            System.out.println(myOrder);
        }
    }

    /**
     * 测试获取订单的所有订单项
     */
    @Test
    public void testGetOrderItemsByOrderId(){
        List<OrderItem> orderItemsByOrderId = orderItemDao.getOrderItemsByOrderId("1583196695398");
        for (OrderItem orderItem : orderItemsByOrderId) {
            System.out.println(orderItem);
        }
    }

    /**
     * 测试更新订单状态
     */
    @Test
    public void testUpdateOrderState(){
        orderDao.updateOrderState("1583215940876",1);
    }
}
