package com.example.demo10;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private static final String url = "jdbc:sqlserver://localhost;databaseName=QuanLyKho_SOA;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        StringBuilder messageBuilder = new StringBuilder();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection conn = DriverManager.getConnection(url)) {
                String sql = "SELECT * FROM Users";
                try (PreparedStatement pstmt = conn.prepareStatement(sql);
                     ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        messageBuilder.append("ID: ")
                                .append(rs.getInt("id"))
                                .append(", Username: ")
                                .append(rs.getString("username"))
                                .append(", Password: ")
                                .append(rs.getString("password"))
                                .append("<br/>");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                messageBuilder.append("Error occurred: ").append(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            messageBuilder.append("Error occurred: ").append(e.getMessage());
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + messageBuilder.toString() + "</h1>");
        out.println("</body></html>");
    }
}

