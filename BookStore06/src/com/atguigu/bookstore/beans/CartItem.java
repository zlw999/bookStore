package com.atguigu.bookstore.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物项类
 */
public class CartItem implements Serializable {

    private Book book; //当前购物项的图书信息
    private int count; //当前购物项中图书的数量
    private double amount; //当前购物项中图书的金额小计，通过计算得到

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //图书的金额小计由图书的价格和图书的数量结算得到
    public double getAmount() {
        BigDecimal bigPrice = new BigDecimal(book.getPrice() + "");
        BigDecimal bigCount = new BigDecimal(count + "");
        return bigPrice.multiply(bigCount).doubleValue();
    }

//    public void setAmount(double amount) {
//        this.amount = amount;
//    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", amount=" + amount +
                '}';
    }
}
