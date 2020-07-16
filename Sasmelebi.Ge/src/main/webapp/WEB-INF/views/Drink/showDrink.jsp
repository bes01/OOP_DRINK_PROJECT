<%@ page import="Drinks.Model.DataBase.DrinkDao.TheDrinkData" %>
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
        <a href="/Login">Logout</a>
    </div>
    <div style="position: absolute; top: 0; right: 70px; width: 100px; text-align:right;">
        <a href="/Favourites">Favourites</a>
    </div>
    <div style="position: absolute; top: 0; right: 180px; width: 100px; text-align:right;">
        <a href="/Search">Search</a>
    </div>
</div>

<h1>${drink.drinkName} </h1>
<img name="${drink.drinkName}" src="${drink.imagePath}" width=200 height=250/>
<c:choose>
    <c:when test="${wrappedDrinkInfo.parentDrink.drinkId!=null}">
        <p>Extended from:</p>
        <a href="/Drink?drink_id=${wrappedDrinkInfo.parentDrink.drinkId}"> ${wrappedDrinkInfo.parentDrink.drinkName}</a>
    </c:when>
    <c:otherwise> <p>Original Post :)</p> </c:otherwise>
</c:choose>
<pre>Instruction: ${drink.instruction}</pre>

<pre>Drink's Current Rank: ${wrappedDrinkInfo.currentUserRanking}</pre>

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
<form action="/Drink" method="GET">
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
</body>
</html>