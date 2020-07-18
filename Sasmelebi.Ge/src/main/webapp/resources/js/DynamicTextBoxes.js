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
