package com.atguigu.bookstore.servlet;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前台管理图书的Servlet
 */
@WebServlet("/BookClientServlet")
public class BookClientServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();


    //分页及带价格查询的方法
    protected void getPageBooksAndPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页码传入的页码及价格范围
        String pageNo = request.getParameter("pageNo");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        //调用BookService中分页和带价格查询的方法
        Page<Book> pageBooks = bookService.getPageBooksAndPrice(pageNo,minPrice,maxPrice);
        //将pageBooks放到request域中
        request.setAttribute("page",pageBooks);
        //将价格范围页放到request域中
        request.setAttribute("minPrice",minPrice);
        request.setAttribute("maxPrice",maxPrice);
        //转发到显示图书的页面
        request.getRequestDispatcher("/pages/client/books.jsp").forward(request,response);
    }

    //分页的方法
    protected void getPageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页码传入的页码
        String pageNo = request.getParameter("pageNo");
        //调用BookService中分页的方法
        Page<Book> pageBooks = bookService.getPageBooks(pageNo);
        //将pageBooks放到request域中
        request.setAttribute("page",pageBooks);
        //转发到显示图书的页面
        request.getRequestDispatcher("/pages/client/books.jsp").forward(request,response);
    }

}
