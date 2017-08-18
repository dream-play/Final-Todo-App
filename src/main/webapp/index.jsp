<!DOCTYPE html>
<head>
    <title> Todo App </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="index.css">
</head>
<html>
    <body>
        <div class="container">
            <div class="row">
                <%--<div class="bs-example">--%>
                    <%--<!-- Button HTML (to Trigger Modal) -->--%>
                    <%--<a href="#myModal" class="btn btn-lg btn-primary" data-toggle="modal">Launch Demo Modal</a>--%>

                    <%--<!-- Modal HTML -->--%>
                    <%--<div id="myModal" class="modal fade">--%>
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title">Login & Register to use the TODO apps</h4>
                                </div>

                                <div class="modal-body">
                                    <div class="col-md-6 col-sm-6 no-padng">
                                        <div class="model-l">
                                            <form method="post" action ="/test/login" id="logFrm" class="log-frm" name="logFrm">
                                                <ul>
                                                    <li>User Name</li>
                                                    <input type="text" placeholder="User ID" id="userName" name="userName" class="form-control"  required/>
                                                    <li>Password</li>
                                                    <input type="password" placeholder="Password" id="password" name="password" class="form-control" />
                                                    <li><br/><button id="logBtn" class="btn btn-default">Login</button></li>
                                                    <div style="display:none;" id="loginFail" class="sign">
                                                        <li> <font color="red">  Username or password is incorrect.</font></li>
                                                    </div>
                                                </ul>
                                            </form>
                                            <div class="clearfix"></div>
                                            <form method="post" id="logFrm1" class="log-frm" name="logFrm1">
                                            </form>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-sm-6 no-padng">
                                        <div class="model-r">
                                            <div class="o-r">
                                                <span>OR</span>
                                            </div>
                                            <form method="post" action="/test/register" id="userRegisterFrm" class="log-frm" name="userRegisterFrm">
                                                <ul>
                                                    <li>User Name</li>
                                                    <input type="text" placeholder="User ID" id="userRName" name="userName" class="form-control">
                                                    <li>Phone Number</li>
                                                    <input type="text" placeholder="Phone Number" id="phone" name="phone" class="form-control">
                                                    <li>Email</li>
                                                    <input type="text" placeholder="Email Id" id="emailId" name="emailId" class="form-control">
                                                    <li>Password</li>
                                                    <input type="password" placeholder="Password" id="rPassword" name="password" class="form-control">
                                                    <br> <li><button name="userRegBtn" class="btn btn-default" >Signup Now</button></li>
                                                    <div style="display:none;" class="sign greenglow">
                                                        <li>   <i class="icon-check"></i><br>
                                                            <font color="red">
                                                                User registration successful.<br>
                                                                Your login Url already send to your email.

                                                            </font></li>
                                                    </div>
                                                    <div style="display:none;" id="regnSuc11" class="sign redglow">
                                                        <li>   <i class="icon-mail"></i><br>
                                                            <font color="red">    Email Exist.</font></li>
                                                    </div>
                                                </ul>
                                            </form>
                                        </div>
                                    </div>

                                    <div class="clearfix"></div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </div>
            </div>
        </div>

        <script type="application/javascript" src="index.js"> </script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
