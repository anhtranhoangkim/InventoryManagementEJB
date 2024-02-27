package com.example.demo12;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "supplierServlet", value = "/supplier-servlet")
public class SupplierServlet extends HttpServlet {

    @EJB
    private SupplierBean supplierBean;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");

        if (userSessionBean != null && userSessionBean.isLoggedIn()) {
            // Redirect GET requests to the input form
            response.sendRedirect("supplier.jsp");
        } else {
            // Redirect to login page
            response.sendRedirect("login.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");

        if (userSessionBean != null && userSessionBean.isLoggedIn()) {
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
            // Redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}
