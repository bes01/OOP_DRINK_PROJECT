<%--
  Created by IntelliJ IDEA.
  User: Ilia
  Date: 7/16/2020
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="/register">Register</a>
<c:if test="${error != null}">
    Authentication failed: ${error}
</c:if>
<form method="POST">
    <label>Username</label>
    <input type="text" name="username" value="${username}" /><br/>
    <label>Password</label>
    <input type="password" name="password" /><br/>
    <button type="submit">Login</button>
</form>
</body>
</html>