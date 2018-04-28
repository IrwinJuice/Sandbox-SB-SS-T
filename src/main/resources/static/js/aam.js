/*jQuery(document).ready(function () {
    jQuery('a').each(function (index) {
        if (this.href.indexOf("http://site.ru") != 0) {
            this.href = "http://site.ru/go.html?" + this.href;
        }
    });
});*/
function addNewVinyl(event) {
    jQuery.ajax({
        type: 'POST',
        url: '/completeApp/addNewVinyl',
        data: {
            title:    jQuery('#title').val(),
            artist: jQuery('#artist').val(),
            price:   jQuery('#price').val(),
            quantity:   jQuery('#quantity').val()
        /*    file: $('#file').val()*/
        },
        success: function () {
            event.preventDefault();
        },
        error: function ( errorThrown ) {
            console.log(errorThrown);
        }
    });
}
