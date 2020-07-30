<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="nav_right">
                <li><a href="/logout" >LogOut</a></li>
            </div>
        </ul>
    </nav>
</header>
<div class="container">
    <div class="text" style="padding: 30px">
        <h1 class="drink_info_center user_name"><b>${user.firstName} ${user.lastName}</b><br></h1>
        <div class="row">
            <div class="column1">
                <% request.setAttribute("female", "Female");%>
                <% request.setAttribute("male", "Male");%>
                <c:choose>
                    <c:when test="${user.sex==male}">
                        <img id="myImg" src="resources/photos/male.jpg" width=250 height=250/>
                    </c:when>
                    <c:when test="${user.sex==female}">
                        <img id="myImg" src="resources/photos/female.jpg" width=250 height=250/>
                    </c:when>
                    <c:otherwise> <img id="myImg" src="resources/photos/noGender.jpg" width=250 height=250/> </c:otherwise>
                </c:choose>
                <div class="card" style="margin-top: 35px">
                    <p class="drink_des"><b>Nickname:</b><a class="link_stf_green"> ${user.nickName}</a></p>
                    <p class="drink_des"><b>Sex:</b><a class="link_stf_green"> ${user.sex}</a></p>
                    <p class="drink_des"><b>Age:</b><a class="link_stf_green"> ${user.age}</a></p>
                    <p class="drink_des"><b>Current Rank:</b><a class="link_stf_green"> ${user.rank}</a></p>
                    <p class="drink_des"><b>eMail: </b><a style="color: midnightblue" href="mailto:${user.mail}">${user.mail}</a></p>
                </div>
            </div>
            <div class="column2" style="margin-left: 6%; float: bottom">
                <img src="/resources/materials/drinks.png" style="width: 100%; height: auto;">
            </div>
        </div>
    </div>
</div>
<div class="container second" style="margin-bottom: 40px">
    <p><b>${user.firstName}'s Drinks:</b></p>
    <c:forEach items="${user.myDrinks}" var="drink">
        <ul>
            <li><a href="/Drink?drink_id=${drink.drinkId}">${drink.drinkName}</a></li>
        </ul>
    </c:forEach>
</div>
<div class="black_cont">
</div>
</body>
</html>
