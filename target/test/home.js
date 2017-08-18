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
    // console.log(params);
    http.send(params);
}

function getTodos() {
    var method = "get";
    var params = null;
    var url = "/test/home" + "?date=" + window.date;
    executer(method, url, params, formatter);
}

function appendHTML(todo, count) {
    var ret = " <div class=\"panel panel-default\"> " + " <div class=\"panel-heading\"> " +
        " <h4 class=\"panel-title\"> " + " <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse" + count.toString() + "\"> " +
        " Task " + count.toString() + ": "+ todo["title"] ;

    if(todo["status"] === "assigned") {
        ret += " : " + todo["assignee"] + " ";
    } else if(todo["status"] === "completed") {
        ret += " : " + todo["assignee"] + " ";
    }

    ret +=" </a> " + " </h4> " +  " </div> " +
        " <div id=\"collapse" + count.toString() + "\" class=\"panel-collapse collapse in\"> ";

    if(todo["status"] === "unassigned") {
        ret += " <button style='float: right' class=\"w3-button w3-block w3-green w3-section w3-padding\" type=\"submit\" onclick=\"updateStatus(" + todo["todoId"] +")\">Start</button> ";
    } else if(todo["status"] === "assigned") {
        ret += " <button style='float: right' class=\"w3-button w3-block w3-green w3-section w3-padding\" type=\"submit\" onclick=\"updateStatus(" + todo["todoId"] +")\">Complete</button> ";
    } else if(todo["status"] === "completed"){
        ret += " <button style='float: right' class=\"w3-button w3-block w3-green w3-section w3-padding\" type=\"submit\" onclick=\"updateStatus(" + todo["todoId"] +")\">Delete</button> ";
    }

       ret += " <div class=\"panel-body\"> " +
        " " + todo["body"] +  "  " + " </div> " + " </div> " + " </div> ";

    return ret;
}

function wrap(ret) {
    ret = " <div class=\"panel-group\" id=\"accordion\" style=\"width:250px\"> " + ret + " </div>";
    ret = "<div class=\"container\" > " + ret + " </div>";
    return ret;
}

function updateRecentTodos(todoList) {

    if( (typeof window.prevTodos) === "undefined") {
        window.prevTodos = [];
    }

    for(var i = 0; i < todoList.length; i++) {
        var isNewTodo = true;
        for(var j = 0; j < window.prevTodos.length; j++) {
            if(todoList[i]["todoId"] === window.prevTodos[j]["todoId"]) {
                isNewTodo = false;
                window.prevTodos[j] = todoList[i];
            }
        }
        if(isNewTodo) {
            window.prevTodos.push(todoList[i]);
        }
    }
}

function formatter(data) {
    data = JSON.parse(data);
    window.date = data["date"];
    var todoList = data["todoList"];
    updateRecentTodos(todoList);
    var unassignedHTML = "";
    var assignedHTML = "";
    var completedHTML = "";

    console.log("window todoList = " , window.prevTodos);

    var unass = 0, ass = 0, comp = 0;

    for(var i = 0; i < window.prevTodos.length; i++) {
        if(window.prevTodos[i]["status"] === "unassigned") {
            unass++;
            unassignedHTML += appendHTML(window.prevTodos[i], unass);
        } else if(window.prevTodos[i]["status"] === "assigned") {
            ass++;
            assignedHTML += appendHTML(window.prevTodos[i], ass);
        } else if(window.prevTodos[i]["status"] === "completed") {
            comp++;
            completedHTML += appendHTML(window.prevTodos[i], comp);
        }
    }

    completedHTML = wrap(completedHTML);
    unassignedHTML = wrap(unassignedHTML);
    assignedHTML = wrap(assignedHTML);

    // console.log("completed html", completedHTML);
    // console.log("unassigned html", unassignedHTML);
    // console.log("completed html", completedHTML);

    // window.addEventListener("load",  function(){
        document.getElementById("completed").innerHTML = completedHTML;
        document.getElementById("unassigned").innerHTML = unassignedHTML;
        document.getElementById("assigned").innerHTML = assignedHTML;
    // });
}

function addTodo() {
    var method = "post";
    var title = document.getElementById("title").value;
    var body = document.getElementById("body").value;
    var params = "title=" + title + "&body=" + body;
    var url = "/test/addTask";
    executer(method, url, params, registerFunc);
}

function registerFunc() {
    // window.location = "/test/home.html";
}

function updateStatusFunc() {
    getTodos();
}

function updateStatus(todoId) {
    var method = "post";
    var url = "/test/updateTask";
    var params = "todoId=" + todoId;
    for(var i = 0; i < window.prevTodos.length; i++) {
        if(window.prevTodos[i]["todoId"] === todoId) {
            params += "&status=" + window.prevTodos[i]["status" ] + "&date=" + window.date;
        }
    }
    console.log("update request is being made on todo id " + todoId + " " + params);
    executer(method, url, params, updateStatusFunc);
}

getTodos();