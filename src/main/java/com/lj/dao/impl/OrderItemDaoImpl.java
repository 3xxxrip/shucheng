package com.lj.dao.impl;

import com.lj.dao.OrderItemDao;
import com.lj.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        //由于id是自增长就不需要插入了
        String sql="INSERT INTO t_order_item (`name`,`count`,`price`,`total_price`,`order_id`) VALUES(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
