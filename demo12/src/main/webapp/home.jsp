<%@ page import="com.example.demo12.UserSessionBean" %>
<% UserSessionBean userSessionBean = (UserSessionBean) request.getSession().getAttribute("userSessionBean"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div>
    <h1>Welcome to Home Page</h1>
    <% String username = (String) session.getAttribute("username"); %>
    <p>Welcome, <%= username %>!</p>

    <div>
        <a href="supplier-servlet">Thêm nhà cung cấp</a>
        <a href="object-servlet">Thêm mặt hàng</a>
        <a href="input-servlet">Thêm phiếu nhập</a>
        <a href="output-servlet">Thêm phiếu xuất</a>
        <a href="statistics-servlet">Kiểm kê kho</a>
        <a href="inventory-servlet">Quản lý tồn kho</a>
        <a href="logout">Đăng xuất</a>
    </div>
</div>
</body>
</html>