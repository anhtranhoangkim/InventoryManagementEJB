<%@ page import="com.example.demo10.ObjectBean" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Object Form</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Object Form</h1>
<form action="object-servlet" method="post">
  <label for="id">ID:</label><br>
  <input type="text" id="id" name="id"><br>
  <label for="displayName">Display Name:</label><br>
  <input type="text" id="displayName" name="displayName"><br>
  <label for="idSupplier">ID Supplier:</label><br>
  <select id="idSupplier" name="idSupplier">
    <% for (ObjectBean.Supplier supplier : (List<ObjectBean.Supplier>) request.getAttribute("suppliers")) { %>
    <option value="<%= supplier.getId() %>"><%= supplier.getDisplayName() %></option>
    <% } %>
  </select><br>
  <input type="submit" value="Submit">
</form>
<% if (request.getAttribute("errorMessage") != null) { %>
<p><%= request.getAttribute("errorMessage") %></p>
<% } %>
<button onclick="location.href='newSupplier.jsp'">Add New Supplier</button>
</body>
</html>
