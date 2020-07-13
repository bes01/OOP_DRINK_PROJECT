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
</head>
<STYLE>A {
    text-decoration: none;
} </STYLE>
<body>


<div style="position: relative; width: ${window.width()} px;">
    <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;">
        <a href="/Login">Logout</a>
    </div>
    <div style="position: absolute; top: 0; right: 70px; width: 100px; text-align:right;">
        <a href="/Favourites">Favourites</a>
    </div>
    <div style="position: absolute; top: 0; right: 180px; width: 100px; text-align:right;">
        <a href="/Search">Search</a>
    </div>

    <div>
        <font size="+3"><p style="font-family:'Courier New">Welcome, ${user.firstName} ${user.lastName}!</p></font>
        <font size="+2"><p style="font-family:'Courier New">Wanna drink? Don't hesitate, just do it!</p></font>
    </div>

    <div>
        <p>Nickname: ${user.nickName}</p>
        <p>Sex: ${user.sex}</p>
        <p>Age: ${user.age}</p>
        <p>eMail: ${user.mail}</p>
        <p>Current Rank: ${user.rank}</p>
    </div>

    <div>
        <a href="/addDrink">Wanna add a new drink? Click here!<br></a>
        <font size="+1"><b><br>My drinks:</b></font>
        <c:forEach items="${user.myDrinks}" var="drink">
            <ul>
                <li><a href="/drink"><font size="+3"
                                           style="font-family: 'French Script MT'">${drink.drinkName}</font></a></li>
            </ul>
        </c:forEach>
    </div>
</div>
</body>
</html>
