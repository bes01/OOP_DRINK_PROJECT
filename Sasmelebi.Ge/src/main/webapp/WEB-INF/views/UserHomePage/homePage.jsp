<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" href="/resources/css/homepage/forLabels.css">
    <link rel="stylesheet" href="/resources/css/homepage/hyperLinks.css">
    <link rel="stylesheet" href="/resources/css/homepage/profile.css">
    <link rel="stylesheet" href="/resources/css/homepage/butts.css">
    <link rel="stylesheet" href="/resources/css/homepage/wallColour.css">
    <link rel="stylesheet" href="/resources/css/homepage/listItem.css">
</head>
<body>
<div style="position: relative;  width: ${window.width()} px;">
    <div style="position: absolute; top: 0; right: 0; width: 100px; text-align:right;">
        <a href="/logout" class="featureLinks">
            <logout>Logout</logout>
        </a>
    </div>
    <div style="position: absolute; top: 0; right: 70px; width: 100px; text-align:right;" class="featureLinks">
        <a href="/Discover">
            <Discover>Discover</Discover>
        </a>
    </div>
    <div style="position: absolute; top: 0; right: 160px; width: 100px; text-align:right;" class="featureLinks">
        <a href="/Search">
            <search>Search</search>
        </a>
    </div>

    <div>
        <Welcome><b>Welcome, ${user.firstName} ${user.lastName}!</b><br></Welcome>
        <doIt><br>Wanna drink? Don't hesitate, just do it!<br></doIt>
    </div>

    <div class="card" style="margin-top: 35px">
        <p><font size="+1"><b>Nickname: ${user.nickName}</b></font></p>
        <p><b>eMail: ${user.mail}</b></p>
        <p><b>Sex: ${user.sex}</b></p>
        <p><b>Age: ${user.age}</b></p>
        <p><b>Current Rank: ${user.rank}</b></p>
    </div>
    <div>
        <p>
            <listHeader><b><br>My Drinks:</b></ListHeader>
        </p>
        <c:forEach items="${user.myDrinks}" var="drink">
            <ul>

                <li><img src="${drink.imagePath}" height="65" width="65" style="border-radius: 20px">
                    <a href="/Drink?drink_id=${drink.drinkId}"><font size="+3" class="list"
                                                                     style="font-family: 'French Script MT'">${drink.drinkName}</font></a>
                </li>
            </ul>
        </c:forEach>
    </div>
    <div style="position: absolute; left: 420px; top: 334px">
        <p>
            <listHeader><b>Favourites:</b></ListHeader>
        </p>
        <ul>
            <c:forEach items="${favourites}" var="drink">
                <li >
                    <a href="/Drink?drink_id=${drink.drinkId}" class="list"><font size="+3"
                                                                                  style="font-family: 'French Script MT'">
                        <img src="${drink.imagePath}" height="65" width="65" style="border-radius: 20px">
                            ${drink.drinkName}</font></a>
                    <a href="/removeFavourite?drink_id=${drink.drinkId}" class="remFavButton">Remove</a>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div style="position: absolute; left: 60%; top:190px;">
        <img src="/resources/materials/drinks.png" width=450; height=225;>
        <mixthings><p>Let's Mix Things Up!</p></mixthings>
        <a href="/addDrink">
            <button class="addDrink">Add Drink</button>
        </a>
    </div>
</div>
</body>
</html>
