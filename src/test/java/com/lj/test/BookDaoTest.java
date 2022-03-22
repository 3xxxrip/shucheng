package com.lj.test;

import com.lj.dao.impl.BookDaoImpl;
import com.lj.pojo.Book;
import com.lj.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private Book book=new Book(24,"龙建是大爹最终版","龙建",new BigDecimal(100),20,80,null);
    private BookDaoImpl bookDao=new BookDaoImpl();
    @Test
    public void addBook() {
        System.out.println(bookDao.addBook(book));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(23);
    }

    @Test
    public void updateBook() {
        System.out.println(bookDao.updateBook(book));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(book.getId()));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for(Book book1:books){
            System.out.println(book1);
        }
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }
    @Test
    public void queryForpageItems() {
        for(Book book:bookDao.queryForpageItems(1,Page.PAGESIZE)){
            System.out.println(book);
        }

    }
}