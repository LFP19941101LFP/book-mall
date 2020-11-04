package com.alibaba.dao;

import com.alibaba.pojo.Book;

import java.util.List;

/**
 * 图书持久化接口
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.pojo.Book
 * @Title BookDao
 * @date 2020-08-10 12:39
 * @company 阿里巴巴
 */
public interface BookDao {

    /**
     * 添加图书
     * @param book
     * @return 返回受影响的行数
     */
    int addBook(Book book);

    /**
     * 根据id删除图书
     * @param id
     * @return 返回图书id
     */
    int deleteBookById(Integer id);

    /**
     * 修改已添加的图书
     * @param book
     * @return 返回受影响的行数
     */
    int updateBook(Book book);

    /**
     * 根据图书的id查询图书
     * @param id
     * @return 返回图书图书对象
     */
    Book getBookById(Integer id);

    /**
     * 查询图书列表
     * @return 返回图书列表
     */
    List<Book> listBook();

    /**
     * 获取总记录数
     * @return
     */
    Integer queryPageTotalCount();

    /**
     * 查询每显示的图书数量
     * @param begin 起始行
     * @param pageSize 也大小
     * @return Book对象
     */
    List<Book> queryPageItems(int begin, int pageSize);

    /**
     * 按照价格区间查询
     * @param min 最低价格
     * @param max 最高价格
     * @return
     */
    Integer queryPageTotalCountByPrice(int min, int max);

    /**
     * 按照价格区间份分页查询显示
     * @param begin 起始页
     * @param pageSize 页大小
     * @param min 最低价格
     * @param max 最高价格
     * @return
     */
    List<Book> queryPageItemsByPrice(int begin, int pageSize, int min, int max);
}
