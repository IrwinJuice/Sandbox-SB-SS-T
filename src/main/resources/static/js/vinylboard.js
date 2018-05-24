jQuery(function ($) {
    $(document).ready(function () {
        $('.carousel').carousel({
            interval: 5000
        });
    });
});
function addCartToLocalStorage(id) {
    var getId = id;
    var cardKey = "ID" +  id;
    localStorage.setItem(cardKey, getId);
    console.log(cardKey);
}