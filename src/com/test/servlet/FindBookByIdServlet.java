package com.test.servlet;

import com.test.model.Book;
import com.test.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FindBookByIdServlet")
public class FindBookByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        BookServiceImpl bookService = new BookServiceImpl();
        Book book = bookService.findBookById(id);
        request.setAttribute("book",book);
        request.getRequestDispatcher("./admin/products/edit.jsp").forward(request,response);
    }
}
