<html>
    <script>
        function add(){
            var container = document.getElementById("inputs");
            var new_select = document.createElement("Select");
            var opt = document.createElement('option');
            var break_line = document.createElement("br");
            opt.value ="A";
            opt.text ="A";

            new_select.setAttribute("name","ingredient[]");
            new_select.appendChild(opt);
            container.appendChild(break_line);

            container.appendChild(new_select);

        }
    </script>
    <head>
        <title>Sasmelebi.ge </title>
    </head>

    <body>
        <form action="/Search" method="get">
            <div id="inputs">
            <label> Drink Name : </label>
            <input type="text" name="drink_name"> <br>
            <label> Ingredients :  </label> <br>
            <Select  name="ingredient[]"> <br>
                <option value="A">A</option>
            </Select>

            </div>
            <input type="submit" , value = "Search"> <br>
          </form>
         <button onclick="add()">Add Ingredient</button>
    </body>
</html>