package com.alibaba.service.impl;

import com.alibaba.dao.BookDao;
import com.alibaba.dao.impl.BookDaoImpl;
import com.alibaba.pojo.Book;
import com.alibaba.pojo.Page;
import com.alibaba.service.BookService;

import java.util.List;

/**
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.service.impl
 * @Title BookServiceImpl
 * @date 2020-08-10 13:42
 * @company 阿里巴巴
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    /**
     * 添加图书
     * @param book
     */
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    /**
     * 根据id删除图书
     * @param id
     */
    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBookById(id);
    }

    /**
     * 修改图书信息
     * @param book
     */
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    /**
     * 根据id查询图书信息
     * @param id
     * @return
     */
    @Override
    public Book getBookById(Integer id) {
        return bookDao.getBookById(id);
    }

    /**
     * 查询全部图书
     * @return
     */
    @Override
    public List<Book> listBooks() {
        return bookDao.listBook();
    }

    /**
     * 分页显示图书信息
     * @param pageNo 当前页码
     * @param pageSize 当前页显示的数量
     * @return
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();
        // 每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryPageTotalCount();
        // 设置总页数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        // 请当前页数据开始的索引
        int begin = (page.getPageNo() -1) * pageSize;
        List<Book> items = bookDao.queryPageItems(begin, pageSize);
        // 设置当前页数据
        page.setItems(items);
        return page;
    }

    /**
     * 处理价格区间的分页查询
     * @param pageNo 当前页
     * @param pageSize 页大小
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();
        // 每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryPageTotalCountByPrice(min, max);
        // 设置总页数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);
        // 请当前页数据开始的索引
        int begin = (page.getPageNo() -1) * pageSize;
        List<Book> items = bookDao.queryPageItemsByPrice(begin, pageSize, min, max);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }
}
