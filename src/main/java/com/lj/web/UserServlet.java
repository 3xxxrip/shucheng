package com.lj.web;

import com.lj.pojo.User;
import com.lj.service.UserService;
import com.lj.service.impl.UserServiceImpl;
import com.lj.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private final UserService userService=new UserServiceImpl();
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action=request.getParameter("action");
////        if(action.equals("login")){
////            login(request,response);
////        }
////        else if(action.equals("regist")){
////            regist(request,response);
////        }
//        try {
//            Method method=this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
//            method.invoke(this,request,response);
//        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

    //由于把处理流程的代码写在dopost方法里面不好维护，所以我们把处理的代码写出来，在dopost里面调用就好

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //这行代码的作用就是获取网页传来的参数封装成map,调用填充工具包，直接填充
        User user = WebUtils.copyParamToBean(new User(), request.getParameterMap());
        //获取从数据库查询到的用户信息
        User loginUser = userService.login(user);
        //判断用户存在直接登录成功
        if(loginUser!=null){
            //登录成功之后保存用户名用户名在session域中用于欢迎使用
            request.getSession().setAttribute("username",loginUser.getUsername());
            //保存userId后面创建订单的时候用
            request.getSession().setAttribute("user",loginUser);
            System.out.println(request.getSession().getAttribute("user"));
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
        else{
            //把错误信息存储在request域中
            if(request.getParameter("username").equals("")){
                request.setAttribute("msg",null);
            }
            else{
                request.setAttribute("msg","用户名或密码错误!");

            }

            //有些数据可以保留在填写框中比如用户名，免去密码错误时候重写的麻烦
            request.setAttribute("username",username);

            //跳回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            }
    }
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String repwd=request.getParameter("repwd");
        String email=request.getParameter("email");
        String code=request.getParameter("code");
        //获取session中的验证码
        String token=(String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //先检查验证码是否正确，先把验证码写死，要求验证码为123
        if(token!=null&&token.equalsIgnoreCase(code)){//不关注大小写比较
            //判断用户名是否已经存在,存在即提示用户名已存在
            if(!userService.existsUsername(username)){
                //不存在则注册成功
                User user=new User(null, username, password, email);
                userService.registUser(user);
                //注册成功之后保存id到session域中后面创建订单需要用
                request.getSession().setAttribute("user",userService.login(user));
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
            else{
                //存在提示用户名已存在,并且把回执用户名清空
                request.setAttribute("msg","用户名已被使用!");
                request.setAttribute("username",null);
                request.setAttribute("password",password);
                request.setAttribute("repwd",repwd);
                request.setAttribute("email",email);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }
        }
        else{
            //验证码错误
            request.setAttribute("msg","验证码错误!");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            //保存用户已经填写的邮箱和用户名,密码
            request.setAttribute("username",username);
            request.setAttribute("password",password);
            request.setAttribute("repwd",repwd);
            request.setAttribute("email",email);
        }
    }
    /** 
    * @Description: 用户处理用户注销的方法
            * @Param: [request, response]
            * @return: void
            * @Author: longjian
            * @Date:14:19 2022/1/27
            */
    
    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //注销session
        request.getSession().invalidate();
        //重定向到工程路径，就是最开始的页面
        response.sendRedirect(request.getContextPath());
    }
}
