package com.example.demo12;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "objectServlet", value = "/object-servlet")
public class ObjectServlet extends HttpServlet {

    @EJB
    private ObjectBean objectBean;

    @EJB
    private UserSessionBean userSessionBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");

        if (userSessionBean != null && userSessionBean.isLoggedIn()) {
            List<ObjectBean.Supplier> suppliers = objectBean.getSuppliers();
            request.setAttribute("suppliers", suppliers);
            request.getRequestDispatcher("object.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");

        if (userSessionBean != null && userSessionBean.isLoggedIn()) {
            String displayName = request.getParameter("displayName");
            int idSupplier = Integer.parseInt(request.getParameter("idSupplier"));

            if (objectBean != null && objectBean.insertObject(displayName, idSupplier)) {
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("errorMessage", "Failed to insert object");
                request.getRequestDispatcher("object.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}