<%-- 
    Document   : result
    Created on : Mar 7, 2021, 11:45:48 AM
    Author     : BlankSpace
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Quiz</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	<link rel="stylesheet" href="assets/css/customize.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
	<jsp:include page="header.jsp"></jsp:include>
	    <div class="container-fluid">
		<div class="row justify-content-center my-5">
		    <div class="col-4">
			<div class="card">
			    <div class="card-header">
				<h3 class="text-center">Quiz Result</h3>
			    </div>
			    <div class="card-body">
			    <c:set var="result" value="${requestScope.RESULT}"/>
			    <c:set var="subName" value="${requestScope.SUB_NAME}"/>
			    <span style="font-weight: bold">
				Subject: 
			    </span> ${subName}
			    <br>
			    <span style="font-weight: bold">
				UserId: 
			    </span> ${result.userId}
			    <br>
			    <span style="font-weight: bold">
				Create Date: 
			    </span> ${result.create_date}
			    <br>
			    <span style="font-weight: bold">
				Number of Correct: 
			    </span>${result.num_of_correct}/${result.num_of_question}
			    <br>
			    <span style="font-weight: bold">
				Score: 
			    </span>${result.score} out of 10.00
			</div>
		    </div>
		</div>
	    </div>
	</div>
    </body>
</html>
