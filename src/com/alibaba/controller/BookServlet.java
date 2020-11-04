package com.alibaba.controller;

import com.alibaba.pojo.Book;
import com.alibaba.pojo.Page;
import com.alibaba.service.BookService;
import com.alibaba.service.impl.BookServiceImpl;
import com.alibaba.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 图书管理控制器
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.controller
 * @Title BookServlet
 * @date 2020-08-10 16:58
 * @company 阿里巴巴
 */
public class BookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /**
     * 添加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;*/
        // 获取请求参数
        Book book  = WebUtils.copyParameter(req.getParameterMap(), new Book());
        // 调用BookService.addBook()保存图书信息到数据库
        bookService.addBook(book);
        // 保存成功后页面重定向到图书列表页,显示添加成功的图书信息,转发会出现表单重复提交
        // req.getRequestDispatcher("/manager/bookServlet?action=queryListBook").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        System.out.println(book);
    }

    /**
     * 执行要修改图书信息方法,修改完成后数据保存到数据库
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateBookInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数,封账称为对象
        Book book = WebUtils.copyParameter(req.getParameterMap(), new Book());
        //调用updateBook()方法,修改图书
        bookService.updateBook(book);
        //重定向到图书管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 获取要修改的图书的信息,将信息放到request域中,带到页面数据回显到表单项中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用getBookById查询图书
        Book book = bookService.getBookById(id);
        // 将图书保存到request域中
        req.setAttribute("book", book);
        // 请求转发到book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }


    /**
     * 查询全部图书列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryListBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过BookService查询全部图书
        List<Book> books = bookService.listBooks();
        // 将全部图书信息保存在request域中
        req.setAttribute("books", books);
        //    重定向到/pages/manager/book_manager.jsp，
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     * 根据id删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteBookInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用delete方法删除图示
        bookService.deleteBook(id);
        // 重定向到图书列表页面,
        // resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));

    }

    /**
     * 处理分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用page方法,封装page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        // 保存对象到request域中
        req.setAttribute("page", page);
        // 请求转发到page/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }


}
