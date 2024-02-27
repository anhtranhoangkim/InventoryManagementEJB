<%@ page import="java.util.List" %>
<%@ page import="com.example.demo12.OutputBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Output Form</title>
    <link rel="stylesheet" href="style.css">
    <script>
        function addObject() {
            var objectList = document.getElementById('objectList');
            var newObject = document.createElement('div');

            newObject.innerHTML = `
                <div class="wrapper">
                    <select name="idObject" style="flex: 3">
                    <% for (OutputBean.Object object : (List<OutputBean.Object>) request.getAttribute("objects")) { %>
                        <option value="<%= object.getId() %>"><%= object.getDisplayName() %></option>
                    <% } %>
                    </select>
                    <input type="text" name="customer" placeholder="Khách hàng" required style="flex: 3">
                    <input type="number" name="quantity" placeholder="Số lượng" required style="flex: 2">
                    <input type="number" step="0.01" name="outputPrice" placeholder="Giá xuất" required style="flex: 2">
                    <br>
                    <br>
                </div>
            `;

            objectList.appendChild(newObject);
        }
    </script>
</head>
<body>
<div class="container">
    <h1>Thêm phiếu xuất</h1>
    <form action="output-servlet" method="post">
        <div id="objectList"></div>
        <div>
            <button type="button" onclick="addObject()">+ Thêm hàng hóa</button>
            <input type="submit" value="Tạo phiếu xuất">
            <button class="go-home" type="button" onclick="window.location.href='home.jsp'">Về trang chủ</button>
        </div>
    </form>
</div>
</body>
</html>
