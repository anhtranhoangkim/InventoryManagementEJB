<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Supplier Form</title>
</head>
<body>
<h1>Supplier Form</h1>
<form action="supplier-servlet" method="post">
    <label for="displayName">Display Name:</label><br>
    <input type="text" id="displayName" name="displayName" required><br>
    <label for="address">Address:</label><br>
    <input type="text" id="address" name="address" required><br>
    <label for="phone">Phone:</label><br>
    <input type="text" id="phone" name="phone" required><br>
    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email" required><br>
    <label for="moreInfo">More Info:</label><br>
    <textarea id="moreInfo" name="moreInfo"></textarea><br>
    <label for="contractDate">Contract Date:</label><br>
    <input type="date" id="contractDate" name="contractDate" required><br>
    <input type="submit" value="Submit">
</form>
<% if (request.getAttribute("errorMessage") != null) { %>
<p><%= request.getAttribute("errorMessage") %></p>
<% } %>
</body>
</html>
