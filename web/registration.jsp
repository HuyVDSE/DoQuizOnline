<%-- 
    Document   : registration
    Created on : Feb 25, 2021, 4:47:12 PM
    Author     : BlankSpace
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
	<title>Sign Up</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css">
	<link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
	<script src="https://kit.fontawesome.com/a81368914c.js"></script>
    </head>

    <body>
	<img class="wave" src="assets/img/wave.png">
	<div class="container">
	    <div class="img">
		<img src="assets/img/bg.svg">
	    </div>
	    <div class="login-content">
		<form action="registration" method="POST">
		    <img src="assets/img/avatar.svg">
		    <h2 class="title">Sign up</h2>
		    <div class="input-div one">
			<div class="i">
			    <i class="fas fa-envelope"></i>
			</div>
			<div class="div">
			    <h5>Email</h5>
			    <input type="text" class="input" name="txtEmail" value="${param.txtEmail}" required>
			</div>
		    </div>
		    <div class="input-div pass">
			<div class="i">
			    <i class="fas fa-user"></i>
			</div>
			<div class="div">
			    <h5>Username</h5>
			    <input type="text" class="input" name="txtUsername" value="${param.txtUsername}" required>
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
		    <div class="input-div pass">
			<div class="i">
			    <i class="fas fa-lock"></i>
			</div>
			<div class="div">
			    <h5>Confirm Password</h5>
			    <input type="password" class="input" name="txtConfirmPwd" value="" required>
			</div>
		    </div>
		    <a href="signin">Back to Login</a>
		    <input type="submit" class="btn" value="Create Account">
		</form>
	    </div>
	</div>

	<!--handle error-->
	<c:set var="errors" value="${requestScope.ERRORS}"/>
	<c:if test="${not empty errors}">
	    <!-- The Modal -->
	    <div class="modal fade" id="errorModal">
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
			<c:if test="${not empty errors.emailFormatErr}">
			    <p class="text-danger">${errors.emailFormatErr}</p>
			</c:if>
			<c:if test="${not empty errors.usernameLengthErr}">
			    <p class="text-danger">${errors.usernameLengthErr}</p>
			</c:if>
			<c:if test="${not empty errors.passwordLengthErr}">
			    <p class="text-danger">${errors.passwordLengthErr}</p>
			</c:if>
			<c:if test="${not empty errors.passwordConfirmErr}">
			    <p class="text-danger">${errors.passwordConfirmErr}</p>
			</c:if>
			<c:if test="${not empty errors.emailExistedErr}">
			    <p class="text-danger">${errors.emailExistedErr}</p>
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
		$("#errorModal").modal();
	    });
	</script>
    </c:if>

    <!--create account success-->
    <c:set var="success" value="${requestScope.SUCCESS}"/>
    <c:if test="${success}">
	<!-- The Modal -->
	<div class="modal fade" id="successModal">
	    <div class="modal-dialog">
		<div class="modal-content">

		    <!-- Modal Header -->
		    <div class="modal-header">
			<img src="assets/img/success.png" alt="success" width="40" height="40"><br/>
			<h4 class="modal-title">Congratulation!</h4>
			<button type="button" class="close" data-dismiss="modal">×</button>
		    </div>

		    <!-- Modal body -->
		    <div class="modal-body">
			<h5>Created account successfully!</h5>
		    </div>

		    <!-- Modal footer -->
		    <div class="modal-footer">
			<a href="signin" class="btn btn-success">Sign in now!</a> <br/>
		    </div>

		</div>
	    </div>
	</div>
	<script>
	    $(document).ready(function () {
		$("#successModal").modal();
	    });
	</script>
    </c:if>
    <script type="text/javascript" src="assets/js/main.js"></script>
</body>

</html>