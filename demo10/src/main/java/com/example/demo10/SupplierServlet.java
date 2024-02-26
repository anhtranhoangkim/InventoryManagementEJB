package com.example.demo10;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ejb.EJB;
import java.io.IOException;

@WebServlet(name = "supplierServlet", value = "/supplier-servlet")
public class SupplierServlet extends HttpServlet {

    @EJB
    private SupplierBean supplierBean;

    @EJB
    private UserSessionBean userSessionBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Redirect GET requests to the input form
        response.sendRedirect("supplier.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userSessionBean.isLoggedIn()) {
            // User is logged in, proceed with the operation
            String displayName = request.getParameter("displayName");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String moreInfo = request.getParameter("moreInfo");
            String contractDate = request.getParameter("contractDate");

            if (supplierBean != null && supplierBean.insertSupplier(displayName, address, phone, email, moreInfo, contractDate)) {
                // Redirect to home.jsp after successful insertion
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("errorMessage", "Failed to insert supplier");
                request.getRequestDispatcher("supplier.jsp").forward(request, response);
            }
        } else {
            // User is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}
