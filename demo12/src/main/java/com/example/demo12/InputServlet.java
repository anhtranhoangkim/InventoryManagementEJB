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

@WebServlet(name = "inputServlet", value = "/input-servlet")
public class InputServlet extends HttpServlet {

    @EJB
    private InputBean inputBean;

    @EJB
    private UserSessionBean userSessionBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");

        if (userSessionBean != null && userSessionBean.isLoggedIn()) {
            // User is logged in, forward to input form
            List<InputBean.Object> objects = inputBean.getObjects();
            request.setAttribute("objects", objects);
            request.getRequestDispatcher("input.jsp").forward(request, response);
        } else {
            // User is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");

        if (userSessionBean != null && userSessionBean.isLoggedIn()) {
            // User is logged in, proceed with the operation
            String[] idObjects = request.getParameterValues("idObject");
            String[] quantities = request.getParameterValues("quantity");
            String[] inputPrices = request.getParameterValues("inputPrice");

            // Check if InputInfo is not null
            if (idObjects != null && quantities != null && inputPrices != null) {
                // Insert into Inputs table
                if (inputBean != null) {
                    int idInput = inputBean.insertInput();
                    if (idInput != -1) {
                        for (int i = 0; i < idObjects.length; i++) {
                            int idObject = Integer.parseInt(idObjects[i]);
                            int quantity = Integer.parseInt(quantities[i]);
                            float inputPrice = Float.parseFloat(inputPrices[i]);

                            if (!inputBean.insertInputInfo(quantity, inputPrice, idObject, idInput)) {
                                request.setAttribute("errorMessage", "Failed to insert input info");
                                request.getRequestDispatcher("input.jsp").forward(request, response);
                                return;
                            }
                        }

                        // Redirect to the input-servlet after successful insertion
                        response.sendRedirect("home.jsp");
                    }
                }
            } else {
                // InputInfo is null, redirect to input page
                response.sendRedirect("input.jsp");
            }
        } else {
            // User is not logged in, redirect to login page
            response.sendRedirect("login.jsp");
        }
    }
}
