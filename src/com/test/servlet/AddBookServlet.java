package com.test.servlet;

import com.test.model.Book;
import com.test.service.BookServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Book book = new Book();
        try {
            BeanUtils.populate(book,request.getParameterMap());
            System.out.println(book);
            BookServiceImpl bookService=new BookServiceImpl();
            bookService.addBook(book);
            List<Book> books = bookService.findAllbooks();
            request.setAttribute("books",books);
            request.getRequestDispatcher("./admin/products/list.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
