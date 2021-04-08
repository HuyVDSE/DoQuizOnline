<%-- 
    Document   : user
    Created on : Feb 25, 2021, 7:04:36 PM
    Author     : BlankSpace
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Home</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	<link rel="stylesheet" href="assets/css/customize.css">
	<script src="assets/js/main.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
	    <div class="container-fluid">
		<div class="row justify-content-center">
		    <div class="col-4">
			<div class="card border-dark my-5">
			    <div class="card-header text-center">
				<h3>Choose Quiz</h3>
			    </div>
			    <div class="card-body">
			    <c:set var="subjectList" value="${requestScope.SUBJECT_LIST}"/>
			    <table class="table text-center" border="1">
				<thead>
				    <tr class="table-primary">
					<th>No.</th>
					<th>Subject</th>
					<th>Action</th>
				    </tr>
				</thead>
				<tbody>
				    <c:forEach var="subject" items="${subjectList}" varStatus="counter">
				    <form action="attempt-quiz" method="POST">
					<tr>
					    <td>${counter.count}</td>
					    <td>
						<input type="hidden" name="subjectId" value="${subject.subId}" />
						<input type="hidden" name="subName" value="${subject.subName}" />
						${subject.subName}
					    </td>
					    <td>
						<input class="btn btn-success" type="submit" value="Attempt" name="btAction" />
					    </td>
					</tr>
				    </form>
				</c:forEach>
				</tbody>
			    </table>
			</div>
		    </div>
		</div>
	    </div>
	</div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
