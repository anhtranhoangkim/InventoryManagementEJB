package com.example.demo12;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ejb.EJB;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "statisticsServlet", value = "/statistics-servlet")
public class StatisticsServlet extends HttpServlet {

    @EJB
    private StatisticsBean statisticsBean;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<StatisticsBean.Input> inputs = statisticsBean.getInputs();
        request.setAttribute("inputs", inputs);

        List<StatisticsBean.Output> outputs = statisticsBean.getOutputs();
        request.setAttribute("outputs", outputs);

        request.getRequestDispatcher("statistics.jsp").forward(request, response);
    }
}
