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