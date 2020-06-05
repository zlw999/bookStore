package com.atguigu.bookstore.servlet;

import com.atguigu.bookstore.beans.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 处理用户登录和注册的Servlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    //通过发送Ajax请求验证用户名是否存在的方法
    protected void checkUsernameByAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户输入的用户名
        String username = request.getParameter("username");
        //调用UserService中验证用户名是存在的方法
        boolean regist = userService.regist(username);
        response.setContentType("text/html;charset=utf-8");
        if(regist){
            //用户名已存在
            response.getWriter().write("用户名已存在!");
        }else{
            //用户名可用
            response.getWriter().write("<font style='color:green'>用户名可用!</font>");
        }
    }

    //处理用户注销的方法
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Session对象
        HttpSession session = request.getSession();
        //使Session对象失效
        session.invalidate();
        //重定向到首页
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    //处理用户登录的方法
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用UserService中处理用户登录的方法
        User login = userService.login(username, password);
        if(login != null){
            //用户名和密码正确，将用户信息保存到session域中
            HttpSession session = request.getSession();
            session.setAttribute("user",login);
            // 重定向到登录成功页面
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }else{
            //用户名或密码不正确，设置一个提示信息并放到request域中
            request.setAttribute("msg","用户名或密码不正确！");
//            HttpSession session = request.getSession();
//            session.setAttribute("msg","用户名或密码不正确！");
            //转发到登录页面
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/user/login.jsp");
            //进行请求的转发
            requestDispatcher.forward(request,response);
        }
    }

    //处理用户注册的方法
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名、密码、邮箱
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //获取用户输入的验证码
        String inputCode = request.getParameter("code");
        //获取Session对象
        HttpSession session = request.getSession();
        //获取session域中的验证码
        String sessionCode = (String) session.getAttribute("codeKey");
        //判断两者是否相等
        if(inputCode.equals(sessionCode)){
            //验证码正确，验证用户名是否存在
            //调用UserService中处理用户注册的方法
            boolean regist = userService.regist(username);
            if(regist){
                //用户名已存在，设置一个提示信息并放到request域中
                request.setAttribute("msg","用户名已存在！");
                //转发到注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else{
                //用户名可用，将用户信息保存到数据库中
                //调用UserService中保存用户信息的方法
                userService.saveUser(username,password,email);
                //重定向到注册成功页面
                response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
            }
        }else{
            //验证码错误，设置一个提示信息并放到request域中
            request.setAttribute("msg","验证码不正确！");
            //转发到注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }

    }
}
