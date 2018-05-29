jQuery(document).ready(function () {
    countInLocalStorage();
});

function countInLocalStorage() {
    let count = 0;
    for (var i = 0; i<localStorage.length; i++){
        key = localStorage.key(i);
        if(~key.indexOf('ID')){
            count++;
        }
    }
    document.getElementById("amount").textContent = ""+count;
}
function getVinylsFromLocalStorage() {
    var m = [];
    for (var i = 0; i<localStorage.length; i++){
        var keychik = localStorage.key(i);
        if(~keychik.indexOf('ID')){
           m[i] = keychik;
        }
    }
    console.log(m);
    return m;
}
function purchaseSheet() {

    jQuery.ajax({
        type: 'GET',
        url: '/get_vinyls',
        success: function (response) {
            process(response);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    })
}
    function process(response) {
        var vinyls = JSON.parse(response);
        console.log(vinyls);
        var ul = document.getElementById("ul");
        var arr = getVinylsFromLocalStorage();

        for(var i = 0; i<arr.length; i++) {
            var idKey = arr[i];
            var li = document.createElement('li');
            var lsgi = localStorage.getItem(idKey);

            console.log(lsgi);

            li.className = "list-group-item d-flex justify-content-between lh-condensed";

            for(var j=0; j<vinyls.length; j++){
                if(vinyls[j].id == lsgi){
                    li.innerHTML = '<img class="card-img" \
                    src= '+ vinyls[j].imageLink +' + alt="Card image cap" \
                    <div class="description"> \
                        <h6 class="my-0">' +  vinyls[j].title + '</h6> \
                         <small class="text-muted">' +  vinyls[j].artist + '</small> \
                     </div> \
                    <span class="text-muted">₴' + vinyls[j].price + '</span>';
                        ul.appendChild(li);
                }
            }

    }
}