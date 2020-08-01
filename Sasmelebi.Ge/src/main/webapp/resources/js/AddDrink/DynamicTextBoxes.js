function GetDynamicTextBox(value){
    return '<input class="dynamicCameText" name = "DynamicTextBox" onkeyup="ListIngredients(value)" onkeydown = "ListIngredients(value)" type="text" value = "' + value + '" />' +
            '<input class="removeBut" type="button" value="Remove" onclick = "RemoveTextBox(this)" /> <br></br>'
}
function AddTextBox() {
    var div = document.createElement('DIV');
    div.innerHTML = GetDynamicTextBox("");
    document.getElementById("TextBoxContainer").appendChild(div);
}
function RemoveTextBox(div) {
    document.getElementById("TextBoxContainer").removeChild(div.parentNode);
}
