<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <script>
        function add(){
            var container = document.getElementById("inputs");
            var element = document.getElementById("select");
            var break_line = document.createElement("br");
            var cln = element.cloneNode(true);
            container.appendChild(break_line);
            container.appendChild(cln);

        }
        function remove(){
            var elements = document.getElementsByClassName("select");
            if(elements.length <= 1)
                alert("You cant delete more Ingredients");
            else{
                var length = elements.length;
                var lastElem = elements[length-1]
                lastElem.parentNode.removeChild(lastElem);
                var br_lines = document.getElementById("inputs").getElementsByTagName("br");
                var br_lines_length = br_lines.length;
                var rem_br_line = br_lines[br_lines_length-1];
                rem_br_line.parentNode.removeChild(rem_br_line);
            }
        }
    </script>
    <style>
    A{
        text-decoration: none;

    }
    </style>
    <head>
        <link rel="stylesheet" href="/resources/css/Search/hyperLinks.css">
        <link rel="stylesheet" href="/resources/css/Search/buttons.css">
        <link rel="stylesheet" href="/resources/css/Search/labels.css">
        <link rel="stylesheet" href="/resources/css/Search/listItem.css">
        <link rel="stylesheet" href="/resources/css/Search/background.css">
        <title>Sasmelebi.ge </title>
    </head>

    <body>
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
         <a href="/HomePage">
             <homepage>Home Page</homepage>
         </a>
     </div>

        <form action="/Search" method="get">
            <div id="inputs">

             <label> Drink Name : </label>

            <input type="text" name="drink_name" , value="${last_search_name}"> <br>
            <label> Ingredients :  </label> <br>
            <c:if test ="${last_ingredients == null}">
            <br>
             <Select  name="ingredient" class ="select" id="select">
                    <option value="empty" selected ></option>
                    <c:forEach items="${all_ingredients}"  var = "current_ingredient">
                        <option value="${current_ingredient.getIngredientId()}">"${current_ingredient.getIngredientName()}"</option>
                    </c:forEach>
             </Select>
            </c:if>
            <c:if test ="${last_ingredients != null}">
                <c:forEach items="${last_ingredients}" var = "current_selected">
                <br>
                <Select  name="ingredient" class="select" id="select">
                <option value="empty"></option>
                    <c:forEach items="${all_ingredients}"  var = "current_ingredient">
                    <c:if test ='${current_ingredient.getIngredientId() != current_selected}'>
                        <option value="${current_ingredient.getIngredientId()}">"${current_ingredient.getIngredientName()}"</option>
                    </c:if>

                    <c:if test ='${current_ingredient.getIngredientId() == current_selected}'>
                        <option value="${current_ingredient.getIngredientId()}" selected >"${current_ingredient.getIngredientName()}"</option>
                    </c:if>
                    </c:forEach>
                </Select>
                </c:forEach>
            </c:if>


            </div>
           <br> <input type="submit" class="searchButton"  value = "Search"> <br>
          </form>
         <button onclick="add()" class="addIngredientButton">Add Ingredient</button>
         <button onclick="remove()" class="removeIngredientButton">Remove Ingredient</button>
         <hr>
         <ul>
         <c:forEach items="${drinks}" var="current_drink">
         <li>
            <img src="${current_drink.getImagePath()}" height="65" width="65" style="border-radius: 20px">
            <a href="/Drink?drink_id=${current_drink.getDrinkId()}">
             <font size="+3"   style="font-family: 'French Script MT'">
            "${current_drink.getDrinkName()}"  </a>
         </li>
         </c:forEach>
        </ul>
          <div style="position: absolute; left: 60%; top:190px;">
                <img src="/resources/materials/drinks.png" width=450; height=225;>
                <mixthings><p>Let's Mix Things Up!</p></mixthings>
                <a href="/addDrink">
                    <button class="addDrink">Add Drink</button>
                </a>
         </div>
    </body>
</html>