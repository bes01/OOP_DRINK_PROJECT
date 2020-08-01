function ListIngredients(prefix){
    if (values != null) {
        var html = "";
        html+="<div>";
var k=0;
        for (var i = 0; i < values.length; i++) {
            if (k<8){
                if (values[i].includes(prefix) == true || prefix===""){
                    html += "<label class="+"element"+">" + values[i] +  "</label><br></br>";
                    k++;
                }
            }
        }
        html+="</div>";
        document.getElementById("PossibleIngredients").innerHTML = html;
    }
}