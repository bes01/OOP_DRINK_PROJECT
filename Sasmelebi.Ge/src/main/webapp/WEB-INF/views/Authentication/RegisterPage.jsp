<%--
  Created by IntelliJ IDEA.
  User: Ilia
  Date: 7/17/2020
  Time: 4:07 AM
  To change this template use File | Settings | File Templates.
--%>


<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="/resources/css/Authentication/register.css">
</head>
<div class="register-page">
    <div class="form">
        <c:if test="${error != null}">
            ${error}
        </c:if>
        <form class="register-form" method="POST">
            <input type="text" name="name" placeholder="First Name" value="${name}" />
            <input type="text" name="last_name" placeholder="Last Name" value="${last_name}" />
            <input type="text" name="username" placeholder="Username" value="${username}" />
            <select name="sex" value="${sex}">
                <option value="" disabled selected>Select Sex</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select><br/>
            <input type="number" name="age" min="1" placeholder="Age" value="${age}" />
            <input type="email" name="mail" placeholder="Mail" value="${mail}" />
            <input type="password" name="password" placeholder="Password" />
            <input type="password" name="repeat_password" placeholder="Repeat Password" />
            <button type="submit">Register</button>
            <p class="message">Already registered? <a href="/">Sign In</a></p>
        </form>
    </div>
</div>