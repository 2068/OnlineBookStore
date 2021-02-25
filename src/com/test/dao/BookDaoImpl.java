package com.test.dao;

import com.test.model.Book;
import com.test.model.PageResult;
import com.test.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BookDaoImpl {
    public List<Book> findAllBooks() throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        return qr.query("select * from books",new BeanListHandler<Book>(Book.class));
    }

    public void addBook(Book book) throws SQLException{
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql="insert into books (id,name,price,pnum,category,description) values (?,?,?,?,?,?);";
        Object[] params=new Object[6];
        params[0]= UUID.randomUUID().toString();
        params[1]=book.getName();
        params[2]=book.getPrice();
        params[3]=book.getPnum();
        params[4]=book.getCategory();
        params[5]=book.getDescription();
        qr.update(sql,params);
    }

    public Book findBookById(String id) throws SQLException {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql="select * from books where id=?";
        return qr.query(sql,new BeanHandler<Book>(Book.class),id);
    }

    public void updateBook(Book book) throws SQLException {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql="update books set name=?,price=?,pnum=?,category=?,description=? where id=?";
        qr.update(sql,book.getName(),book.getPrice(),book.getPnum(),book.getCategory(),book.getDescription(),book.getId());

    }

    public void deleteBookById(String id) throws SQLException {
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        String sql="delete from books where id=?";
        qr.update(sql,id);
    }

    public void deleteBookByIds(String ids) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String[] idArr = ids.split(",");
        String sql = "delete from books where id = ?";
        for(String id : idArr){
            qr.update(sql,id);
        }
    }

    public List<Book> findBook(String id, String name, String category, String minprice, String maxprice) throws SQLException {
        String sql="select * from books where 1=1";
        if(!"".equals(id)){
            sql+=" and id='"+id+"'";
        }
        if(!"".equals(name)){
            sql+=" and name like '%"+name+"%'";
        }
        if(!"".equals(category)){
            sql+=" and ategory='"+category+"'";
        }
        if(!"".equals(minprice)){
            sql+=" and price>="+minprice;
        }
        if(!"".equals(maxprice)){
            sql+=" and price<="+maxprice;
        }
        System.out.println(sql);
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        return qr.query(sql,new BeanListHandler<>(Book.class));
    }

    public PageResult<Book> findBooksByPage(int page)throws SQLException{
        QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
        PageResult<Book> pr=new PageResult<Book>();
        Long totalCount=(Long)qr.query("select count(*) from books",new ScalarHandler());
        pr.setTotalCount(totalCount);
        int totalPage=(int)(totalCount%pr.getPageCount()==0?totalCount/pr.getPageCount():totalCount/pr.getPageCount()+1);
        pr.setTotalPage(totalPage);
        pr.setCurrentPage(page);
        String sql="select * from books order by pnum limit ?,?";
        int start=(page-1)*pr.getPageCount();
        List<Book> books=qr.query(sql,new BeanListHandler<Book>(Book.class),start,pr.getPageCount());
        pr.setList(books);
        return pr;
    }
}
