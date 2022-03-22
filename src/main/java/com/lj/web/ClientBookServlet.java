package com.lj.web;

import com.lj.pojo.Book;
import com.lj.pojo.Page;
import com.lj.service.BookService;
import com.lj.service.impl.BookServiceImpl;
import com.lj.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {
    private BookService bookService=new BookServiceImpl();

    protected void page(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo= WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGESIZE);
        //2 调用bookService.page（pageNo，pageSize）获取当前页的page对象
        Page<Book> page=bookService.page(pageNo,pageSize);
        //设置好page的url用于导航栏的分离抽取
        page.setUrl("client/bookServlet?action=page");
        //3 保存page对象到request域中
        request.setAttribute("page",page);
        //4 请求转发到book_manager.jsp中
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

//    public void search(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
//        int pageNo= WebUtils.parseInt(request.getParameter("pageNo"),1);
//        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGESIZE);
//        //
//        if(request.getParameter("max")==null){
//            request.setAttribute("max",0);
//        }else{
//            request.setAttribute("max",Integer.parseInt(request.getParameter("max")));
//        }
//        int maxAttr=(Integer) request.getAttribute("max");
//
//        int min=WebUtils.parseInt(request.getParameter("min"), 0);
//        int max=WebUtils.parseInt(request.getParameter("max"), maxAttr);
//        request.setAttribute("min",min);
//        request.setAttribute("max",max);
//        Page<Book> page=bookService.pageByPrice(pageNo,pageSize,min,max);
//        //设置好page的url用于导航栏的分离抽取
//        page.setUrl("client/bookServlet?action=search");
//        //3 保存page对象到request域中
//        request.setAttribute("page",page);
//        //4 请求转发到book_manager.jsp中
//        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
//
//    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGESIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小价格的参数,追加到分页条的地址参数中
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        // 如果有最大价格的参数,追加到分页条的地址参数中
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}