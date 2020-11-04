package com.alibaba.controller;

import com.alibaba.pojo.Cart;
import com.alibaba.pojo.User;
import com.alibaba.service.OrderService;
import com.alibaba.service.impl.OrderServiceImpl;
import com.alibaba.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 订单页面控制器
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.controller
 * @Title OrderServlet
 * @date 2020-08-14 10:34
 * @company 阿里巴巴
 */
public class OrderServlet extends BaseServlet{

    OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        // 结账前登陆
        if (user == null ) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        //获取用户的id并创建订单
        Integer userId = user.getId();
        String orderId = orderService.createOrder(cart, userId);

        /*防止表单重复提交,应该使用重定向
        req.setAttribute("orderId", orderId);
        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);*/
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
