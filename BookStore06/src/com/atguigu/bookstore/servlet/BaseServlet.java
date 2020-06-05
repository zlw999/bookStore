package com.atguigu.bookstore.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 专门用来被各个Servlet继承的BaseServlet,但是，子类不能重写BaseServlet中的doGet和doPost方法
 */
public class BaseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获取请求参数methodName
        String methodName = request.getParameter("methodName");
//        System.out.println(methodName);
//        if("login".equals(methodName)){
//            //证明在登录，调用login方法
//            login(request,response);
//        } else if ("regist".equals(methodName)) {
//            //证明在注册，调用regist方法
//            regist(request,response);
//        }

        try {
            //获取方法的对象
            /*
                getDeclaredMethod()方法中传入的参数的说明：
                第一个参数是将来要调用的方法的方法名
                第二个和第三个参数是将来要调用的方法中需要传入的参数的类型
             */
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //设置访问权限
            method.setAccessible(true);
            //执行对应的方法
            /*
                invoke()方法中传入的参数的说明：
                第一个参数是要调用那个对象的方法
                第二个和第三个参数是调用方法时需要传入的参数
             */
            method.invoke(this,request,response);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
