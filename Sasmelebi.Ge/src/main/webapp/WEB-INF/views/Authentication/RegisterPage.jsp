<%--
  Created by IntelliJ IDEA.
  User: Ilia
  Date: 7/17/2020
  Time: 4:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<c:if test="${error != null}">
    Registration failed: ${error}
</c:if>
<c:if test="${success != null}">
    Registration was successful ${success}
</c:if>
<form method="POST">
    <label>First Name</label>
    <input type="text" name="name" value="${name}" /><br/>
    <label>Last Name</label>
    <input type="text" name="last_name" value="${last_name}" /><br/>
    <label>Username</label>
    <input type="text" name="username" value="${username}" /><br/>
    <label>Sex</label>
    <select name="sex" id="sex" value="${sex}">
        <option value="male">Male</option>
        <option value="female">Female</option>
        <option value="other">Other</option>
    </select><br/>
    <label>Age</label>
    <input type="number" name="age" min="1" value="${age}" /><br/>
    <label>Mail</label>
    <input type="email" name="mail" value="${mail}" /><br/>
    <label>Password</label>
    <input type="password" name="password" /><br/>
    <label>Repeat Password</label>
    <input type="password" name="repeat_password" /><br/>
    <button type="submit">Register</button>
</form>
<form action="/">
    <button type="submit">Login</button>
</form>
</body>
</html>