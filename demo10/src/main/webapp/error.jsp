<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
<h1>An error occurred while processing your request.</h1>
<p>Error Details:</p>
<pre><%= exception.getMessage() %></pre>
<p>Stack Trace:</p>
<pre><%
    exception.printStackTrace();
%></pre>
</body>
</html>