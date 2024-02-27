<%@ page import="com.example.demo12.ObjectBean" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Thêm mặt hàng</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
  <h1>Thêm mặt hàng</h1>
  <form action="object-servlet" method="post">
    <label for="displayName">Tên mặt hàng:</label>
    <input type="text" id="displayName" name="displayName" required>
    <label for="idSupplier">Nhà cung cấp:</label>
    <select id="idSupplier" name="idSupplier" required>
      <% for (ObjectBean.Supplier supplier : (List<ObjectBean.Supplier>) request.getAttribute("suppliers")) { %>
      <option value="<%= supplier.getId() %>"><%= supplier.getDisplayName() %></option>
      <% } %>
    </select><br>
    <input type="submit" value="Submit">
    <button class="go-home" type="button" onclick="window.location.href='home.jsp'">Go Home</button>
  </form>
</div>
<% if (request.getAttribute("errorMessage") != null) { %>
<p><%= request.getAttribute("errorMessage") %></p>
<% } %>
</body>
</html>
