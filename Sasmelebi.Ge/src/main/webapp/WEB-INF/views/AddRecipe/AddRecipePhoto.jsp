<meta charset="utf-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <html xmlns:th="https://www.thymeleaf.org">
    <head>
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
                    <img name="myimage" src="${path}" name="photo" width=200 height=250/>
            <form method="POST" enctype="multipart/form-data" action="/addDrink" >
            				<input type="file" name="file" />
            				<input  type="submit" value="Upload" />
            </form>
            <br></br>
            <form method="POST" enctype="multipart/form-data" action="/addDrink/photo/submit?image=${path}" >
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
                    <script type="text/javascript">
                        function GetDynamicTextBox(value){
                            return '<input name = "DynamicTextBox" type="text" value = "' + value + '" />' +
                                    '<input type="button" value="Remove" onclick = "RemoveTextBox(this)" />'
                        }
                        function AddTextBox() {
                            var div = document.createElement('DIV');
                            div.innerHTML = GetDynamicTextBox("");
                            document.getElementById("TextBoxContainer").appendChild(div);
                        }
                        function RemoveTextBox(div) {
                            document.getElementById("TextBoxContainer").removeChild(div.parentNode);
                        }
                    </script>
            </form>
        </body>
<html>