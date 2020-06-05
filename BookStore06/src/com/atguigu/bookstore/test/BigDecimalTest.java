package com.atguigu.bookstore.test;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        //创建BigDecimal对象
        BigDecimal a = new BigDecimal(100);
        BigDecimal b = new BigDecimal(10);
        //加
        int value = a.add(b).intValue();
        System.out.println(value);
        //减
        BigDecimal subtract = a.subtract(b);
        System.out.println(subtract);
        //乘
        BigDecimal multiply = a.multiply(b);
        System.out.println(multiply);
        //除
        BigDecimal divide = a.divide(b);
        System.out.println(divide);

        //使用BigDecimal处理double类型的精度问题
        //使用的BigDecimal传入字符串的构造器
        BigDecimal c = new BigDecimal("0.01");
        BigDecimal d = new BigDecimal("0.09");
        //加
        BigDecimal add = c.add(d);
        System.out.println(add);
    }
}
