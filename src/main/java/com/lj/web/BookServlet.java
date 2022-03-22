package com.lj.web;

import com.lj.pojo.Book;
import com.lj.pojo.Page;
import com.lj.service.BookService;
import com.lj.service.impl.BookServiceImpl;
import com.lj.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BookServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();

    //后台管理页面的列出书表
    protected void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //2.通过bookservice查询查询全部的书籍
        List<Book> books = bookService.queryBooks();
        //3.把查询出来的书籍保存在request域中
        request.setAttribute("books",books);
        //4.查询出来的结果传输到book_manager.jsp页面上
        request.getRequestDispatcher("//pages/manager/book_manager.jsp").forward(request,response);
    }

    //后台添加书籍信息
    protected void add(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//        借楼补充一点，如果在Baseservlet改了编码方式还是不行可以尝试在jdbc.properties中将原先url替换
//                成url=jdbc:mysql://localhost:3306/book?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true。我就是因为这里导致数据插入乱码的
        Book newBook = WebUtils.copyParamToBean(new Book(), request.getParameterMap());
        bookService.addBook(newBook);
        //由于这种写法会出现刷新重写提交bug，就是在填写完提交之后再刷新页面还会提交请求，这时候再服务器端就会添加两次一样的书籍。所以不能用请求转发，应该用重定向，因为重定向刷新是发两次请求了
//        request.getRequestDispatcher("//manager/bookServlet?action=list").forward(request,response);
//        重定向时候斜杠解析为到端口号，所以还要获取工程路径,是/shucheng
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page");
    }

    //后台更新书籍信息
    protected void update(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //下面是要处理修改书本并且传回book_manager
        Book bookChanged = WebUtils.copyParamToBean(new Book(), request.getParameterMap());
        bookService.updateBook(bookChanged);
        request.getRequestDispatcher("/manager/bookServlet?action=page").forward(request,response);
    }
    protected void getbook(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        //这是book_manager传过来要修改的书本的id
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        request.setAttribute("id",id);
        Book book = bookService.queryBookById(id);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }

    //后台删除书籍信息
    protected void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //默认值设设置为0是因为id是从1开始的，0就是删除任何书籍
        int id=WebUtils.parseInt(request.getParameter("id"),0);
        bookService.deleteBookById(id);
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=page");
    }
    //处理分页的
    protected void page(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo=WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGESIZE);
        //2 调用bookService.page（pageNo，pageSize）获取当前页的page对象
        Page<Book> page=bookService.page(pageNo,pageSize);
        //设置好page的url用于导航栏的分离抽取
        page.setUrl("manager/bookServlet?action=page");
        //3 保存page对象到request域中
        request.setAttribute("page",page);
        //4 请求转发到book_manager.jsp中
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
}
