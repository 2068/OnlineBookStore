package com.test.servlet;

import com.test.service.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BatchDeleteBookServlet")
public class BatchDeleteBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids=request.getParameter("ids");
        BookServiceImpl bookService = new BookServiceImpl();
        bookService.deleteBookByIds(ids);
        request.setAttribute("books",bookService.findAllbooks());
        request.getRequestDispatcher("./admin/products/list.jsp").forward(request,response);
    }
}
