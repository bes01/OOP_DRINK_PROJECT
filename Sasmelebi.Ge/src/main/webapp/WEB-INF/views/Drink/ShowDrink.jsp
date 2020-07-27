<%@ page import="Drinks.Model.DataBase.DrinkDao.TheDrinkData" %>
<%@ page import="Drinks.Model.Containers.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
<div class="first_layer">
    <div class="drink_info_center">
        <c:choose>
            <c:when test="${wrappedDrinkInfo.drinksAuthor.userId==user_id}">
                <a href="/HomePage" class="user_name"><h6> Your </h6></a>
            </c:when>
            <c:otherwise>
                <a href="/User?user_id=${wrappedDrinkInfo.drinksAuthor.userId}" class="user_name">
                    <h6>${wrappedDrinkInfo.drinksAuthor.nickName}'s</h6>
                </a>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="drink_info_center"><h1 class="drink_name"><b>${drink.drinkName}</b></h1></div>
    <% request.setAttribute("user_id", session.getAttribute("user_id"));%>
    <div>
        <img id="myImg" name="${drink.drinkName}" src="${drink.imagePath}" width=200 height=250/>
    </div>
    <div id="myModal" class="modal">
        <span class="close">&times;</span>
        <img class="modal-content" id="img01">
        <div id="caption"></div>
    </div>
    <script>
        var modal = document.getElementById('myModal');
        var img = document.getElementById('myImg');
        var modalImg = document.getElementById("img01");
        var captionText = document.getElementById("caption");
        img.onclick = function(){
            modal.style.display = "block";
            modalImg.src = this.src;
            captionText.innerHTML = this.alt;
        }
        var span = document.getElementsByClassName("close")[0];
        span.onclick = function() {
            modal.style.display = "none";
        }
    </script>
</div>
<%--
<form action="/Drink/Image" method="POST">
    <input type="hidden" value="${drink.imagePath}" name="imagePath">
    <input type="submit" value="Open Image" name="Open">
</form>
--%>
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
            <input class="common-btn" type="submit" value="Update">
        </c:when>
        <c:otherwise> <input class="common-btn" type="submit" value="Save"> </c:otherwise>
    </c:choose>
</form>

<div style="position: absolute; left: 60%; top:35%;">
    <form action="/Drink/extend" method="POST">
        <input type="hidden" value="${drink.drinkId}" name="drink_id">
        <input class="common-btn" type="submit" value="Click To Extend Drink!">
    </form>
    <form action="/Drink/favourite" method="POST">
        <input type="hidden" value="${drink.drinkId}" name="drink_id">
        <input class="common-btn" type="submit" value="Click To Add In Favourites!">
    </form>
</div>
</body>
</html>