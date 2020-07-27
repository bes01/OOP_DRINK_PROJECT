<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sasmelebi.ge</title>
    <link rel="stylesheet" href="/resources/css/homepage/backImg.css">
    <link rel="stylesheet" href="/resources/css/Drink/FixedBar.css">
    <link rel="stylesheet" href="/resources/css/Drink/Labels.css">
    <link rel="stylesheet" href="/resources/css/Drink/Buttons.css">
    <link rel="stylesheet" href="/resources/css/Drink/Image.css">
</head>
<body>
<header class="my_header">
    <img class="logo" src="/resources/materials/drinks.png" alt="logo">
    <nav class="my_nav">
        <ul class="nav_links">
            <li><a href="/HomePage" >Home</a></li>
            <li><a href="/Discover" >Discover</a></li>
            <li><a href="/Search" >Search</a></li>
            <li><a href="/About" >About</a></li>
            <%-- About is not decided --%>
            <div class="nav_links-right">
                <li><a href="/logout" >LogOut</a></li>
            </div>
        </ul>
    </nav>
</header>
<h1 class="user_name"><b>${user.firstName} ${user.lastName}</b><br></h1>

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
