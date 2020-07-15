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
</div>
<h1>${drink.drinkName}</h1>
<h2>${drink.instruction}</h2>
<h3>Ingredients:</h3>
<ul>
    <c:forEach items="${drink.myIngredients}" var = "curr">
        <li>${curr.ingredientName}</li>
    </c:forEach>
</ul>

<form action="/Drink" method="get">
    <c:if test="">
    </c:if>
    <label>Rate this drink:</label>
    <select value = "ranking">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
    </select>
    <input type="submit" value="Save">
</form>
</body>
</html>