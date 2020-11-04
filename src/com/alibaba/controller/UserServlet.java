package com.alibaba.controller;

import com.alibaba.pojo.User;
import com.alibaba.service.UserService;
import com.alibaba.service.impl.UserServiceImpl;
import com.alibaba.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 用户注册和登录控制器
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.controller
 * @Title UserServlet
 * @date 2020-08-09 15:24
 * @company 阿里巴巴
 */
public class UserServlet  extends BaseServlet {

    private UserService us = new UserServiceImpl();

    /**
     * 用户登录方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = us.login(new User(null, username, password, null));
        // login为空则登录失败
        if (login == null) {
            // 将错误信息和回显表单项信息,保存到request域中
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 保存用户登录信息到session域中
            req.getSession().setAttribute("user", login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * 请求注册方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 为防止每次切换的验证码不同,需要将本次生成的使用后立即删除
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        // 1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParameter(req.getParameterMap(), new User());

        //2.判断验证码是否可用,可用注册成功跳转成页面,反之跳转在当前页面
        if (token != null && token.equalsIgnoreCase(code)) {
            // 检查用户名是否可用
            if (us.existUser(username)) {
                System.out.println("用户名已存在");
                // 将回显信息保存到request域中
                req.setAttribute("msg", "验证码错误");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                us.registerUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码" + code + "错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 用户注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //清空session中的用户信息
        req.getSession().invalidate();
        //重定向到首页,req.getContextPath()工程路径就是首页
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * ajax验证用户名是否可用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxIsExitUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数
        String username = req.getParameter("username");
        // 调用service检查用户名是否可用的方法
        boolean existUser = us.existUser(username);
        // 将封装的结果返回
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existUser", existUser);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);

    }

}