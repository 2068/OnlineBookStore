package com.test.service;

import com.test.dao.BookDaoImpl;
import com.test.model.Book;
import com.test.model.PageResult;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl {
    BookDaoImpl bookDao=new BookDaoImpl();
    public List<Book> findAllbooks(){
        try {
            return bookDao.findAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addBook(Book book){
        try {
            bookDao.addBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book findBookById(String id){
        try {
            return bookDao.findBookById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBook(Book book){
        try {
            bookDao.updateBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBookById(String id) {
        try {
            bookDao.deleteBookById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteBookByIds(String ids) {
        try {
            bookDao.deleteBookByIds(ids);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Book> findBook(String id, String name, String category, String minprice, String maxprice) {
        try {
            return bookDao.findBook(id, name, category, minprice, maxprice);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public PageResult<Book> findBooksByPage(int page){
        try {
            return bookDao.findBooksByPage(page);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
