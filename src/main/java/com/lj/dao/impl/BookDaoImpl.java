package com.lj.dao.impl;

import com.lj.dao.BookDao;
import com.lj.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql="INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) \n" +
                "VALUES(?,?,?,?,?,?);";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql="DELETE FROM t_book WHERE id=?;";

        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="UPDATE t_book SET `name`=?, `author` =?, `price`=? , `sales`=? , `stock` =?, `img_path`=? where `id`=?;";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql="select * from t_book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select * from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql="select count(*) from t_book";
        Number PageTotalCount= (Number) queryForSingleValue(sql);
        return PageTotalCount.intValue();
    }

    @Override
    public List<Book> queryForpageItems(int begin, int pageSize) {
        String sql="select * from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public List<Book> queryForpageItemsByPrice(int begin, int pageSize,int min,int max) {
        String sql="select * from t_book  WHERE price BETWEEN ? AND ? limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min,int max) {
        String sql="select count(*) from t_book WHERE price BETWEEN ? AND ?";
        Number queryForPageTotalCountByPrice= (Number) queryForSingleValue(sql,min,max);
        return queryForPageTotalCountByPrice.intValue();
    }
}
