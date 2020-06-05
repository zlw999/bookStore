package com.atguigu.bookstore.test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;

public class BookServiceTest {

    BookService bookService = new BookServiceImpl();

    /**
     * 测试分页的方法
     */
    @Test
    public void testGetPageBooks(){
        Page<Book> page = bookService.getPageBooks("2");
        //获取当前页
        int pageNo = page.getPageNo();
        System.out.println("当前页是："+pageNo);
        //获取总记录数
        int totalRecord = page.getTotalRecord();
        System.out.println("总记录数是："+totalRecord);
        //获取总页数
        int totalPageNo = page.getTotalPageNo();
        System.out.println("总页数是："+totalPageNo);
        //当前页中的图书有
        List<Book> list = page.getList();
        for (Book book : list) {
            System.out.println(book);
        }
    }
}
