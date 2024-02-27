<%@ page import="java.util.List" %>
<%@ page import="com.example.demo12.InventoryBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <h1>Quản lý hàng tồn kho</h1>
    <form action="inventory-servlet" method="get">
        <input type="text" name="searchName" placeholder="Tìm theo tên">
        <input type="submit" value="Tìm kiếm">
    </form>
    <table>
        <tr>
            <th>Id</th>
            <th>Tên hàng</th>
            <th>Số lượng tồn</th>
        </tr>
        <% for (InventoryBean.Object object : (List<InventoryBean.Object>) request.getAttribute("objects")) { %>
        <tr>
            <td><%= object.getId() %></td>
            <td><%= object.getDisplayName() %></td>
            <td><%= object.getInventory() %></td>
        </tr>
        <% } %>
    </table>

</div>
</body>
</html>
