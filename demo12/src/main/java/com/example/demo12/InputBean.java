package com.example.demo12;

import jakarta.ejb.Stateless;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Stateless
public class InputBean {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKho_SOA;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public int insertInput() {
        int id = -1;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO Inputs (DateInput) OUTPUT INSERTED.ID VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Get current date and time
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = formatter.format(date);
            statement.setString(1, strDate);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public boolean insertInputInfo(int quantity, float inputPrice, int idObject, int idInput) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO InputInfo (Quantity, InputPrice, IdObject, IDInput) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setFloat(2, inputPrice);
            statement.setInt(3, idObject);
            statement.setInt(4, idInput);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Object> getObjects() {
        List<Object> objects = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM Objects");
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Object object = new Object(
                        rs.getInt("Id"),
                        rs.getString("DisplayName"),
                        rs.getInt("IdSupplier")
                );
                objects.add(object);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception appropriately, log or throw as needed.
        }
        return objects;
    }

    // Embedded Object class
    public class Object {
        private int id;
        private String displayName;
        private int idSupplier;

        // Constructors, getters, and setters

        public Object(int id, String displayName, int idSupplier) {
            this.id = id;
            this.displayName = displayName;
            this.idSupplier = idSupplier;
        }

        public int getId() {
            return id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public int getIdSupplier() {
            return idSupplier;
        }

        // Getters and setters for other fields

        // You can generate these using your IDE or write them manually.
        // It's a good practice to override the toString() method as well.
    }
}
