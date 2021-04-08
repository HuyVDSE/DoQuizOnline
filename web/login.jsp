<%-- 
    Document   : login
    Created on : Feb 25, 2021, 4:46:46 PM
    Author     : BlankSpace
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
	<title>Sign In</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
	<script src="https://kit.fontawesome.com/a81368914c.js"></script>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
    </head>

    <body>
	<img class="wave" src="assets/img/wave.png">
	<div class="container">
	    <div class="img">
		<img src="assets/img/bg.svg">
	    </div>
	    <div class="login-content">
		<form action="login" method="POST">
		    <img src="assets/img/avatar.svg">
		    <h2 class="title">Sign In</h2>
		    <div class="input-div one">
			<div class="i">
			    <i class="fas fa-envelope"></i>
			</div>
			<div class="div">
			    <h5>Email</h5>
			    <input type="text" class="input" name="txtEmail" value="" required>
			</div>
		    </div>
		    <div class="input-div pass">
			<div class="i">
			    <i class="fas fa-lock"></i>
			</div>
			<div class="div">
			    <h5>Password</h5>
			    <input type="password" class="input" name="txtPassword" value="" required>
			</div>
		    </div>
		    <a href="signup">Create an Account?</a>
		    <input type="submit" class="btn" value="Login">
		</form>
	    </div>
	</div>

	<c:set var="errors" value="${requestScope.ERRORS}" />
	<c:if test="${not empty errors}">
	    <!-- The Modal -->
	    <div class="modal fade" id="error">
		<div class="modal-dialog">
		    <div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
			    <img src="assets/img/warning.png" alt="warning" width="40" height="40"><br/>
			    <h4 class="modal-title">Warning</h4>
			    <button type="button" class="close" data-dismiss="modal">×</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">

			    <c:if test="${not empty errors.invalidUserErr}">
				<h5>${errors.invalidUserErr}</h5>
			    </c:if>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
			    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
			</div>

		    </div>
		</div>
	    </div>

	    <script>
		$(document).ready(function () {
		    $("#error").modal();
		});
	    </script>
	</c:if>


	<script type="text/javascript" src="assets/js/main.js"></script>
    </body>

</html>