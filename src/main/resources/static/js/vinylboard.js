jQuery(function ($) {
    $(document).ready(function () {
        $('.carousel').carousel({
            interval: 5000
        });
    });
});

function addCartToLocalStorage() {

    var vinylId = document.getElementById('idtest').innerText;///////////////////////////////////// ID
    console.log(vinylId);
/* /!*   var vinylId = vinyl/!*[[${vinyl.getId}]]*!/ ;*!/
    var randomKey = 'cardKey' + Math.floor(Math.random() * 100);
    localStorage.setItem(randomKey, vinylId);
    var localValue = localStorage.getItem(randomKey);
    console.log(localValue);*/
}