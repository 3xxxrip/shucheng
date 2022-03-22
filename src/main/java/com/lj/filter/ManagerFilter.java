package com.lj.filter;

import com.lj.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManagerFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }
    public void destroy() {
    }
/** 
* @Description: 这个过滤器是过滤manager下面的文件，要求用户登录才能访问
        * @Param: [request, response, chain]
        * @return: void
        * @Author: longjian
        * @Date:16:37 2022/2/23
        */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        User user =(User) httpServletRequest.getSession().getAttribute("user");
        if(user==null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }
        else{
            chain.doFilter(request, response);
        }
    }
}
