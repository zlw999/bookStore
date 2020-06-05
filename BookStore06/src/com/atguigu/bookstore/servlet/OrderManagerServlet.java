package com.atguigu.bookstore.servlet;

import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.OrderItem;
import com.atguigu.bookstore.service.OrderItemService;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderItemServiceImpl;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
    后台处理订单的Servlet
 */
@WebServlet("/OrderManagerServlet")
public class OrderManagerServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();
    OrderItemService orderItemService = new OrderItemServiceImpl();

    //发货的方法
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取订单号
        String orderId = request.getParameter("orderId");
        //调用OrderService中发货的方法
        orderService.updateOrderState(orderId,1);
        //方式一：重定向到查询所有订单的方法
        //方式二：指定调用查询所有订单的方法
        getOrders(request,response);
    }

    //根据订单号查询所有的订单项
    protected void getOrderItemsByOrderId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取订单号
        String orderId = request.getParameter("orderId");
        //调用OrderItemService中获取所有的订单项的方法
        List<OrderItem> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
        //将所有的订单项放到request域中
        request.setAttribute("orderItems",orderItems);
        //转发到显示所有的订单项的页面
        request.getRequestDispatcher("/pages/order/orderItems.jsp").forward(request,response);
    }

    //获取所有订单
    protected void getOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用OrderService中获取所有订单的方法
        List<Order> orders = orderService.getOrders();
        //将所有的订单放到request域中
        request.setAttribute("orders",orders);
        //转发到显示所有订单的页面
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
    }


}
