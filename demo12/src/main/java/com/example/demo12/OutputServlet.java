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

@WebServlet(name = "outputServlet", value = "/output-servlet")
public class OutputServlet extends HttpServlet {

    @EJB
    private OutputBean outputBean;

    @EJB
    private UserSessionBean userSessionBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");

        if (userSessionBean != null && userSessionBean.isLoggedIn()) {
            List<OutputBean.Object> objects = outputBean.getObjects();
            request.setAttribute("objects", objects);
            request.getRequestDispatcher("output.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserSessionBean userSessionBean = (UserSessionBean) session.getAttribute("userSessionBean");

        if (userSessionBean != null && userSessionBean.isLoggedIn()) {
            String[] customers = request.getParameterValues("customer");
            String[] idObjects = request.getParameterValues("idObject");
            String[] quantities = request.getParameterValues("quantity");
            String[] outputPrices = request.getParameterValues("outputPrice");

            if (customers != null && idObjects != null && quantities != null && outputPrices != null) {
                int idOutput = outputBean.insertOutput();
                if (idOutput != -1) {
                    for (int i = 0; i < idObjects.length; i++) {
                        String customer = customers[i];
                        int idObject = Integer.parseInt(idObjects[i]);
                        int quantity = Integer.parseInt(quantities[i]);
                        float outputPrice = Float.parseFloat(outputPrices[i]);

                        OutputBean.Object object = outputBean.getObjectById(idObject);
                        if (object != null) {
                            if (quantity > 0 && quantity <= object.getInventory()) {
                                if (!outputBean.insertOutputInfo(customer, quantity, outputPrice, idObject, idOutput)) {
                                    request.setAttribute("errorMessage", "Failed to insert output info");
                                    request.getRequestDispatcher("output.jsp").forward(request, response);
                                    return;
                                }
                            } else {
                                request.setAttribute("errorMessage", "Invalid quantity for object: " + object.getDisplayName());
                                request.getRequestDispatcher("output.jsp").forward(request, response);
                                return;
                            }
                        }
                    }
                    response.sendRedirect("home.jsp");
                }
            } else {
                response.sendRedirect("output.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}