package com.lj.web;

import com.lj.pojo.Cart;
import com.lj.pojo.User;
import com.lj.service.OrderService;
import com.lj.service.impl.OrderServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class OrderServlet extends BaseServlet {
    OrderService orderService=new OrderServiceImpl();
/** 
* @Description: 生产订单的方法
        * @Param: [request, response]
        * @return: void
        * @Author: longjian
        * @Date:9:52 2022/2/23
        */

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建订单需要调用OrderService.createOrder，需要获取cart和userid
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            //转发之后就不需要执行了直接return,就直接结束了
            return;
        }
        System.out.println(user);
        String order = orderService.createOrder(cart, user.getId());
        request.getSession().setAttribute("order", order);
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }

}
