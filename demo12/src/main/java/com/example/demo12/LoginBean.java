package com.example.demo12;

import jakarta.ejb.Stateless;
import java.sql.*;

@Stateless
public class LoginBean {
    private static final String url = "jdbc:sqlserver://localhost;databaseName=QuanLyKho_SOA;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public boolean validate(String username, String password) {
        StringBuilder messageBuilder = new StringBuilder();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection conn = DriverManager.getConnection(url)) {
                String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet rs = statement.executeQuery();
                return rs.next();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            messageBuilder.append("Error occurred: ").append(e.getMessage());
        }
        return false;
    }
}