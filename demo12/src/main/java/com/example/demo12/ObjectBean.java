package com.example.demo12;

import jakarta.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class ObjectBean {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKho_SOA;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public boolean insertObject(String displayName, int idSupplier) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO Objects (DisplayName, IdSupplier) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, displayName);
            statement.setInt(2, idSupplier);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Supplier> getSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM Suppliers");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getInt("Id"),
                        rs.getString("DisplayName"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("MoreInfo"),
                        rs.getDate("ContractDate")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return suppliers;
    }

    public class Supplier {
        private int id;
        private String displayName;
        private String address;
        private String phone;
        private String email;
        private String moreInfo;
        private Date contractDate;

        public Supplier(int id, String displayName, String address, String phone, String email, String moreInfo, Date contractDate) {
            this.id = id;
            this.displayName = displayName;
            this.address = address;
            this.phone = phone;
            this.email = email;
            this.moreInfo = moreInfo;
            this.contractDate = contractDate;
        }

        public int getId() {
            return id;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
