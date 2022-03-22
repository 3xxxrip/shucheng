package com.lj.web;

import com.lj.pojo.Book;
import com.lj.pojo.Cart;
import com.lj.pojo.CartItem;
import com.lj.service.BookService;
import com.lj.service.impl.BookServiceImpl;
import com.lj.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();
/**
* @Description: 实现页面加入购物车的方法
        * @Param: [request, response]
        * @return: void
        * @Author: longjian
        * @Date:15:04 2022/2/21
        */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //首先获取图书id
        Integer Bookid = WebUtils.parseInt(request.getParameter("id"), 0);
        //得到之后通过bookService得到图书信息
        Book book = bookService.queryBookById(Bookid);
        //将图书信息转化为cartItem项存储到cart里面去
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //判断session域中是否存在购物车对象，如果没有就新创建一个并且存入session域中
        if(cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice()));
        request.getSession().setAttribute("lastItemName",book.getName());
        //Referer这个是客户端浏览器发过来的数据，保存在请求头中，这个数据是指客户端发送请求给服务器时候地址栏中的地址
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
    * @Description: 删除购物车中产品的servlet方法
            * @Param: [request, response]
            * @return: void
            * @Author: longjian
            * @Date:9:16 2022/2/22
            */

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //判断session域中是否存在购物车对象，如果没有就新创建一个并且存入session域中
        if(cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.deleteItem(id);
        request.getRequestDispatcher("/pages/cart/cart.jsp").forward(request, response);
    }
    /**
    * @Description: 清空购物车servlet方法
            * @Param: [request, response]
            * @return: void
            * @Author: longjian
            * @Date:9:30 2022/2/22
            */

    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //判断session域中是否存在购物车对象，如果没有就新创建一个并且存入session域中
        if(cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.clear();
        response.sendRedirect(request.getHeader("Referer"));
    }
    protected void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //判断session域中是否存在购物车对象，如果没有就新创建一个并且存入session域中
        if(cart==null){
            cart=new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //更新
        cart.updateItem(id, count);
        //这里不用自己保存是因为完美使用的cart只是一个指向session域中cart的地址,所以修改的话会一并修改,不用再存储到域之中
//        request.getSession().setAttribute("cart", cart);
        response.sendRedirect(request.getHeader("Referer"));
    }
}
