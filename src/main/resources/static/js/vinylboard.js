jQuery(function ($) {
    $(document).ready(function () {
        $('.carousel').carousel({
            interval: 5000
        });
    });
});

jQuery(document).ready(function () {
    document.getElementById("amount").textContent = localStorage.length;
});

function addCartToLocalStorage(id) {
    var getId = id;
    var cardKey = "ID" +  id;
    localStorage.setItem(cardKey, getId);
    document.getElementById("amount").textContent = localStorage.length;
    console.log(cardKey);
}