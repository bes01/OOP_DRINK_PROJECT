<%--
  Created by IntelliJ IDEA.
  User: Ilia
  Date: 7/27/2020
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Forgot password</title>
    <link rel="stylesheet" href="/resources/css/Authentication/forgotpassword.css">
</head>
<div class="forgotpassword-page">
    <div class="form">
        <c:if test="${error != null}">
            ${error}
        </c:if>
        <form class="login-form" method="POST">
            <p>Please enter your mail to receive the password</p>
            <input type="email" name="mail" placeholder="Mail" value="${mail}" />
            <button type="submit">Send</button>
        </form>
    </div>
</div>