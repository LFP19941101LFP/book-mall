package com.alibaba.test;

import com.alibaba.pojo.Book;
import com.alibaba.pojo.Page;
import com.alibaba.service.BookService;
import com.alibaba.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * service层测试类
 * @author LiFupeng
 * @version V1.0
 * @Package com.alibaba.test
 * @Title BookServiceTest
 * @date 2020-08-10 13:46
 * @company 阿里巴巴
 */
public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    // 测试添加
    @Test
    public void addBookTest(){
        bookService.addBook(new Book(null,"奋斗的路上","李富鹏",new BigDecimal(120), 50,8000,null));
    }

    // 测试删除
    @Test
    public void deleteBookTest(){
        bookService.deleteBook(26);
    }

    // 测试修改
    @Test
    public void updateBookTest(){
        bookService.updateBook(new Book(26,"怎么走666","李富鹏",new BigDecimal(120), 50,8000,null));
    }

    // 根据id查询图书信息
    @Test
    public void getBookByIdTest(){
        System.out.println(bookService.getBookById(21));
    }

    //查询全部图书
    @Test
    public void listBooksTest(){
        for (Book o : bookService.listBooks()) {
            System.out.println(o);
        }
    }

    //测试分页
    @Test
    public void pageTest() {
        System.out.println(bookService.page(1,Page.PAGE_SIZE));
    }

    //测试分页
    @Test
    public void pageByPriceTest() {
        System.out.println(bookService.pageByPrice(1,Page.PAGE_SIZE, 30,60));
    }
}
