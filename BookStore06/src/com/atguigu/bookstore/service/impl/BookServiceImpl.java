package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public Book getBookById(Integer id) {
       return bookDao.getBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Page<Book> getPageBooks(String pageNo) {
        //设置一个默认的页码
        int defaultPageNo = 1;
        //创建Page对象
        Page<Book> page = new Page<>();
        try {
            defaultPageNo = Integer.parseInt(pageNo);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        //给page对象设置当前的页码
        page.setPageNo(defaultPageNo);
        return bookDao.getBooksByPage(page);
    }

    @Override
    public Page<Book> getPageBooksAndPrice(String pageNo, String minPrice, String maxPrice) {
        //设置一个默认的页码
        int defaultPageNo = 1;
        //创建Page对象
        Page<Book> page = new Page<>();
        try {
            defaultPageNo = Integer.parseInt(pageNo);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        //给page对象设置当前的页码
        page.setPageNo(defaultPageNo);
        //设置一个默认的价格范围
        double defaultMinPrice = 0;
        double defaultMaxPrice = Double.MAX_VALUE;
        try {
            defaultMinPrice=Double.parseDouble(minPrice);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        try {
            defaultMaxPrice = Double.parseDouble(maxPrice);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return bookDao.getBooksByPageAndPrice(page,defaultMinPrice,defaultMaxPrice);
    }
}
