function executer(method, url, params, callBackFunc) {

    var http = new XMLHttpRequest();
    http.open(method, url, true);

    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // http.setRequestHeader("Content-length", params.length);
    // http.setRequestHeader("Connection", "close");

    http.onreadystatechange = function() {//Call a function when the state changes.
        if(http.readyState == 4 && http.status == 200) {
            callBackFunc(http.responseText);
        }
    }
    console.log(params);
    http.send(params);
}

function userLogin() {
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    var method = "post";
    var params = "userName=" + userName + "&password=" + password;
    var url = "/test/login";
    executer(method, url, params, loginFunc);
}

function userRegister() {
    var userName = document.getElementById("userRName").value;
    var password = document.getElementById("rPassword").value;
    var emailId = document.getElementById("emailId").value;
    var phone = document.getElementById("phone").value;
    console.log(userName, password, emailId, phone);
    var method = "post";
    var params = "userName=" + userName + "&password=" + password + "&emailId=" + emailId + "&phone=" + phone;
    var url = "/test/register";
    console.log("user Register");
    executer(method, url, params, registerFunc);
}

function loginFunc(data) {
    data = JSON.parse(data);
    if(data["loginMessage"] === "success"){
        // window.location = "test/home.html"
    }
    else {
        alert(data["loginMessage"]);
    }
}

function registerFunc(data) {
    // window.location = "/test/home.html"
    console.log(data);
    console.log("Registration was successful\n");
}