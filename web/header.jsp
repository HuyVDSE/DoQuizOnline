<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="account" value="${sessionScope.ACCOUNT}"/>
<div class="nav-bar navbar-dark navbar-expand-sm bg-success py-2">
    <ul class="navbar-nav ml-auto">
	<c:if test="${not empty account}">
	    <ul class="navbar-nav ml-auto">
		<li class="nav-item">
		    <div class="text-white">Welcome, ${account.username}</div>
		</li>
		<li class="nav-item">
		    <a href="logout" class="btn btn-light btn-sm my-2 my-sm-0 mx-3">Logout</a>
		</li>
	    </ul>
	</c:if>
    </ul>
</div>
<div class="jumbotron text-center" style="margin-bottom: 0">
    <h1>Quizzee!!</h1>
    <p>Practice your brain</p>
</div>
<!--nav bar-->
<div class="navbar navbar-dark navbar-expand-sm bg-dark justify-content-center">
    <ul class="navbar-nav">
	<c:if test="${ACCOUNT.role eq 'admin'}">
	    <li class="nav-item">
		<a class="nav-link active" href="adminHome">Home</a>
	    </li>
	    <li class="nav-item">
		<a class="nav-link" href="create-question">Create Question</a>
	    </li>
	</c:if>
	<c:if test="${ACCOUNT.role eq 'student'}">
	    <li class="nav-item">
		<a class="nav-link active" href="userHome">Home</a>
	    </li>
	</c:if>
	<li class="nav-item">
	    <a class="nav-link" href="#">Do Quiz History</a>
	</li>
    </ul>
</div>