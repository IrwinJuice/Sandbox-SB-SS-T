/*
function getVinyls() {
    jQuery.ajax({
        type: 'GET',
        url: '/get_vinyls',
        success: function (response) {
            process(response);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
}
function process(response) {
    var vinyls = JSON.parse(response);
    var tblVinyls = document.getElementById("vinilboard");

    for (var i = 0; i < vinyls.length; i++) {
        var row = tblVinyls.insertRow();
        row.insertCell(0).innerHTML = vinyls[i].id;
        row.insertCell(1).innerHTML = vinyls[i].image;
    }
}*/

jQuery(function ($) {
    $(document).ready(function(){
        $('.carousel').carousel({
            interval: 3000
        });
    });
});
