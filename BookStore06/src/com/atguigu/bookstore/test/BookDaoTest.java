package com.atguigu.bookstore.test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.util.List;

public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();

    /**
     * 测试查询所有图书
     */
    @Test
    public void testGetBooks(){
        List<Book> books = bookDao.getBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    /**
     * 测试添加图书
     */
    @Test
    public void testSaveBook(){
        Book book = new Book(null,"三国演义","罗贯中",88.00,100,100);
        bookDao.saveBook(book);
    }

    /**
     * 测试删除图书
     */
    @Test
    public void testDeleteBookById(){
        bookDao.deleteBookById(33);
    }

    /**
     * 测试获取一本图书
     */
    @Test
    public void testGetBookById(){
        Book bookById = bookDao.getBookById(38);
        System.out.println(bookById);
    }

    /**
     * 测试更新图书
     */
    @Test
    public void testUpdateBook(){
        Book book = new Book(31,"三国演义第二季","罗小中",22.00,1000,10);
        bookDao.updateBook(book);
    }

    /**
     * 测试带分页的图书信息
     */
    @Test
    public void testGetPageBooks(){
        //创建一个Page对象
        Page<Book> page = new Page<Book>();
        //给page对象设置页码
        page.setPageNo(-1);

        //获取当前页中的图书
        page= bookDao.getBooksByPage(page);
        //获取当前页码：
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

    /**
     * 测试带分页和价格范围的图书信息
     */
    @Test
    public void testGetPageBooksAndPrice(){
        //创建一个Page对象
        Page<Book> page = new Page<Book>();
        //给page对象设置页码
        page.setPageNo(2);

        //获取当前页中的图书
        page= bookDao.getBooksByPageAndPrice(page,11.00,30.00);
        //获取当前页码：
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

    /**
     * 测试BookDao中的批处理的方法
     */
    @Test
    public void testBatchUpdateSalesAndStock(){
        //update books set sales = ? , stock = ? where id = ?
        Object[][] args = new Object[3][];
        args[0] = new Object[]{200,200,1}; //将id=1的图书的库存和销量都设置为200
        args[1] = new Object[]{300,300,2};
        args[2] = new Object[]{500,500,3};
        bookDao.batchUpdateBook(args);
    }
}
