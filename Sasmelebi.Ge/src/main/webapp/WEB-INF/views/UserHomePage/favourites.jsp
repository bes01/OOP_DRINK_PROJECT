<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/14/2020
  Time: 4:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Favourites</title>
</head>
<STYLE>A {
    text-decoration: none;
} </STYLE>
<body>
<div style="position: relative; width: ${window.width()} px">
    <div>
        <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;">
            <a href="/Login">Logout</a>
        </div>
        <div style="position: absolute; top: 0; right: 70px; width: 100px; text-align:right;">
            <a href="/HomePage">HomePage</a>
        </div>
        <div style="position: absolute; top: 0; right: 180px; width: 100px; text-align:right;">
            <a href="/Search">Search</a>
        </div>
        <p><font size="+2" style="font-family: 'sans-serif'">Favourites:</font></p>
        <ul>
            <c:forEach items="${favourites}" var="drink">
                <li>
                    <form action="/Favourites?drink_id=${drink.drinkId}" method="post">
                        <a href="/${drink.drinkId}"><font size="+3"
                                               style="font-family: 'French Script MT'">${drink.drinkName}</font></a>
                        <button type="submit" style="margin: 5px">Remove</button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
