package com.example.demo12;

import jakarta.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class InventoryBean {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKho_SOA;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public List<Object> getObjects(String searchName) {
        List<Object> objects = new ArrayList<>();
        String sql = "SELECT * FROM Objects WHERE Inventory > 0";
        if (searchName != null && !searchName.isEmpty()) {
            sql += " AND DisplayName LIKE ?";
        }

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            if (searchName != null && !searchName.isEmpty()) {
                statement.setString(1, "%" + searchName + "%");
            }
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Object object = new Object(
                        rs.getInt("Id"),
                        rs.getString("DisplayName"),
                        rs.getInt("IdSupplier"),
                        rs.getInt("Inventory")
                );
                objects.add(object);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return objects;
    }


    public class Object {
        private int id;
        private String displayName;
        private int idSupplier;
        private int inventory;

        public Object(int id, String displayName, int idSupplier, int inventory) {
            this.id = id;
            this.displayName = displayName;
            this.idSupplier = idSupplier;
            this.inventory = inventory;
        }

        // Getters and setters for all fields

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public int getIdSupplier() {
            return idSupplier;
        }

        public void setIdSupplier(int idSupplier) {
            this.idSupplier = idSupplier;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }
    }
}
