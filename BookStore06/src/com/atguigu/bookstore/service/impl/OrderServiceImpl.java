package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.beans.*;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, User user) {
        //生成订单号
        String orderId = System.currentTimeMillis()+"";
        //获取购物车中图书的总数量
        int totalCount = cart.getTotalCount();
        //获取购物车中图书的总金额
        double totalAmount = cart.getTotalAmount();
        //获取用户的id
        Integer userId = user.getId();
        //创建Order对象
        Order order = new Order(orderId,new Date(),totalCount,totalAmount,0,userId);
        //1.将order中的信息插入到数据库中
        orderDao.saveOrder(order);

        //获取购物车中所有的购物项
        List<CartItem> cartItems = cart.getCartItems();
        //创建两个二维数组，分别进行批量插入订单项及批量更新图书的库存和销量
        Object[][] orderItems = new Object[cartItems.size()][];
        Object[][] books = new Object[cartItems.size()][];
        //遍历得到每一个购物项
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            //获取当前购物项中的图书的数量
            int count = cartItem.getCount();
            //获取当前购物项中图书的金额小计
            double amount = cartItem.getAmount();
            //获取当前购物项中的图书对象
            Book book = cartItem.getBook();
            //获取书名
            String title = book.getTitle();
            //获取作者
            String author = book.getAuthor();
            //获取图书的价格
            double price = book.getPrice();
            //获取图书的封面
            String imgPath = book.getImgPath();
            //创建订单项
//            OrderItem orderItem = new OrderItem(null,count,amount,title,author,price,imgPath,orderId);
            //insert into order_items(count,amount,title,author,price,img_path,order_id) values(?,?,?,?,?,?,?)
            orderItems[i] = new Object[]{count,amount,title,author,price,imgPath,orderId};
            //2.将订单项信息插入到数据库中
//            orderItemDao.saveOrderItem(orderItem);
            //更新图书的库存和销量
            //获取当前的销量
            Integer sales = book.getSales();
            //将当前图书的销量设置为sale+count
//            book.setSales(sales+count);
            //获取当前图书的库存
            Integer stock = book.getStock();
            //将当前图书的库存设置为stock-count
//            book.setStock(stock-count);
            //3.更新图书的库存和销量
//            bookDao.updateBook(book);
            //update books set sales = ? , stock = ? where id = ?
            books[i] = new Object[]{sales+count,stock-count,book.getId()};
        }
        //批量插入订单项
        orderItemDao.batchSaveOrderItems(orderItems);
        //批量更新图书的库存和销量
        bookDao.batchUpdateBook(books);

        //4.清空购物车
        cart.emptyCart();
        return orderId;
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    @Override
    public List<Order> getMyOrders(int userId) {
        return orderDao.getMyOrders(userId);
    }

    @Override
    public void updateOrderState(String orderId, int state) {
        orderDao.updateOrderState(orderId,state);
    }
}
