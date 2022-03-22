package com.lj.dao;

import com.lj.pojo.Book;

import java.util.List;

public interface BookDao {



    public int addBook(Book book);
    public int deleteBookById(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    Integer queryForPageTotalCount();
    List<Book> queryForpageItems(int begin, int pageSize);

    public List<Book> queryForpageItemsByPrice(int begin, int pageSize,int min,int max);
    Integer queryForPageTotalCountByPrice(int min,int max);
}
