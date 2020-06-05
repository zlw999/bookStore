package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BasicDao;
import com.atguigu.bookstore.dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BasicDao implements BookDao {
    @Override
    public List<Book> getBooks() {
        //写sql语句
        String sql = "select id,title,author,price,sales,stock,img_path imgPath from books";
        //调用BasicDao中获取一个List的方法
        List<Book> beanList = getBeanList(Book.class, sql);
        return beanList;
    }

    @Override
    public void saveBook(Book book) {
        //写sql语句
        String sql = "insert into books(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        //调用BasicDao中通用的增删改的方法
        update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public void deleteBookById(Integer id) {
        //写sql语句
        String sql = "delete from books where id = ?";
        //调用BasicDao中通用的增删改的方法
        update(sql, id);
    }

    @Override
    public Book getBookById(Integer id) {
        //写sql语句
        String sql = "select id,title,author,price,sales,stock,img_path imgPath from books where id = ?";
        //调用BasicDao中获取一个对象的方法
        Book bean = getBean(Book.class, sql, id);
        return bean;
    }

    @Override
    public void updateBook(Book book) {
        //写sql语句
        String sql = "update books set title = ? , author = ? , price = ? , sales = ? , stock = ? where id = ?";
        //调用BasicDao中通用的增删改的方法
        update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getId());
    }

    @Override
    public Page<Book> getBooksByPage(Page<Book> page) {
        //1.获取总记录数
        String sql = "select count(*) from books";
        //调用BasicDao中获取一个单一值的方法
        long count = (long) getSingleValue(sql);
        //将总记录数设置到page对象中
        page.setTotalRecord((int)count);

        //2.获取当前页中所有的图书
        String sql2 = "select id,title,author,price,sales,stock,img_path imgPath from books limit ?,?";
        //调用BasicDao中获取一个List的方法
        List<Book> beanList = getBeanList(Book.class, sql2, (page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        //将beanList放到page对象中
        page.setList(beanList);
        return page;
    }

    @Override
    public Page<Book> getBooksByPageAndPrice(Page<Book> page, double minPrice, double maxPrice) {
        //1.获取总记录数
        String sql = "select count(*) from books where price between ? and ?";
        //调用BasicDao中获取一个单一值的方法
        long count = (long) getSingleValue(sql,minPrice,maxPrice);
        //将总记录数设置到page对象中
        page.setTotalRecord((int)count);

        //2.获取当前页中所有的图书
        String sql2 = "select id,title,author,price,sales,stock,img_path imgPath from books where price between ? and ? limit ?,?";
        //调用BasicDao中获取一个List的方法
        List<Book> beanList = getBeanList(Book.class, sql2, minPrice,maxPrice,(page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        //将beanList放到page对象中
        page.setList(beanList);
        return page;
    }

    @Override
    public void batchUpdateBook(Object[][] args) {
        //写sql语句
        String sql = "update books set sales = ? , stock = ? where id = ?";
        //调用BasicDao中批处理的方法
        batchUpdate(sql,args);
    }
}
