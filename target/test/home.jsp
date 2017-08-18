<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="home.js" type="application/javascript"></script>
</head>
<body style="background-color: darkgoldenrod">
<div class = "todo" >
    <div class="todoHeader" style="text-align: center;">
        <h2 style="text-align: center;"> Welcome to our Todo App. <br> Let's get things done <br> <br></h2>
        <!-- Trigger the modal with a button -->
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Create  Todo</button>

        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">New Todo</h4>
                    </div>
                    <div class="modal-body">
                        <form class="w3-container">
                            <div class="w3-section">
                                <label><b>Title</b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Title" id="title" required>
                                <br><br><label><b>Body</b></label>
                                <textarea row="5" class="w3-input w3-border" type="text" placeholder="Enter Details here" id="body" required></textarea>
                                <br><button class="w3-button w3-block w3-green w3-section w3-padding" type="submit" onclick="addTodo()">Add todo</button>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
    </div>


    <div class="container-fluid" style=" background: white; margin-top: 5px; min-height: 90vh">
        <div class="row">
            <!--Newly added part-->
            <div class="col-md-4" > <h3 style="padding-left: 50px">Unassigned Tasks</h3>
                <div class="col-md-12 todo" id="unassigned">
                </div>
            </div>
            <!--In progress part-->
            <div class="col-md-4"> <h3 style="padding-left: 50px">Assigned Tasks</h3>
                <div class="col-md-12 todo" id="assigned">
                </div>
            </div>
            <!--Completed part-->
            <div class="col-md-4"> <h3 style="padding-left: 50px"> Completed Tasks </h3>
                <div class="col-md-12 todo" id="completed">
                </div>
            </div>
        </div>
    </div>

</div>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>