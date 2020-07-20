<%@ page import="Drinks.Model.DataBase.DrinkDao.TheDrinkData" %>
<%@ page import="Drinks.Model.Containers.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
    <div style="position: absolute; top: 40px; right: 0; width: 150px; text-align:right;">
        <h4>Date: ${drink.additionTime}</h4>
    </div>
</div>
<h1><b>${drink.drinkName}</b></h1>
<% request.setAttribute("user_id", session.getAttribute("user_id"));%>
<div>
    <div>
    <c:choose>
        <c:when test="${wrappedDrinkInfo.drinksAuthor.userId==user_id}">
            <a href="/HomePage">By You </a>
        </c:when>
        <c:otherwise>
            <a href="/User?user_id=${wrappedDrinkInfo.drinksAuthor.userId}">
                By ${wrappedDrinkInfo.drinksAuthor.nickName}
            </a>
        </c:otherwise>
    </c:choose>
    </div>
    <div>
        <img name="${drink.drinkName}" src="${drink.imagePath}" width=200 height=250/>
    </div>
</div>

<form action="/Drink/Image" method="POST">
    <input type="hidden" value="${drink.imagePath}" name="imagePath">
    <input type="submit" value="Open Image" name="Open">
</form>
<c:choose>
    <c:when test="${wrappedDrinkInfo.parentDrink.drinkId!=null}">
        <p>Extended from:</p>
        <a href="/Drink?drink_id=${wrappedDrinkInfo.parentDrink.drinkId}">
                ${wrappedDrinkInfo.parentDrink.drinkName}</a>
    </c:when>
    <c:otherwise> <p>Original Post :)</p> </c:otherwise>
</c:choose>
<pre>Instruction: ${drink.instruction}</pre>

<pre>Drink's Current Rank: ${wrappedDrinkInfo.currentRanking}</pre>

<h4>Ingredients:</h4>
<ul>
    <c:forEach items="${drink.myIngredients}" var = "curr">
        <li><a href="/Search?ingredient=${curr.ingredientId}"> ${curr.ingredientName}</a></li>
    </c:forEach>
</ul>
<c:choose>
    <c:when test="${wrappedDrinkInfo.currentUserRanking!=0}">
        <p>Your latest score is ${wrappedDrinkInfo.currentUserRanking}!</p>
        <p>You can update it: </p>
    </c:when>
    <c:otherwise><p>Rate this drink:</p></c:otherwise>
</c:choose>
<form action="/Drink/Ranking" method="POST">
    <select name = "ranking_sc" id ="select">
        <c:forEach var = "i" begin = "1" end = "5">
            <option value = "${i}"><c:out value = "${i}"/></option>
        </c:forEach>
    </select>
    <input type="hidden" value="${drink.drinkId}" name="drink_id">
    <c:choose>
        <c:when test="${wrappedDrinkInfo.currentUserRanking!=0}">
            <input type="submit" value="Update">
        </c:when>
        <c:otherwise> <input type="submit" value="Save"> </c:otherwise>
    </c:choose>
</form>

<div style="position: absolute; left: 60%; top:35%;">
    <img src="/resources/materials/drinks.png" width=450; height=225;>
    <form action="/Drink/extend" method="POST">
        <input type="hidden" value="${drink.drinkId}" name="drink_id">
        <input type="submit" value="Click To Extend Drink!">
    </form>
    <form action="/Drink/favourite" method="POST">
        <input type="hidden" value="${drink.drinkId}" name="drink_id">
        <input type="submit" value="Click To Add In Favourites!">
    </form>
</div>
</body>
</html>