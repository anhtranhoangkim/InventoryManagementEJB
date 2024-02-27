package com.example.demo12;

import jakarta.ejb.Stateless;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Stateless
public class OutputBean {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKho_SOA;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public int insertOutput() {
        int id = -1;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO Outputs (DateOutput) OUTPUT INSERTED.ID VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql);

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

    public boolean insertOutputInfo(String customer, int quantity, float outputPrice, int idObject, int IdOutput) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO OutputInfo (Customer, Quantity, OutputPrice, IdObject, IdOutput) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, customer);
            statement.setInt(2, quantity);
            statement.setFloat(3, outputPrice);
            statement.setInt(4, idObject);
            statement.setInt(5, IdOutput);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Object getObjectById(int id) {
        Object object = null;
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM Objects WHERE Id = ?")) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                object = new Object(
                        rs.getInt("Id"),
                        rs.getString("DisplayName"),
                        rs.getInt("IdSupplier"),
                        rs.getInt("Inventory")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return object;
    }

    public List<Object> getObjects() {
        List<Object> objects = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM Objects WHERE Inventory > 0");
             ResultSet rs = statement.executeQuery()) {

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

        public int getId() {
            return id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public int getInventory() {
            return inventory;
        }
    }
}