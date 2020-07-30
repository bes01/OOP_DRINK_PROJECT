var read_modal = document.getElementById("readModal");
var btn = document.getElementById("myBtn");

btn.onclick = function() {
    read_modal.style.display = "block";
}

var span_close = document.getElementsByClassName("close_tx")[0];

span_close.onclick = function() {
    read_modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        read_modal.style.display = "none";
    }
}