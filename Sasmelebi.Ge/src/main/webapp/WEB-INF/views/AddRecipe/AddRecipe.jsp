<meta charset="utf-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <html  lang="en">
    <head>
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
            window.onload = RecreateDynamicTextboxes;
         </script>
        <title>Add Recipe</title>
    </head>
    <body>
        <h1>Add Recipe</h1>

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

            <c:choose>
                <c:when test="${path==null}">
                    <img src="/resources/photos/no_photo.png" width=200 height=250/>
                </c:when>
                <c:otherwise>
                    <img name="myimage" src="${path}" width=200 height=250/>
                </c:otherwise>
            </c:choose>
            <form method="POST" enctype="multipart/form-data" action="/addDrink" >
            				<input type="file" name="file" />
            				<input  type="submit" value="Upload" />
            </form>
            <form method="POST" enctype="multipart/form-data" action="/addDrink/submit" >
            <br></br>
                <label>Enter Name Of The Drink</label>
            <input id="name" type="text" name="name"/>
            <br></br>
            <label>Press add button to increase the number of textfields for ingredients</label>
            <input id="btnAdd" type="button" value="add" onclick="AddTextBox()" />
            <div name="TextBoxContainer" id="TextBoxContainer"></div>
            <br></br>
            <label>Type Instructions</label>
            <input name="instruction" type="text"/>
            <br></br>
            <input  type="submit" value="Add Recipe"  />
             <c:choose>
                <c:when test="${exists==true}">
                   <label>Such Drink Already Exists</label>
                </c:when>
             </c:choose>
             </form>
        </body>
<html>