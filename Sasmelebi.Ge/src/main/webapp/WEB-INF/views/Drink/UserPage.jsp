<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sasmelebi.ge</title>
</head>
<body>
<div style="position: relative; width: ${window.width()} px;">
    <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;">
        <a href="/logout">Logout</a>
    </div>
    <div style="position: absolute; top: 0; right: 70px; width: 100px; text-align:right;">
        <a href="/Search">Search</a>
    </div>
    <div style="position: absolute; top: 0; right: 140px; width: 100px; text-align:right;">
        <a href="/HomePage">Home Page</a>
    </div>
</div>
<h1><b>${user.firstName} ${user.lastName}</b><br></h1>

<div class="card" style="margin-top: 35px">
    <p><b>Nickname:</b> ${user.nickName}</p>
    <p><b>eMail:</b> ${user.mail}</p>
    <p><b>Sex:</b> ${user.sex}</p>
    <p><b>Age:</b> ${user.age}</p>
    <p><b>Current Rank:</b> ${user.rank}</p>
</div>

<p><b>${user.firstName}'s Drinks:</b></p>
<c:forEach items="${user.myDrinks}" var="drink">
    <ul>
        <li><a href="/Drink?drink_id=${drink.drinkId}">${drink.drinkName}</a></li>
    </ul>
</c:forEach>
<div style="position: absolute; left: 60%; top:35%;">
    <img src="/resources/materials/drinks.png" width=450; height=225;>
</div>
</body>
</html>
