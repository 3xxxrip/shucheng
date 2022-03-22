package com.lj.service.impl;

import com.lj.dao.BookDao;
import com.lj.dao.impl.BookDaoImpl;
import com.lj.pojo.Book;
import com.lj.pojo.Page;
import com.lj.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page= new Page<>();
        //设置每页显示数量
        page.setPageSize(pageSize);
        //设置总记录数
        Integer pageTotalCount=bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        //求总页码,如果总记录数除以每页显示数量大于0说明不能刚好除完，所以会多一页
        Integer pageTotal=pageTotalCount/pageSize;
        if(pageTotal%pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        //设置当前页码,并且完成当前页码判断，如果当前页码超出范围做出相应对策

        page.setPageNo(pageNo);

        //设置当前页数据
        //设置当前也数据分页开始的索引
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=bookDao.queryForpageItems(begin,pageSize);
        page.setPageItems(items);


        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page= new Page<>();
        //设置每页显示数量
        page.setPageSize(pageSize);
        //设置总记录数
        Integer queryForPageTotalCountByPrice=bookDao.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(queryForPageTotalCountByPrice);

        //求总页码,如果总记录数除以每页显示数量大于0说明不能刚好除完，所以会多一页
        Integer pageTotal=queryForPageTotalCountByPrice/pageSize;
        if(pageTotal%pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        //设置当前页码,并且完成当前页码判断，如果当前页码超出范围做出相应对策
        page.setPageNo(pageNo);
        //设置当前也数据分页开始的索引
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> items=bookDao.queryForpageItemsByPrice(begin,pageSize,min,max);
        page.setPageItems(items);

        return page;
    }

}
