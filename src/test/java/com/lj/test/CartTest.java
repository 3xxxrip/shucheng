package com.lj.test;

import com.lj.pojo.Cart;
import com.lj.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {


    @Test
    public void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1, "helloJava", 1, new BigDecimal(20), new BigDecimal(20)));
        cart.addItem(new CartItem(1, "helloJava", 1, new BigDecimal(20), new BigDecimal(20)));
        cart.addItem(new CartItem(2, "helloJava2", 1, new BigDecimal(60), new BigDecimal(60)));
        cart.updateItem(2, 100);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        String s1="http://localhost:8080/shucheng/";
        System.out.println(s1.contains("shucheng?"));
    }

    @Test
    public void clear() {

    }

    @Test
    public void updateItem() {
        String s1 = "abc";//字面量的定义方式
        String s2 = "abc";
        s1 = "hello";
        System.out.println(s1 == s2);//比较s1和s2的地址值
    }
}