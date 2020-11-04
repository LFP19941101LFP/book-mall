package com.alibaba.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 统一处理所有请求基类,提供请求和转发公共post和get方法<br/>
 * 继承HttpServlet类,重写post和get方法<br/>
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.controller
 * @Title BaseServlet
 * @date 2020-08-09 17:06
 * @company 阿里巴巴
 */
public abstract class BaseServlet extends HttpServlet {

    /**
     * 处理get请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    /**
     * 处理post请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          /*
        super.doPost(req, resp);  坑:java.lang.IllegalStateException: Cannot forward after response has been committed
                                        . lang。IllegalStateException:在响应被提交后不能转发
        System.out.println("进入方法");
         */
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
        try {
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
