package com.atguigu.bookstore.service;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;

import java.util.List;

public interface BookService {
    /**
     * 获取所有图书的方法
     * @return
     */
    List<Book> getBooks();

    /**
     * 添加图书的方法
     * @param book
     */
    void saveBook(Book book);

    /**
     * 删除图书的方法
     * @param id
     */
    void deleteBookById(Integer id);

    /**
     * 查询图书的方法
     * @param id
     * @return
     */
    Book getBookById(Integer id);

    /**
     * 更新图书的方法
     * @param book
     */
    void updateBook(Book book);

    /**
     * 分页的方法
     * @param pageNo 从Servlet传入的页码
     * @return
     */
    Page<Book> getPageBooks(String pageNo);

    /**
     * 分页及带价格范围查询的方法
     * @param pageNo 从Servlet传入的页码
     * @return
     */
    Page<Book> getPageBooksAndPrice(String pageNo,String minPrice , String maxPrice);
}
