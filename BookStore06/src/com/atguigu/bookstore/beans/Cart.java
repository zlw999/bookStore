package com.atguigu.bookstore.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 购物车类
 */
public class Cart implements Serializable {
    //Map中的key是图书的id，Map中的value是图书对应的购物项
    private Map<String , CartItem> map = new LinkedHashMap<String,CartItem>();
//    private int totalCount; //购物车中图书的总数量，通过计算得到
//    private double totalAmount; //购物车中的总金额，通过计算得到

    //将图书添加到购物车的方法
    public void addBook2Cart(Book book){
        //根据图书的id从购物车中查询是否有对应的购物项
        CartItem cartItem = map.get(book.getId() + "");
        //判断是否有对应的购物项
        if(cartItem == null){
            //证明当前购物车中还没有该购物项
            //创建购物项
            cartItem = new CartItem();
            //将图书设置到该购物项中
            cartItem.setBook(book);
            //设置当前购物项中图书的数量为1
            cartItem.setCount(1);
            //将当前购物项添加到购物车中
            map.put(book.getId()+"",cartItem);
        }else{
            //证明当前购物车中有该购物项，此时只需将购物项中图书的数量加1
            int count = cartItem.getCount();
            //设置当前购物项中图书的数量为count + 1
            cartItem.setCount(count + 1);
        }
    }

    //更新购物车中购物项的方法
    public void updateCartItem(String bookId,String bookCount){
        //从map中获取要更新的购物项
        CartItem cartItem = map.get(bookId);
        try {
            int parseInt = Integer.parseInt(bookCount);
            if(parseInt > 0){
                //当转换int类型成功并且大于0时再更新购物项的数量
                cartItem.setCount(parseInt);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    //删除购物项
    public void deleteCartItem(String bookId){
        //将map中对应的购物项移除
        map.remove(bookId);
    }

    //清空购物车的方法
    public void emptyCart(){
        //清空map
        map.clear();
    }

    //获取购物车中所有的购物项的方法
    public List<CartItem> getCartItems(){
        //获取Map中的所有的购物项
        Collection<CartItem> values = map.values();
        return new ArrayList<>(values);
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    //购物车中的总数量是各个购物项中的数量之和
    public int getTotalCount() {
        int totalCount = 0;
        //获取购物车所有的购物项
        List<CartItem> cartItems = getCartItems();
        for (CartItem cartItem : cartItems) {
            //获取当前购物项中图书的数量
            int count = cartItem.getCount();
            totalCount += count;
        }
        return totalCount;
    }

    //购物车中的总金额是各个购物项中的金额小计之和
    public double getTotalAmount() {
//        double totalAmount = 0;
        BigDecimal totalAmount = new BigDecimal("0");
        //获取购物车所有的购物项
        List<CartItem> cartItems = getCartItems();
        for (CartItem cartItem : cartItems) {
            //获取当前购物项中图书的金额小计
            double amount = cartItem.getAmount();
            BigDecimal bigAmount = new BigDecimal(amount + "");
            totalAmount = totalAmount.add(bigAmount);
        }
        return totalAmount.doubleValue();
    }
}
