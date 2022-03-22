package com.lj.dao.impl;

import com.lj.dao.OrderDao;
import com.lj.pojo.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao{
    /**
    * @Description: 实现对订单的保存
            * @Param: [order]
            * @return: int
            * @Author: longjian
            * @Date:15:51 2022/2/22
            */
    @Override
    public int saveOrder(Order order) {
        String sql="INSERT INTO t_order (`order_id`,`create_time`,`price`,`status`,`user_id`) VALUES(?,?,?,?,?)";
        return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
