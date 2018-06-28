function verifyAndSave() {
    jQuery.ajax({
        type: 'POST',
        url: '/signup',
        data: {
            login: jQuery('#login').val(),
            email: jQuery('#email').val(),
            password: jQuery('#password-check').val()
        },
        success: function (response) {
            process(response);
        },
        error: function ( errorThrown ) {
            console.log(errorThrown);
        }
    });
    function process(response) {
        JSON.parse(response, function(key, value){
            if(key == 'errorNull' & value == false){
                if(!document.getElementById('null-alert')){

                    var parentElement = document.getElementById('sing-up-form');
                    var theFirstChild = parentElement.firstChild;

                    let div = document.createElement('div');
                    div.id = 'null-alert';
                    div.innerHTML = 'All fields must be filled!';

                    parentElement.insertBefore(div, theFirstChild);
                }
            }
            if(key == 'errorLogin' & value == false){
                if(!document.getElementById('login-alert')) {
                    var parentElement = document.getElementById('sing-up-form');
                    var theFirstChild = parentElement.firstChild;

                    let div = document.createElement('div');
                    div.id = 'login-alert';
                    div.innerHTML = 'User with such login exists!';

                    parentElement.insertBefore(div, theFirstChild);
                }
            }
            if(key == 'errorEmail' & value == false) {
                if(!document.getElementById('email-alert')) {
                    var parentElement = document.getElementById('sing-up-form');
                    var theFirstChild = parentElement.firstChild;

                    let div = document.createElement('div');
                    div.id = 'email-alert';
                    div.innerHTML = 'User with such e-mail exists!';

                    parentElement.insertBefore(div, theFirstChild);
                }
            }
            if(key == 'save' & value == true) {
                alert(
                    "Successfully! Please \"Sign In\" with your account"
                );
                location.reload();
            }
        });
    }

}


function checkPasswordTrue() {

    if(document.getElementById("password-check") === document.getElementById("password-check-true")){
        document.getElementById("password-check-true").style.borderBottomColor = '#28a745';
        document.getElementById("password-check-true").style.color = '#28a745';
    }else{
        document.getElementById("password-check").style.borderBottomColor = 'red';
        document.getElementById("password-check").style.color = 'red';
        document.getElementById("password-check-true").style.borderBottomColor = 'red';
        document.getElementById("password-check-true").style.color = 'red';
        if(!document.getElementById('password-check-alert')) {
            var parentElement = document.getElementById('sing-up-form');
            var theFirstChild = parentElement.firstChild;

            let div = document.createElement('div');
            div.id = 'password-check-alert';
            div.innerHTML = 'Passwords do not match!';

            parentElement.insertBefore(div, theFirstChild);
        }
    }
}
/**
 * Variables
 */
const signupButton = document.getElementById("signup-button"),
    loginButton = document.getElementById("login-button"),
    userForms = document.getElementById("user_options-forms");

/**
 * Add event listener to the "Sign Up" button
 */
signupButton.addEventListener(
    "click",
    () => {
    userForms.classList.remove("bounceRight");
userForms.classList.add("bounceLeft");
},
false);

/**
 * Add event listener to the "Login" button
 */
loginButton.addEventListener(
    "click",
    () => {
    userForms.classList.remove("bounceLeft");
userForms.classList.add("bounceRight");
},
false
);