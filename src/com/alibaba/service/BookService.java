package com.alibaba.service;

import com.alibaba.pojo.Book;
import com.alibaba.pojo.Page;

import java.util.List;

/**
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.service
 * @Title BookService
 * @date 2020-08-10 13:39
 * @company 阿里巴巴
 */
public interface BookService {

    /**
     * 添加图书
     * @param book
     */
    void addBook(Book book);

    /**
     * 根据id删除图书信息
     * @param id
     */
    void deleteBook(Integer id);

    /**
     * 修改图书信息,调用dao层的updateBook方法<br/>
     * 在BookDaoImpl中updateBook是根据id修改图是信息<br/>
     * @param book
     */
    void updateBook(Book book);

    /**
     * 根据id查询图书信息
     * @param id
     * @return
     */
    Book getBookById(Integer id);

    /**
     * 查询全部图书
     * @return
     */
    List<Book> listBooks();

    /**
     * 分页查询图书信息
     * @param pageNo 当前页码
     * @param pageSize 当前页显示的数量
     * @return Book对象
     */
    Page<Book> page(int pageNo, int pageSize);

    /**
     * 处理价格区间的分页查询
     * @param pageNo 当前页
     * @param pageSize 页大小
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
