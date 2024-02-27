package com.example.demo12;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ejb.EJB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "inventoryServlet", value = "/inventory-servlet")
public class InventoryServlet extends HttpServlet {

    @EJB
    private InventoryBean inventoryBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchName = request.getParameter("searchName");
        List<InventoryBean.Object> objects = inventoryBean.getObjects(searchName);
        request.setAttribute("objects", objects);
        request.getRequestDispatcher("inventory.jsp").forward(request, response);
    }
}
