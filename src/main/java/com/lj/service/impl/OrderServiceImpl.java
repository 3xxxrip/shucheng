package com.lj.service.impl;

import com.lj.dao.BookDao;
import com.lj.dao.OrderDao;
import com.lj.dao.OrderItemDao;
import com.lj.dao.impl.BookDaoImpl;
import com.lj.dao.impl.OrderDaoImpl;
import com.lj.dao.impl.OrderItemDaoImpl;
import com.lj.pojo.*;
import com.lj.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号===唯一性
        String orderId=System.currentTimeMillis()+""+userId;
        //创建一个订单对象,根据购物车内容创建订单
        Order order=new Order(orderId,new Date(),cart.getTotalprice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

        //遍历购物车中每一个商品项转化成订单项保存到数据库
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer,CartItem> item:items.entrySet()) {
            //获取购物车中每个商品项
            CartItem value = item.getValue();
            //转化为表单项
            OrderItem orderItem=new OrderItem(null,value.getName(),value.getCount(),value.getPrice(),value.getTotalprice(),orderId);
            //保存到数据库
            orderItemDao.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookDao.queryBookById(value.getId());
            //减少书籍库存
            book.setStock(book.getStock()-value.getCount());
            //增加销量
            book.setSales(book.getSales()+value.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
