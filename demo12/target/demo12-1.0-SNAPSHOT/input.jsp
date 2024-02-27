<%@ page import="com.example.demo12.InputBean" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Input Form</title>
  <link rel="stylesheet" href="style.css">
  <script>
    function addObject() {
      var objectList = document.getElementById('objectList');
      var newObject = document.createElement('div');

      newObject.innerHTML = `
                <div class="container">
                    <select name="idObject">
                    <% for (InputBean.Object object : (List<InputBean.Object>) request.getAttribute("objects")) { %>
                        <option value="<%= object.getId() %>"><%= object.getDisplayName() %></option>
                    <% } %>
                    </select>
                    <input type="number" name="quantity" placeholder="Quantity" required>
                    <input type="number" step="0.01" name="inputPrice" placeholder="Input Price" required>
                    <br>
                    <br>
                </div>
            `;

      objectList.appendChild(newObject);
    }
  </script>
</head>
<body>
<form action="input-servlet" method="post">
  <div id="objectList"></div>
  <div>
    <button type="button" onclick="addObject()">Add Object</button>
    <input type="submit" value="Create Input">
    <button class="go-home" type="button" onclick="window.location.href='home.jsp'">Go Home</button>
  </div>
</form>
</body>
</html>
