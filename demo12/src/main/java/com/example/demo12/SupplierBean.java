package com.example.demo12;

import jakarta.ejb.Stateless;
import java.sql.*;

@Stateless
public class SupplierBean {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKho_SOA;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public boolean insertSupplier(String displayName, String address, String phone, String email, String moreInfo, String contractDate) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO Suppliers (DisplayName, Address, Phone, Email, MoreInfo, ContractDate) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, displayName);
            statement.setString(2, address);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setString(5, moreInfo);
            statement.setString(6, contractDate);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
