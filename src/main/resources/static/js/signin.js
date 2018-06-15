function checkLogin() {
    jQuery.ajax({
       type: 'POST',
        url: '/checkLog',
        data: {
            login: jQuery('#login').val()
        },
        success: function (response) {
            process(response)
        },
        error: function ( errorThrown ) {
            console.log(errorThrown);
        }
    });
    function process(response) {

        document.getElementById("email-alert").style.visibility = 'hidden';
        document.getElementById("password-alert").style.visibility = 'hidden';
        document.getElementById("password-chech-alert").style.visibility = 'hidden';

        if(response){
            document.getElementById("login").style.borderBottomColor = '#28a745';
            document.getElementById("login").style.color = '#28a745';
            document.getElementById("login-alert").style.visibility = 'hidden';

        }else{
            document.getElementById("login").style.borderBottomColor = 'red';
            document.getElementById("login").style.color = 'red';
            document.getElementById("login-alert").style.visibility = 'visible';
        }
    }
}
function checkEmail() {
    jQuery.ajax({
        type: 'POST',
        url: '/checkEmail',
        data: {
            email: jQuery('#email').val()
        },
        success: function (response) {
            process(response)
        },
        error: function ( errorThrown ) {
            console.log(errorThrown);
        }
    });
    function process(response) {
        document.getElementById("login-alert").style.visibility = 'hidden';
        document.getElementById("password-alert").style.visibility = 'hidden';
        document.getElementById("password-chech-alert").style.visibility = 'hidden';

        if(!response){
            document.getElementById("email").style.borderBottomColor = 'red';
            document.getElementById("email").style.color = 'red';
            document.getElementById("email-alert").style.visibility = 'visible';
        }else{
            document.getElementById("email-alert").style.visibility = 'hidden';
        }
    }
}
function checkPassword() {
    jQuery.ajax({
        type: 'POST',
        url: '/checkPassword',
        data: {
            password: jQuery('#password-check').val()
        },
        success: function (response) {
            process(response)
        },
        error: function ( errorThrown ) {
            console.log(errorThrown);
        }
    });
    function process(response) {
        document.getElementById("login-alert").style.visibility = 'hidden';
        document.getElementById("email-alert").style.visibility = 'hidden';
        document.getElementById("password-chech-alert").style.visibility = 'hidden';
        if(response){
            document.getElementById("password-check").style.borderBottomColor = '#28a745';
            document.getElementById("password-check").style.color = '#28a745';
            document.getElementById("password-alert").style.visibility = 'hidden';
        }else{
            document.getElementById("password-check").style.borderBottomColor = 'red';
            document.getElementById("password-check").style.color = 'red';
            document.getElementById("password-alert").style.visibility = 'visible';
        }
    }
}

function checkPasswordTrue() {
    document.getElementById("login-alert").style.visibility = 'hidden';
    document.getElementById("email-alert").style.visibility = 'hidden';
    document.getElementById("password-alert").style.visibility = 'hidden';

    if(document.getElementById("password-check") === document.getElementById("password-check-true")){
        document.getElementById("password-check-true").style.borderBottomColor = '#28a745';
        document.getElementById("password-check-true").style.color = '#28a745';
        document.getElementById("password-chech-alert").style.visibility = 'hidden';
    }else{
        document.getElementById("password-check").style.borderBottomColor = 'red';
        document.getElementById("password-check").style.color = 'red';
        document.getElementById("password-check-true").style.borderBottomColor = 'red';
        document.getElementById("password-check-true").style.color = 'red';
        document.getElementById("password-chech-alert").style.visibility = 'visible';
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
