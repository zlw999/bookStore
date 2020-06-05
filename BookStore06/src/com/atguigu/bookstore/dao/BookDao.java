package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;

import java.util.List;

public interface BookDao {
    /**
     * 查询数据库中所有的图书
     * @return
     */
    List<Book> getBooks();

    /**
     * 将图书信息插入到数据库中
     * @param book
     */
    void saveBook(Book book);

    /**
     * 根据图书的id删除数据库中的图书
     * @param id
     */
    void deleteBookById(Integer id);

    /**
     * 根据图书的id从数据库中查询图书的信息
     * @param id
     * @return
     */
    Book getBookById(Integer id);

    /**
     * 根据图书的id更新数据库中的图书信息
     * @param book
     */
    void updateBook(Book book);

    /**
     * 获取带分页的图书信息
     * @param page 传入的page对象是只包含pageNo属性的
     * @return 返回的page对象包含了分页相关的所有信息
     */
    Page<Book> getBooksByPage(Page<Book> page);

    /**
     * 获取带分页和价格的图书信息
     * @param page 传入的page对象是只包含pageNo属性的
     * @return 返回的page对象包含了分页相关的所有信息
     */
    Page<Book> getBooksByPageAndPrice(Page<Book> page,double minPrice , double maxPrice);

    /**
     * 批量更新图书的库存和销量的方法
     */
    void batchUpdateBook(Object[][] args);

}
