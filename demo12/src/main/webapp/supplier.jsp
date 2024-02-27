<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm nhà cung cấp</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <h1>Thêm nhà cung cấp</h1>
    <form action="supplier-servlet" method="post">
        <label for="displayName">Tên nhà cung cấp:</label>
        <input type="text" id="displayName" name="displayName" required>
        <label for="address">Địa chỉ:</label>
        <input type="text" id="address" name="address" required>
        <label for="phone">Số điện thoại:</label>
        <input type="text" id="phone" name="phone" required>
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required>
        <label for="moreInfo">Thông tin thêm:</label>
        <textarea id="moreInfo" name="moreInfo"></textarea>
        <label for="contractDate">Ngày ký hợp đồng:</label>
        <input type="date" id="contractDate" name="contractDate" required><br>
        <input type="submit" value="Thêm nhà cung cấp">
        <button class="go-home" type="button" onclick="window.location.href='home.jsp'">Về trang chủ</button>
    </form>
</div>
<% if (request.getAttribute("errorMessage") != null) { %>
<p><%= request.getAttribute("errorMessage") %></p>
<% } %>
</body>
</html>
