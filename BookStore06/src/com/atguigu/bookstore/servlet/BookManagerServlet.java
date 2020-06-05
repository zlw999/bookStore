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
import java.util.List;

/**
 * 后台管理图书的Servlet
 */
@WebServlet("/BookManagerServlet")
public class BookManagerServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    //分页的方法
    protected void getPageBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页码传入的页码
        String pageNo = request.getParameter("pageNo");
        //调用BookService中分页的方法
        Page<Book> pageBooks = bookService.getPageBooks(pageNo);
        //将pageBooks放到request域中
        request.setAttribute("page",pageBooks);
        //转发到显示图书的页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    //添加或更新图书
    protected void addOrUpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取图书信息
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String sales = request.getParameter("sales");
        String stock = request.getParameter("stock");
        //根据id判断是在添加图书还是在更新图书
        if("".equals(id)){
            //证明在添加图书
            //封装Book对象
            Book book = new Book(null,title,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock));
            //调用BookService中添加图书的方法
            bookService.saveBook(book);
        }else{
            //证明在更新图书
            //封装Book对象
            Book book = new Book(Integer.parseInt(id),title,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock));
            //调用BookService中更新图书的方法
            bookService.updateBook(book);
        }
        //重定向到查询所有图书的方法
        response.sendRedirect(request.getContextPath()+"/BookManagerServlet?methodName=getPageBooks");
    }

    //获取一本图书的方法
    protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取图书的id
        String bookId = request.getParameter("bookId");
        //调用BookService中获取一本图书的方法
        Book bookById = bookService.getBookById(Integer.parseInt(bookId));
        //将图书信息放到request域中
        request.setAttribute("book",bookById);
        //转发到修改图书的页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }

    //删除图书
    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要删除的图书的id
        String bookId = request.getParameter("bookId");
        //调用BookService中删除图书的方法
        bookService.deleteBookById(Integer.parseInt(bookId));
        //重定向到查询所有图书的方法
        response.sendRedirect(request.getContextPath()+"/BookManagerServlet?methodName=getPageBooks");
    }

    //获取所有图书
    protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用BookService中获取所有图书的方法
        List<Book> books = bookService.getBooks();
        //将books放到request域中
        request.setAttribute("books",books);
        //转发到显示所有图书的页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    //添加图书
//    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取图书信息
//        String title = request.getParameter("title");
//        String author = request.getParameter("author");
//        String price = request.getParameter("price");
//        String sales = request.getParameter("sales");
//        String stock = request.getParameter("stock");
//        //封装Book对象
//        Book book = new Book(null,title,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock));
//        //调用BookService中添加图书的方法
//        bookService.saveBook(book);
//        //方式一：重定向到查询所有图书的方法
////        response.sendRedirect(request.getContextPath()+"/BookManagerServlet?methodName=getBooks");
//        //方式二：直接调用getBooks方法再查询一遍数据库
//        getBooks(request,response);
//    }
}
