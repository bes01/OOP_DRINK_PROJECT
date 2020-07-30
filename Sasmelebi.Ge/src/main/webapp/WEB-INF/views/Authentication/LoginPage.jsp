<%--
  Created by IntelliJ IDEA.
  User: Ilia
  Date: 7/16/2020
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/resources/css/Authentication/login.css">
</head>
<div class="login-page">
    <div class="form">
        <c:if test="${error != null}">
            ${error}
        </c:if>
        <form class="login-form" method="POST">
            <input type="text" name="username" placeholder="Username" value="${username}" />
            <input type="password" name="password" placeholder="Password" />
            <button type="submit">Login</button>
            <p class="message">Not registered? <a href="/Register">Create an account</a></p>
            <p class="message"> <a href="/ForgotPassword">Forgot your password?</a></p>
        </form>
    </div>
</div>