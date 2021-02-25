package com.test.servlet;

import com.test.model.Book;
import com.test.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteBookByIdServlet")
public class DeleteBookByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        BookServiceImpl bookService = new BookServiceImpl();
        bookService.deleteBookById(id);
        request.setAttribute("books",bookService.findAllbooks());
        request.getRequestDispatcher("./admin/products/list.jsp").forward(request,response);
    }
}
