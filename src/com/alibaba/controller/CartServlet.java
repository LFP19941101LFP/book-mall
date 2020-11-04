package com.alibaba.controller;

import com.alibaba.pojo.Book;
import com.alibaba.pojo.Cart;
import com.alibaba.pojo.CartItems;
import com.alibaba.service.BookService;
import com.alibaba.service.impl.BookServiceImpl;
import com.alibaba.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品控制器类
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.controller
 * @Title CartServlet
 * @date 2020-08-13 20:28
 * @company 阿里巴巴
 */
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.getBookById(id);
        CartItems cartItems = new CartItems(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart c = (Cart) req.getSession().getAttribute("cart");
        if (c == null) {
            c = new Cart();
            //坑:设置session属性应该和获取的session属性一致,都为cart,再将cart设置到Cart c对象中
            req.getSession().setAttribute("cart", c);
        }
        c.addItem(cartItems);
        System.out.println(c);
        req.getSession().setAttribute("lastName", cartItems.getName());
        resp.sendRedirect(req.getHeader("Referer"));

    }

    /**
     * 删除购物车的中的商品,根据id删除
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart c = (Cart) req.getSession().getAttribute("cart");
        if (c != null) {
            //删除购物车中的商品
            c.deleteItem(id);
            //重定向到购物车页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clearItem();
            //重定向到购物车页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 修改购物车中的商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =  WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateItemCount(id, count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * ajax请求添加商品到购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.getBookById(id);
        CartItems cartItems = new CartItems(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart c = (Cart) req.getSession().getAttribute("cart");
        if (c == null) {
            c = new Cart();
            //坑:设置session属性应该和获取的session属性一致,都为cart,再将cart设置到Cart c对象中
            req.getSession().setAttribute("cart", c);
        }
        c.addItem(cartItems);
        System.out.println(c);
        // 最后一个商品添加的名称
        req.getSession().setAttribute("lastName", cartItems.getName());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", c.getTotalCount());
        resultMap.put("lastName", cartItems.getName());

        Gson gson = new Gson();
        String mapJsonString = gson.toJson(resultMap);

        resp.getWriter().write(mapJsonString);
    }

}