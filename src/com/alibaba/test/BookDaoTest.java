package com.alibaba.test;

import com.alibaba.dao.BookDao;
import com.alibaba.dao.impl.BookDaoImpl;
import com.alibaba.pojo.Book;
import com.alibaba.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * bookdao测试类
 * @author LiFupeng
 * @version V1.0
 * @Package
 * @Title
 * @date 2020-08-10 13:04
 * @company 阿里巴巴
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void testAddBook(){
        bookDao.addBook(new Book(null,"奋斗不止","李富鹏",new BigDecimal(120), 50,8000,null));
    }

    @Test
    public void testDeleteBookById(){
        bookDao.deleteBookById(22);
    }

    @Test
    public void testUpdateBook(){
        bookDao.updateBook(new Book(21,"分布式id生成方式","雷丰阳",new BigDecimal(120), 50,8000,null));
    }

    @Test
    public void testGetBookById(){
        System.out.println(bookDao.getBookById(21));
    }

    @Test
    public void testGetBook(){
        for (Book book : bookDao.listBook()) {
            System.out.println(book);
        }
    }

    //查询总记录数
    @Test
    public void queryPageTotalCount() {
        System.out.println(bookDao.queryPageTotalCount());
    }

    //查询页大小
    @Test
    public void queryPageItems() {
        for (Book book : bookDao.queryPageItems(8, Page.PAGE_SIZE)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryPageTotalCountByPrice() {
        System.out.println(bookDao.queryPageTotalCountByPrice(30,60));
    }


    @Test
    public void queryPageItemsByPrice() {
        for (Book book : bookDao.queryPageItemsByPrice(0, Page.PAGE_SIZE,30,100)) {
            System.out.println(book);
        }
    }

}
