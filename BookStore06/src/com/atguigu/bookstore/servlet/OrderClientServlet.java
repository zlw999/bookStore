package com.atguigu.bookstore.servlet;

import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.Order;
import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 前端用户处理订单的Servlet
 */
@WebServlet("/OrderClientServlet")
public class OrderClientServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();

    //收货的方法
    protected void takeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取订单号
        String orderId = request.getParameter("orderId");
        //调用OrderService中收货的方法
        orderService.updateOrderState(orderId,2);
        //再次调用获取我的订单的方法查询一遍
        getMyOrders(request,response);
    }

    //获取我的订单的方法
    protected void getMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session对象
        HttpSession session = request.getSession();
        //获取session域中的用户
        User user = (User)session.getAttribute("user");
        //调用OrderService中获取我的订单的方法
        List<Order> orders = orderService.getMyOrders(user.getId());
        //将我的所有订单放到request域中
        request.setAttribute("orders",orders);
        //转发到显示所有订单的页面
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);

    }

    //去结账
    protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session对象
        HttpSession session = request.getSession();
        //获取session域中的购物车和用户
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        //调用UserService中去结账的方法
        String orderId = orderService.createOrder(cart, user);
        //将订单号放到session域中
        session.setAttribute("orderId",orderId);
        //重定向到显示订单号的页面
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
