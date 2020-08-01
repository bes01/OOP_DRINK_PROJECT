<%@ page import="Drinks.Model.DataBase.DrinkDao.TheDrinkData" %>
<%@ page import="Drinks.Model.Containers.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sasmelebi.ge</title>
    <link rel="stylesheet" href="/resources/css/Drink/Background.css">
    <link rel="stylesheet" href="/resources/css/Drink/FixedBar.css">
    <link rel="stylesheet" href="/resources/css/Drink/Labels.css">
    <link rel="stylesheet" href="/resources/css/Drink/Buttons.css">
    <link rel="stylesheet" href="/resources/css/Drink/Image.css">
    <link rel="stylesheet" href="/resources/css/Drink/Container.css">
    <link rel="stylesheet" href="/resources/css/Drink/ComboBox.css">
    <link rel="stylesheet" href="/resources/css/Drink/ReadMore.css">
    <link rel="stylesheet" href="/resources/css/Drink/IngredientsList.css">
    <script src="${pageContext.request.contextPath}/resources/js/Logout/Logout.js"> </script>
</head>
<body>
<div class="grid">
    <header class="my_header">
        <img class="logo" src="/resources/materials/drinks.png" alt="logo">
        <nav class="my_nav">
            <ul class="nav_links">
                <li><a href="/HomePage" >Home</a></li>
                <li><a href="/Discover" >Discover</a></li>
                <li><a href="/Search" >Search</a></li>
                <%-- <li><a href="/About" >About</a></li> --%>
                <div class="nav_right">
                    <li><a class="featureLinks" onclick="logout()" style="cursor:pointer;">LogOut</a></li>
                </div>
            </ul>
        </nav>
    </header>
    <div class="container-fluid">
        <div class="container" style="padding-bottom: 4%">
            <h1 class="drink_info_center drink_name" style="padding-top: 10px"><b>${drink.drinkName}</b></h1>
            <div class="row">
                <div class="column1">
                    <div class="text" style="padding: 30px; padding-bottom: 7px;">
                        <img id="myImg" name="${drink.drinkName}" src="${drink.imagePath}" width=250 height=250/>

                        <c:choose>
                            <c:when test="${wrappedDrinkInfo.drinksAuthor.userId==user_id}">
                                <div style="padding-top: 35px">
                                    <p class="drink_des">Drink's Author:   <a href="/HomePage"  class="link_stf"  style="color: #ffcfdf"> You</a></p>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div style="padding-top: 35px">
                                    <p class="drink_des">Drink's Author:
                                        <a href="/User?user_id=${wrappedDrinkInfo.drinksAuthor.userId}" class="link_stf"
                                                                              style="color: #ffcfdf">
                                    ${wrappedDrinkInfo.drinksAuthor.nickName} </a></p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <% request.setAttribute("user_id", session.getAttribute("user_id"));%>
                        <c:choose>
                            <c:when test="${wrappedDrinkInfo.parentDrink.drinkId!=null}">
                                <p class="drink_des">Status: Extended from -
                                <a class="link_stf" href="/Drink?drink_id=${wrappedDrinkInfo.parentDrink.drinkId}">
                                        ${wrappedDrinkInfo.parentDrink.drinkName}</a></p>
                            </c:when>
                            <c:otherwise> <p class="drink_des">Status: Original Post <a class="link_stf"> :)</a></p> </c:otherwise>
                        </c:choose>
                        <p class="drink_des">Drink's Current Rank: <a class="link_stf">${wrappedDrinkInfo.currentRanking}</a></p>

                        <c:choose>
                            <c:when test="${wrappedDrinkInfo.currentUserRanking!=0}">
                                <p class="drink_des">Your latest score is ${wrappedDrinkInfo.currentUserRanking}!</p>
                                <p class="drink_des">You can update it: </p>
                            </c:when>
                            <c:otherwise><p class="drink_des">Rate this drink: </p></c:otherwise>
                        </c:choose>
                    </div>
                    <div style="padding-left: 30px;">
                        <form action="/Drink/Ranking" method="POST">
                            <div class="dropdown dropdown-dark">
                                <select name = "ranking_sc" class="dropdown-select">
                                    <c:forEach var = "i" begin = "1" end = "5">
                                        <option value = "${i}"><c:out value = "${i}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="hidden" value="${drink.drinkId}" name="drink_id">
                            <c:choose>
                                <c:when test="${wrappedDrinkInfo.currentUserRanking!=0}">
                                    <input class="common-btn" type="submit" value="Update">
                                </c:when>
                                <c:otherwise> <input class="common-btn" type="submit" value="Save"> </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
                </div>
                <div class="column2" style="margin-left: 7%">
                    <div style="padding-top: 40px">
                    <p>
                        <p class="instruction">Instruction:</p><p class="instruction_text" style="padding-left: 10%;
                         padding-bottom: 3%;">${drink.instruction}
                    </p>
                    </p>
                    </div>
                    <div style="padding-left: 10%;">
                        <button class="common-btn" id="myBtn">Read More</button>
                    </div>

                </div>
            </div>
        </div>

        <div class="container second" style="margin-bottom: 40px">
            <div class="row">
                <div class="column3">
                    <div class="div_ls">
                        <h2 class="h2_ls">Ingredients:</h2>
                        <ul class="ul_ls">
                            <c:forEach items="${drink.myIngredients}" var = "curr">
                                <li><a href="/Search?ingredient=${curr.ingredientId}"> ${curr.ingredientName}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="column2" style="margin-left: 0%">
                    <img id="Thinker" src="resources/photos/thinker.jpg" style="width: 100%; height: 100%"/>
                </div>
                <div class="column2" style="margin-left: 0%">
                    <img id="Lover" src="resources/photos/lover.jpg" style="width: 100%; height: 100%"/>
                </div>

                <div class="column4">
                    <form action="/Drink/favourite" method="POST">
                        <input type="hidden" value="${drink.drinkId}" name="drink_id">
                        <div class="btn_center">
                            <input class="common-btn-blue" type="submit" value="Click To Add In Favourites!">
                        </div>
                    </form>
                </div>
                <div class="column4">
                    <form action="/Drink/extend" method="POST">
                        <input type="hidden" value="${drink.drinkId}" name="drink_id">
                        <div class="btn_center">
                            <input class="common-btn-blue" type="submit" value="Click To Extend Drink!">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="black_cont">
        </div>
    </div>
    <div id="myModal" class="modal">
        <span class="close">&times;</span>
        <img class="modal-content" id="img01">
        <div id="caption"></div>
    </div>
    <div id="readModal" class="read">
        <div class="modal-content_tx">
            <span class="close_tx">&times;</span>
            <p class="drink_des">Instruction:</p>
            <p class="instruction_text">
                ${drink.instruction}
            </p>
        </div>
    </div>
    <%--
    <form action="/Drink/Image" method="POST">
        <input type="hidden" value="${drink.imagePath}" name="imagePath">
        <input type="submit" value="Open Image" name="Open">
    </form>
    --%>
</div>
</body>
<script src="resources/js/Drink/Image.js"></script>
<script src="resources/js/Drink/IngredientsTab.js"></script>
</html>