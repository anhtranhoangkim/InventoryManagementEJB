<%@ page import="java.util.List" %>
<%@ page import="com.example.demo12.StatisticsBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Statistics</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="container" style="width: 700px;">
    <div class="container">
        <h2>Phiếu nhập</h2>
        <% for (StatisticsBean.Input input : (List<StatisticsBean.Input>) request.getAttribute("inputs")) { %>
        <table style="border: 1px solid #333; border-radius: 5px">
            <tr>
                <th>Id</th>
                <th>Ngày nhập</th>
                <th>Tổng giá trị</th>
            </tr>
            <tr>
                <td><%= input.getId() %></td>
                <td><%= input.getDateInput() %></td>
                <td><%= input.getTotalPrice() %></td>
            </tr>
            <tr>
                <td colspan="3">
                    <table class="subtable">
                        <tr>
                            <th>Id</th>
                            <th>Hàng hóa</th>
                            <th>Số lượng</th>
                            <th>Giá nhập</th>
                            <th>Tổng giá trị</th>
                        </tr>
                        <% for (StatisticsBean.InputInfo inputInfo : input.getInputInfos()) { %>
                        <tr>
                            <td><%= inputInfo.getId() %></td>
                            <td><%= inputInfo.getObjectDisplayName() %></td>
                            <td><%= inputInfo.getQuantity() %></td>
                            <td><%= inputInfo.getInputPrice() %></td>
                            <td><%= inputInfo.getTotalPrice() %></td>
                        </tr>
                        <% } %>
                    </table>
                </td>
            </tr>
        </table>
        <% } %>
    </div>
    <div class="container">
        <h2>Phiếu xuất</h2>
        <% for (StatisticsBean.Output output : (List<StatisticsBean.Output>) request.getAttribute("outputs")) { %>
        <table style="border: 1px solid #333; border-radius: 5px">
            <tr>
                <th>Id</th>
                <th>Ngày xuất</th>
                <th>Tổng giá trị</th>
            </tr>
            <tr>
                <td><%= output.getId() %></td>
                <td><%= output.getDateOutput() %></td>
                <td><%= output.getTotalPrice() %></td>
            </tr>
            <tr>
                <td colspan="3">
                    <table class="subtable">
                        <tr>
                            <th>Id</th>
                            <th>Hàng hóa</th>
                            <th>Khách hàng</th>
                            <th>Số lượng</th>
                            <th>Giá xuất</th>
                            <th>Tổng giá trị</th>
                        </tr>
                        <% for (StatisticsBean.OutputInfo outputInfo : output.getOutputInfos()) { %>
                        <tr>
                            <td><%= outputInfo.getId() %></td>
                            <td><%= outputInfo.getObjectDisplayName() %></td>
                            <td><%= outputInfo.getCustomer() %></td>
                            <td><%= outputInfo.getQuantity() %></td>
                            <td><%= outputInfo.getOutputPrice() %></td>
                            <td><%= outputInfo.getTotalPrice() %></td>
                        </tr>
                        <% } %>
                    </table>
                </td>
            </tr>
        </table>
        <% } %>
    </div>
</div>
</body>
</html>
