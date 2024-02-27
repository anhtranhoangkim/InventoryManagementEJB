package com.example.demo12;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ejb.EJB;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private LoginBean loginBean;

    @EJB
    private UserSessionBean userSessionBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Check if user is already logged in
        if (userSessionBean != null && userSessionBean.isLoggedIn()) {
            // User is already logged in, redirect to home page
            response.sendRedirect("home.jsp");
        } else {
            // User is not logged in, redirect to login form
            response.sendRedirect("login.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (loginBean == null) {
            throw new ServletException("LoginBean is not initialized");
        } else if (!loginBean.validate(username, password)) {
            throw new ServletException("Invalid username or password");
        } else {
            userSessionBean.login(request, username); // Set the username in the session bean and mark as logged in
            request.getSession().setAttribute("username", username); // Store the username in the session
            response.sendRedirect("home.jsp");
        }
    }
}
