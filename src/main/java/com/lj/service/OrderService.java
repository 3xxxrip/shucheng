package com.lj.service;

import com.lj.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
