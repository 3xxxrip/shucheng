package com.lj.filter;

import com.lj.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {



        try {
            //通过这样一个过滤器实现对所有过滤的网站资源实现事务提交或者回滚
            chain.doFilter(request, response);
            //提交事务
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            JdbcUtils.rollbackAndClose();
            //异常抛出给tomcat服务器以便出错时跳转到错误页面
            throw new RuntimeException(e);
        }
    }
}
