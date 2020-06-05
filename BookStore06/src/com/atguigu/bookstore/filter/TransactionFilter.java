package com.atguigu.bookstore.filter;

import com.atguigu.bookstore.utils.JDBCUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 开启事务的过滤器
 */
@WebFilter("/*")
public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        try {
            //2.开启事务
            connection.setAutoCommit(false);
            //3.放行请求
            chain.doFilter(req, resp);
            //4.没有出现异常，提交事务
            connection.commit();
        } catch (Exception e) {
            try {
                //5.出现异常，回滚事务
                connection.rollback();
                //重定向到异常页面
                response.sendRedirect(request.getContextPath()+"/pages/error/error.jsp");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            //6.释放连接
            JDBCUtils.releaseConnection();
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
