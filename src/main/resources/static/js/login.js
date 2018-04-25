function getVinyls() {
    jQuery.ajax({
        type: 'GET',
        url: '/get_vinyls',
        success: function (response) {
            process(response);
        },
        error: function ( errorThrown ) {
            console.log(errorThrown);
        }
    });
    function process(response) {

    }
}