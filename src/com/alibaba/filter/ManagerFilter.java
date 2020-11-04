package com.alibaba.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用户登录权限管理拦截类
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.filter
 * @Title ManagerFilter
 * @date 2020-08-14 16:31
 * @company 阿里巴巴
 */
public class ManagerFilter implements Filter {


    /**
     * 判断用户是否登录,如果在没有登录的情况下进行订单的支付
     * 需要跳转到用户登录页面,登录成功后才能支付
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
