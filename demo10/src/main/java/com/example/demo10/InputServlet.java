package com.example.demo10;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ejb.EJB;
import java.io.IOException;



@WebServlet(name = "inputServlet", value = "/input-servlet")
public class InputServlet extends HttpServlet {

    @EJB
    private InputBean inputBean;

    @EJB
    private UserSessionBean userSessionBean;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Redirect GET requests to the input form
        response.sendRedirect("input.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userSessionBean.isLoggedIn()) {
            // User is logged in, proceed with the operation
            String id = request.getParameter("id");

            if (inputBean != null && inputBean.insertInput(id)) {
                // Redirect to the object-servlet after successful insertion
                response.sendRedirect("object-servlet");
            } else {
                request.setAttribute("errorMessage", "Failed to insert input");
                request.getRequestDispatcher("input.jsp").forward(request, response);
            }
        } else {
            // User is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}
