package com.lj.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//这是一个基础类，就是一个工具类。因为在用户模块和图书管理模块在处理流程上很多相似的地方，为了降低代码的耦合，创建这个基础操作类，其他模块的servlet继承这个基础模块类
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取方法名称
        //因为其他servlet继承自这个类，所以在dopost的时候this就是调用这个方法的类
        String action=request.getParameter("action");
        try {
//            getDeclaredMethod方法是获取当前类中所有声明的方法
            Method method=this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            //手动抛出一个异常给filter后置代码捕获以便回滚事务
            throw new RuntimeException(e);
        }
    }
}
