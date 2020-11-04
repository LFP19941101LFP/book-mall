package com.alibaba.dao.impl;

import com.alibaba.dao.BaseDao;
import com.alibaba.dao.BookDao;
import com.alibaba.pojo.Book;
import java.util.List;

/**
 * @author LiFupeng
 * @version V1.0
 * @Package
 * @Title
 * @date 2020-08-10 12:46
 * @company 阿里巴巴
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     * 添加图书
     * @param book
     * @return 返回sql参数
     */
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book( `name` , `author` , `price` , `sales` , `stock` , `img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    /**
     * 根据图书id删除图书
     * @param id
     * @return
     */
    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }


    /**
     * 修改图书信息,根据图书id,坑:注意sql语句中 `name=?`报错
     * @param book
     * @return
     */
    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name=? , author=? , price=? , sales=? , stock=? , img_path=? where id=?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(),book.getId());
    }

    /**
     * 根据图书id查询图书
     * @param id
     * @return
     */
    @Override
    public Book getBookById(Integer id) {
        String sql = "select id, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    /**
     * 查询图书列表
     * @return
     */
    @Override
    public List<Book> listBook() {
        String sql = "select id, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
        return queryForList(Book.class, sql) ;
    }

    /**
     * 获取总记录数
     * @return 返回查询的行数
     */
    @Override
    public Integer queryPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    /**
     * 查询每显示的图书数量
     * @param begin 起始行
     * @param pageSize 也大小
     * @return
     */
    @Override
    public List<Book> queryPageItems(int begin, int pageSize) {
        String sql = "select id, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book LIMIT ?, ?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    /**
     * 按照价格区间查询
     * @param min 最低价格
     * @param max 最高价格
     * @return
     */
    @Override
    public Integer queryPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    /**
     * 按照价格区间份分页查询显示
     * @param begin 起始页
     * @param pageSize 页大小
     * @param min 最低价格
     * @param max 最高价格
     * @return
     */
    @Override
    public List<Book> queryPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select id, `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath " +
                "from t_book where price between ? and ? order by price LIMIT ?, ? ";
        return queryForList(Book.class, sql, min, max, begin, pageSize );
    }
}
