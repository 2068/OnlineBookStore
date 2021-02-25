package com.test.servlet;

import com.test.model.Book;
import com.test.model.PageResult;
import com.test.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page=req.getParameter("page");
        if(page==null||"".equals(page)){
            page="1";
        }
        BookServiceImpl bookService=new BookServiceImpl();
        PageResult<Book> pageResult=bookService.findBooksByPage(Integer.parseInt(page));
        req.setAttribute("pageResult",pageResult);
        req.getRequestDispatcher("./admin/products/list.jsp").forward(req,resp);
    }
}
