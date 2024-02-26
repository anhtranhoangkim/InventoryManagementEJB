<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Input Form</title>
</head>
<body>
<h1>Input Form</h1>
<form action="input-servlet" method="post">
  <label for="id">ID:</label><br>
  <input type="text" id="id" name="id"><br>
  <input type="submit" value="Submit">
</form>
<% if (request.getAttribute("errorMessage") != null) { %>
<p><%= request.getAttribute("errorMessage") %></p>
<% } %>
</body>
</html>