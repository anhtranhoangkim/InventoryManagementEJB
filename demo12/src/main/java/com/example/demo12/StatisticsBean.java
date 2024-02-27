package com.example.demo12;

import jakarta.ejb.Stateless;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class StatisticsBean {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKho_SOA;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public List<Input> getInputs() {
        List<Input> inputs = new ArrayList<>();
        String sql = "SELECT * FROM Inputs";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Input input = new Input(
                        rs.getInt("Id"),
                        rs.getTimestamp("DateInput"),
                        rs.getFloat("TotalPrice"),
                        getInputInfo(rs.getInt("Id"))
                );
                inputs.add(input);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return inputs;
    }

    private List<InputInfo> getInputInfo(int inputId) {
        List<InputInfo> inputInfos = new ArrayList<>();
        String sql = "SELECT InputInfo.*, Objects.DisplayName FROM InputInfo INNER JOIN Objects ON InputInfo.IdObject = Objects.Id WHERE IdInput = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, inputId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                InputInfo inputInfo = new InputInfo(
                        rs.getInt("Id"),
                        rs.getInt("Quantity"),
                        rs.getFloat("InputPrice"),
                        rs.getFloat("TotalPrice"),
                        rs.getString("DisplayName")
                );
                inputInfos.add(inputInfo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return inputInfos;
    }

    public class Input {
        private int id;
        private Timestamp dateInput;
        private float totalPrice;
        private List<InputInfo> inputInfos;

        public Input(int id, Timestamp dateInput, float totalPrice, List<InputInfo> inputInfos) {
            this.id = id;
            this.dateInput = dateInput;
            this.totalPrice = totalPrice;
            this.inputInfos = inputInfos;
        }

        // Getters and setters for all fields

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Timestamp getDateInput() {
            return dateInput;
        }

        public void setDateInput(Timestamp dateInput) {
            this.dateInput = dateInput;
        }

        public float getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(float totalPrice) {
            this.totalPrice = totalPrice;
        }

        public List<InputInfo> getInputInfos() {
            return inputInfos;
        }

        public void setInputInfos(List<InputInfo> inputInfos) {
            this.inputInfos = inputInfos;
        }
    }

    public class InputInfo {
        private int id;
        private int quantity;
        private float inputPrice;
        private float totalPrice;
        private String objectDisplayName;

        public InputInfo(int id, int quantity, float inputPrice, float totalPrice, String objectDisplayName) {
            this.id = id;
            this.quantity = quantity;
            this.inputPrice = inputPrice;
            this.totalPrice = totalPrice;
            this.objectDisplayName = objectDisplayName;
        }

        // Getters and setters for all fields

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public float getInputPrice() {
            return inputPrice;
        }

        public void setInputPrice(float inputPrice) {
            this.inputPrice = inputPrice;
        }

        public float getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(float totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getObjectDisplayName() {
            return objectDisplayName;
        }

        public void setObjectDisplayName(String objectDisplayName) {
            this.objectDisplayName = objectDisplayName;
        }
    }

    public List<Output> getOutputs() {
        List<Output> outputs = new ArrayList<>();
        String sql = "SELECT * FROM Outputs";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Output output = new Output(
                        rs.getInt("Id"),
                        rs.getTimestamp("DateOutput"),
                        rs.getFloat("TotalPrice"),
                        getOutputInfo(rs.getInt("Id"))
                );
                outputs.add(output);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return outputs;
    }

    private List<OutputInfo> getOutputInfo(int outputId) {
        List<OutputInfo> outputInfos = new ArrayList<>();
        String sql = "SELECT OutputInfo.*, Objects.DisplayName FROM OutputInfo INNER JOIN Objects ON OutputInfo.IdObject = Objects.Id WHERE IdOutput = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, outputId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                OutputInfo outputInfo = new OutputInfo(
                        rs.getInt("Id"),
                        rs.getString("Customer"),
                        rs.getInt("Quantity"),
                        rs.getFloat("OutputPrice"),
                        rs.getFloat("TotalPrice"),
                        rs.getString("DisplayName")
                );
                outputInfos.add(outputInfo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return outputInfos;
    }

    public class Output {
        private int id;
        private Timestamp dateOutput;
        private float totalPrice;
        private List<OutputInfo> outputInfos;

        public Output(int id, Timestamp dateOutput, float totalPrice, List<OutputInfo> outputInfos) {
            this.id = id;
            this.dateOutput = dateOutput;
            this.totalPrice = totalPrice;
            this.outputInfos = outputInfos;
        }

        // Getters and setters for all fields

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Timestamp getDateOutput() {
            return dateOutput;
        }

        public void setDateOutput(Timestamp dateOutput) {
            this.dateOutput = dateOutput;
        }

        public float getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(float totalPrice) {
            this.totalPrice = totalPrice;
        }

        public List<OutputInfo> getOutputInfos() {
            return outputInfos;
        }

        public void setOutputInfos(List<OutputInfo> outputInfos) {
            this.outputInfos = outputInfos;
        }
    }

    public class OutputInfo {
        private int id;
        private String customer;
        private int quantity;
        private float outputPrice;
        private float totalPrice;
        private String objectDisplayName;

        public OutputInfo(int id, String customer, int quantity, float outputPrice, float totalPrice, String objectDisplayName) {
            this.id = id;
            this.customer = customer;
            this.quantity = quantity;
            this.outputPrice = outputPrice;
            this.totalPrice = totalPrice;
            this.objectDisplayName = objectDisplayName;
        }

        // Getters and setters for all fields

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public float getOutputPrice() {
            return outputPrice;
        }

        public void setOutputPrice(float outputPrice) {
            this.outputPrice = outputPrice;
        }

        public float getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(float totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getObjectDisplayName() {
            return objectDisplayName;
        }

        public void setObjectDisplayName(String objectDisplayName) {
            this.objectDisplayName = objectDisplayName;
        }
    }
}
