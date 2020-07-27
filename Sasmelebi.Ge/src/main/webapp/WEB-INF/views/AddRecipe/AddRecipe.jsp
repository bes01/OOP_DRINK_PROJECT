<meta charset="utf-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Drinks.Model.DataBase.RecipeDao.IngredientPrefix" %>
 <html  lang="en">
    <head>
        <link rel="stylesheet" href="/resources/css/AddRecipe/UploadButtons.css?1422585377">
        <link rel="stylesheet" href="/resources/css/AddRecipe/SubmitButton.css?1422585377">
        <link rel="stylesheet" href="/resources/css/AddRecipe/Background.css?1422585377">
        <link rel="stylesheet" href="/resources/css/AddRecipe/ImageConf.css?1422585377">
        <link rel="stylesheet" href="/resources/css/AddRecipe/HeaderConf.css?1422585377">
        <link rel="stylesheet" href="/resources/css/AddRecipe/TextFieldConf.css?1422585377">
        <link rel="stylesheet" href="/resources/css/AddRecipe/dynamicButton.css?1422585377">
        <link rel="stylesheet" href="/resources/css/AddRecipe/hyperLink.css?1422585377">
        <link rel="stylesheet" href="/resources/css/AddRecipe/Suggested.css?1422585377">
        <script src="${pageContext.request.contextPath}/resources/js/DynamicTextBoxes.js"></script>
         <script type="text/javascript">
            function RecreateDynamicTextboxes() {
                var values=new Array();
                <% String[] codes=(String[])request.getAttribute("Values");
                if(codes!=null){
                    for(int i=0; i<codes.length; i++){ %>
                        var code='<%= codes[i] %>';
                        values[<%= i %>]=code;
                    <%}
                }%>
                if (values != null) {
                    var html = "";
                    for (var i = 0; i < values.length; i++) {
                        html += "<div>" + GetDynamicTextBox(values[i]) + "</div>";
                    }
                    document.getElementById("TextBoxContainer").innerHTML = html;
                }
            }
            var values=new Array();
             <% IngredientPrefix data= (IngredientPrefix)pageContext.getServletContext().getAttribute("IngredientPrefix") ;
             String [] ingredients=(String []) data.getIngredientsByPrefix();
                 for(int i=0; i<ingredients.length; i++){ %>
                     var code='<%= ingredients[i] %>';
                     values[<%= i %>]=code;
                 <%
                 }
             %>;
             function ListIngredients(prefix){
                if (values != null) {
                    var html = "";
                    html+="<div>";
                    html+="<label class="+"header"+">Suggested Drinks</label><br></br>";

                    for (var i = 0; i < values.length; i++) {
                        if (i<8){
                            if (values[i].includes(prefix) == true){
                                html += "<label class="+"element"+">" + values[i] +  "</label><br></br>";
                            }
                        }
                    }
                    html+="</div>";
                    document.getElementById("PossibleIngredients").innerHTML = html;
                }
            }
            window.onload = RecreateDynamicTextboxes;
         </script>
        <title>Add Recipe</title>
    </head>
    <body>
        <h1 class="headerCL" style="display: block; margin-left: auto; margin-right: auto;">Good Choice! Add Drink</h1>

            <div  style="position: absolute; top: 80px; right: 10px; width: 100px; text-align:right;">
                <a class="hypLink" href="/HomePage">HomePage    </a>
            </div>
            <div style="position: absolute; top: 110px; right: 10px; width: 100px; text-align:right;">
                <a class="hypLink" href="/Search">Search</a>
            </div>
            <div style="position: absolute; top: 140px; right: 10px; width: 100px; text-align:right;">
                <a class="hypLink" href="/">Logout</a>
            </div>

            <div class="photo" style="display: block; margin-left: auto; margin-right: auto;">
                <img  style="display: block; margin-left: auto; margin-right: auto;" src="/resources/photos/no_photo.png" width=200 height=250/>
                <div class="container">
                    <p>CHOOSE AND UPLOAD PHOTO</p>
                </div>
            </div>
            <div>
            <form method="POST" enctype="multipart/form-data" action="/addDrink" >
                <input style="position: absolute; top:230px; left:20px;" class="fileButton" type="file" name="file" />
            	<input style="position: absolute; top:125px; left:20px;" class="photoButton" type="submit" value="UPLOAD PHOTO" />
            </form>
            </div>
            <form method="POST" enctype="multipart/form-data" action="/addDrink/submit" >
            <br></br>
            <div class="cart">
                <br></br>
                <p class="typeLabel">Enter Drink Name </p>
                <input class="nameText"  id="name" type="text" name="name"/>
                <p class="typeLabelInst">Type Instructions </p>
                <input class="instructionText" name="instruction" type="text"/>
                <br></br>
            </div>
             <div name="PossibleIngredients" style="position: absolute; left: 550px; top: 450px;" id="PossibleIngredients"></div>
            <div style="position: absolute; right: 30px; top: 50%;">
                <input class="photoButton" id="btnAdd" type="button" value="ADD INGREDIENTS" onclick="AddTextBox()" />
                <br></br>
                <br></br>

                <div name="TextBoxContainer" id="TextBoxContainer"></div>
                <br></br>
                <br></br>
            </div>

            <input class="submitButton" style="display: block; margin-left: auto; margin-right: auto;" type="submit" value="Add Recipe"  />
             <c:choose>
                <c:when test="${exists==true}">
                   <label>Such Drink Already Exists</label>
                </c:when>
             </c:choose>
             </form>
        </body>
<html>