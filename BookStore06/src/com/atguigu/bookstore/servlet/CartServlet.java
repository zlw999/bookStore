package com.atguigu.bookstore.servlet;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Cart;
import com.atguigu.bookstore.beans.CartItem;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 对购物车进行操作的Servlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    //通过发送Ajax请求更新购物项
    protected void updateCartItemByAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取图书的id和用户输入的图书的数量
        String bookId = request.getParameter("bookId");
        String bookCount = request.getParameter("bookCount");
        //获取Session对象
        HttpSession session = request.getSession();
        //获取session域中的购物车
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            //更新购物项
            cart.updateCartItem(bookId, bookCount);
        }
        //获取当前购物项
        CartItem cartItem = cart.getMap().get(bookId);
        //获取当前购物项中的金额小计
        double amount = cartItem.getAmount();
        //获取购物车中图书的总数量
        int totalCount = cart.getTotalCount();
        //获取购物车中图书的总金额
        double totalAmount = cart.getTotalAmount();
        //创建一个Map
        Map<String , Object> map = new HashMap<String,Object>();
        //将三个数据放到map中
        map.put("amount",amount+"");
        map.put("totalCount",totalCount+"");
        map.put("totalAmount",totalAmount+"");
        //创建Gson对象
        Gson gson = new Gson();
        //将map转换位JSON字符串
        String json = gson.toJson(map);
        //将JSON字符串响应到浏览器
        response.getWriter().write(json);
    }
    //更新购物项
//    protected void updateCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取图书的id和用户输入的图书的数量
//        String bookId = request.getParameter("bookId");
//        String bookCount = request.getParameter("bookCount");
//        //获取Session对象
//        HttpSession session = request.getSession();
//        //获取session域中的购物车
//        Cart cart = (Cart) session.getAttribute("cart");
//        if (cart != null) {
//            //更新购物项
//            cart.updateCartItem(bookId, bookCount);
//        }
//        //重定向到购物车页面
//        response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
//    }

    //删除购物项
    protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要删除的购物项中图书的id
        String bookId = request.getParameter("bookId");
        //获取Session对象
        HttpSession session = request.getSession();
        //获取session域中的购物车
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            //删除购物项
            cart.deleteCartItem(bookId);
        }
        //重定向到购物车页面
        response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
    }

    //清空购物车
    protected void emptyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session对象
        HttpSession session = request.getSession();
        //获取session域中的购物车
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            //清空购物车
            cart.emptyCart();
        }
        //重定向到购物车页面
        response.sendRedirect(request.getContextPath() + "/pages/cart/cart.jsp");
    }

    //添加图书到购物车
    protected void addBook2Cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要添加的图书的id
        String bookId = request.getParameter("bookId");
        //调用BookService中获取图书信息的方法
        Book bookById = bookService.getBookById(Integer.parseInt(bookId));
        //获取Session对象
        HttpSession session = request.getSession();
        //从session域中获取购物车
        Cart cart = (Cart) session.getAttribute("cart");
        //判断session域中是否有购物车
        if (cart == null) {
            //证明session域中还没有购物车，创建一个购物车，并将它放到session域中
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        //将图书添加到购物车中
        cart.addBook2Cart(bookById);
        //获取当前图书的库存
        Integer stock = bookById.getStock();
        //获取当前购物项
        CartItem cartItem = cart.getMap().get(bookId);
        //获取当前购物项中图书的数量
        int count = cartItem.getCount();
        //判断购物项的数量是否大于库存
        if (count > stock) {
            //证明再买一本已经超库存，设置一个提示信息并放到session域中
            session.setAttribute("msg", "该图书库存只有" + stock + "本！");
            //将当前购物项中图书的数量设置为最大库存
            cartItem.setCount(stock);
        }else{
            //将图书的书名添加到session域中
            session.setAttribute("bookTitle", bookById.getTitle());
        }
        //获取请求从哪儿发过来的，即获取请求头中的Referer属性值
        String referer = request.getHeader("Referer");
        //重定向到referer
        response.sendRedirect(referer);
    }

}
