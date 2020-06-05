package com.atguigu.bookstore.test;

import com.atguigu.bookstore.beans.Book;
import com.atguigu.bookstore.beans.Cart;
import org.junit.Test;

public class CartTest {

    /**
     * 测试Cart类中的方法
     */
    @Test
    public void testCart(){
        //创建购物车
        Cart cart = new Cart();
        //创建要买的图书
        Book book = new Book(1,"母猪的产后护理","赵本上",0.01,100,100);
        Book book2 = new Book(2,"公猪很快乐","贾子强",0.09,100,100);

        //将book添加到购物车中
        cart.addBook2Cart(book);
        cart.addBook2Cart(book2);
//        cart.addBook2Cart(book2);

        //更新book图书的数量
//        cart.updateCartItem("1","5");
//
//        //删除book2这个购物项
//        cart.deleteCartItem("2");
//
//        //清空购物车
//        cart.emptyCart();

        //获取购物车中的图书的总数量
        int totalCount = cart.getTotalCount();
        //获取购物车中图书的总金额
        double totalAmount = cart.getTotalAmount();

        System.out.println("购物车中图书的总数量是:"+totalCount);
        System.out.println("购物车中图书的总金额是:"+totalAmount);
    }
}
