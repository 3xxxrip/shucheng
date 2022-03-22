package com.lj.test;

import com.lj.dao.OrderDao;
import com.lj.dao.impl.OrderDaoImpl;
import com.lj.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoImplTest {

    @Test
    public void saveOrder() {
         Order order=new Order("1",new Date(System.currentTimeMillis()),new BigDecimal(20),1,2);
        OrderDao orderDao=new OrderDaoImpl();
        int i = orderDao.saveOrder(order);
        System.out.println(i);
    }
}