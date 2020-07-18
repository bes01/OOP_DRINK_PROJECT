<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Discover</title>
    <link rel="stylesheet" href="/resources/css/homepage/hype.css">
    <link rel="stylesheet" href="/resources/css/homepage/forLabels.css">
    <link rel="stylesheet" href="/resources/css/homepage/wallColour.css">
    <link rel="stylesheet" href="/resources/css/discoverpage/columns.css">
    <link rel="stylesheet" href="/resources/css/discoverpage/headLines.css">
    <link rel="stylesheet" href="/resources/css/discoverpage/li.css">
    <link rel="stylesheet" href="/resources/css/homepage/aTag.css">
</head>
<body>
<div style="position: relative;  width: ${window.width()} px;">
    <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;">
        <a href="/logout" class="featureLinks">
            <logout>Logout</logout>
        </a>
    </div>
    <div style="position: absolute; top: 0; right: 70px; width: 100px; text-align:right;" class="featureLinks">
        <a href="/HomePage">
            <homepage>HomePage</homepage>
        </a>
    </div>
    <div style="position: absolute; top: 0; right: 160px; width: 100px; text-align:right;" class="featureLinks">
        <a href="/Search">
            <search>Search</search>
        </a>
    </div>

    <div style="text-align: center">
        <h1>Discover and Explore Humongous Variety of Drinks!</h1>
    </div>

    <div style="align-items: center">
        <div id=leftbox>
            <h2>Top Drinks</h2>
            <c:forEach items="${topDrinks}" var="drink">
                <li><a href="" class="list"><item>${drink.drinkName}</item></a></li>
            </c:forEach>
        </div>

        <div id=middlebox>
            <h2>Random Drinks</h2>
            <c:forEach items="${randomDrinks}" var="drink">
                <li><a href="" class="list"><item>${drink.drinkName}</item></a></li>
            </c:forEach>
        </div>

        <div id=rightbox>

            <h2>Recently Added</h2>
            <c:forEach items="${recentlyAdded}" var="drink">
                <li><a href="" class="list"><item>${drink.drinkName}</item></a> </li>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
