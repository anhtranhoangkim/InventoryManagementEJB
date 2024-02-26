package com.example.demo10;

import jakarta.ejb.Stateless;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Stateless
public class InputBean {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKho_SOA;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public boolean insertInput(String id) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO Inputs (Id, DateInput) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);

            // Get current date and time
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = formatter.format(date);
            statement.setString(2, strDate);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}