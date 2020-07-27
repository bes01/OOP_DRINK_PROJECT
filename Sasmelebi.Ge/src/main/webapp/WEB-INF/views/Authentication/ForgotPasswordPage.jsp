<%--
  Created by IntelliJ IDEA.
  User: Ilia
  Date: 7/27/2020
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Forgot password</title>
</head>
<h1>Please enter your mail to receive the password</h1>
<body>
<c:if test="${error != null}">
    Failed: ${error}
</c:if>
<form method="POST">
    <label>Mail</label>
    <input type="mail" name="mail" /><br/>
    <button type="submit">Send</button>
</form>
</body>
</html>