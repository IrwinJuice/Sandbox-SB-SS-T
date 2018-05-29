jQuery(function ($) {
    $(document).ready(function () {
        $('.carousel').carousel({
            interval: 5000
        });
    });
});

jQuery(document).ready(findInLocalStorage());

function addCartToLocalStorage(id) {
    var getId = id;
    var cardKey = "ID" +  id;
    localStorage.setItem(cardKey, getId);
    findInLocalStorage();
    console.log(cardKey);
}
function findInLocalStorage() {
    let count = 0;
    for (var i = 0; i<localStorage.length; i++){
        key = localStorage.key(i);
        if(~key.indexOf('ID')){
            count++;
        }
    }
    document.getElementById("amount").textContent = ""+count;
}