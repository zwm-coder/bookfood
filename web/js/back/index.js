window.onload = function () {
    var items = document.querySelectorAll('li.item');
    for (var i = 0; i < items.length; i++) {
        items[i].addEventListener('click', function(e) {
            console.log(e)
        });
    }
};

function validateForm() {
    var flag = true;
    if (document.loginForm.username.value === ''){
        utils.addClass(document.loginForm.username, 'uk-form-danger');
        flag = false;
    }
    if (document.loginForm.password.value === ''){
        utils.addClass(document.loginForm.password, 'uk-form-danger');
        flag = false;
    }
    return flag;
}

utils = {
    hasClass: function (ele, name) {
        return !!ele.className.match(new RegExp("(\\s|^)" + name + "(\\s|$)"));

    },
    addClass: function (ele, name) {
        if (!this.hasClass(ele, name)){
            ele.className += " " + name;
        }

    }
}

function goBack() {
    history.back();
}