<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/12/2020
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" href="/resources/css/text.css">
    <link rel="stylesheet" href="/resources/css/links.css">
    <link rel="stylesheet" href="/resources/css/profile.css">
    <link rel="stylesheet" href="/resources/css/background.css">
    <link rel="stylesheet" href="/resources/css/button.css">
</head>
<body>

<div style="position: relative;   width: ${window.width()} px;">
    <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;">
        <a href="/Login">
            <logout>Logout</logout>
        </a>
    </div>
    <div style="position: absolute; top: 0; right: 70px; width: 100px; text-align:right;">
        <a href="/Discover">
            <Discover>Discover</Discover>
        </a>
    </div>
    <div style="position: absolute; top: 0; right: 160px; width: 100px; text-align:right;">
        <a href="/Search">
            <search>Search</search>
        </a>
    </div>

    <div>
        <Welcome><b>Welcome, ${user.firstName} ${user.lastName}!</b><br></Welcome>
        <doIt><br>Wanna drink? Don't hesitate, just do it!<br></doIt>
    </div>

    <div class="card">
        <p class="title">Nickname: ${user.nickName}</p>
        <p>eMail: ${user.mail}</p>
        <p>Sex: ${user.sex}</p>
        <p>Age: ${user.age}</p>
        <p>Current Rank: ${user.rank}</p>
    </div>

    <div>
        <a href="/user/add_recipe">Wanna add a new drink? Click here!<br></a>
    </div>
    <div>
        <p><font size="+2" style="font-family: sans-serif"> <b>My Drinks:</b></font></p>
        <c:forEach items="${user.myDrinks}" var="drink">
            <ul>
                <li style="margin-top:20px"><a href="/drink"><font class="DrinkLabels">${drink.drinkName}</font></a></li>
            </ul>
        </c:forEach>
    </div>
    <div style="position: absolute; left: 260px; top: 303px">
        <p><font size="+2" style="font-family: sans-serif"> <b>Favourites:</b></font></p>
        <ul>
            <c:forEach items="${favourites}" var="drink">
                <li style="margin-top: 20px;">
                    <a href="/Drink?drink_id=${drink.drinkId}"><font size="+3"
                                                                     style="font-family: 'French Script MT'">${drink.drinkName}</font></a>
                    <a href="/removeFavourite?drink_id=${drink.drinkId}" class="remFavButton">Remove</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <img style="position: absolute; left: 58%; top:40%" src="/resources/materials/vodka.png" width="550" ; height="250"
         ;>
</div>
</body>
</html>
