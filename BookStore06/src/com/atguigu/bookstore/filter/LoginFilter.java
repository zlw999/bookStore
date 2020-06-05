package com.atguigu.bookstore.filter;

import com.atguigu.bookstore.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 验证用户是否登录的Filter
 */
@WebFilter("/OrderClientServlet") //配置要拦截的地址
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        //获取Session对象
        HttpSession session = request.getSession();
        //获取session域中的User对象
        User user = (User)session.getAttribute("user");
        //判断User对象是否为null
        if (user != null) {
            //证明已经登录，放行请求
            chain.doFilter(req, resp);
        }else{
            //还没有登录，设置一个提示信息并放到request域中
            request.setAttribute("msg","该操作需要先登录！");
            //转发到登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
