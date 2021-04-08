<%-- 
    Document   : do-quiz
    Created on : Mar 6, 2021, 11:29:00 PM
    Author     : BlankSpace
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Do Quiz</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	<link rel="stylesheet" href="assets/css/customize.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="http://1892.yn.lt/blogger/JQuery/Pagging/js/jquery.twbsPagination.js" type="text/javascript"></script>
	<script src="assets/js/doQuiz.js"></script>
    </head>
    <body>
	<style>
	    .jumbotron {
		padding: 2rem 2rem !important;
	    }
	</style>
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
	<c:set var="quiz" value="${sessionScope.QUIZ}"/>
	<div class="container-fluid">
	    <div class="row justify-content-center mt-4">
		<!-- Hiên thị nút bấm -->
		<ul id="pagination"></ul>
	    </div>
	    <div class="row justify-content-center my-2">
		<!--quiz information-->
		<div class="col-2">
		    <div class="card border-dark text-center">
			<div class="card-header">
			    <h4>Subject: ${sessionScope.SUB_NAME}</h4>
			</div>
			<div class="card-body">
			    Time left: <span id="timer"></span>
			</div>
			<div class="card-footer">
			    <input class="btn btn-danger" type="submit" value="Finish" name="btAction" form="submit"/>
			</div>
		    </div>
		</div>

		<!--question-->
		<div class="col-6">
		    <c:set var="questionList" value="${quiz.questionList}"/>
		    <form id="submit" action="submit-quiz" method="POST">
			<c:forEach var="ques" items="${questionList}" varStatus="counter">
			    <div class="contentPage">
				<div class="card border-dark">
				    <div class="card-header">
					<h4>Question ${counter.count}</h4>
				    </div>
				    <div class="card-body">
					<input type="hidden" name="questionId${counter.count}" value="${ques.questionId}" />
					<span style="font-weight: bold">${ques.content}</span><br><br>
					<p><input type="radio" name="txtAnswer${counter.count}" value="A"> A. ${ques.optionA}</p>
					<p><input type="radio" name="txtAnswer${counter.count}" value="B"> B. ${ques.optionB}</p>
					<p><input type="radio" name="txtAnswer${counter.count}" value="C"> C. ${ques.optionC}</p>
					<p><input type="radio" name="txtAnswer${counter.count}" value="D"> D. ${ques.optionD}</p>
				    </div>
				</div>
			    </div>
			</c:forEach>
		    </form>
		</div>
	    </div>
	</div>
	<script>
	    $(function () {
		var pageSize = 1; // Hiển thị 1 câu hỏi trên 1 trang
		showPage = function (page) {
		    $(".contentPage").hide();
		    $(".contentPage").each(function (n) {
			if (n >= pageSize * (page - 1) && n < pageSize * page)
			    $(this).show();
		    });
		};
		showPage(1);
		///** Cần truyền giá trị vào đây **///
		var totalRows = ${sessionScope.TOTAL_PAGES}; // Tổng số câu hỏi hiển thị
		var btnPage = 5; // Số nút bấm hiển thị di chuyển trang
		var iTotalPages = Math.ceil(totalRows / pageSize);

		var obj = $('#pagination').twbsPagination({
		    totalPages: iTotalPages,
		    visiblePages: btnPage,
		    onPageClick: function (event, page) {
			showPage(page);
		    }
		});
	    });

	    window.onload = function () {
		var endTime = ${quiz.endTime.time};
		var currentTime = new Date().getTime();
		var duration = Math.round((endTime - currentTime) / 1000);
		startTime(duration);
	    };
	</script>
    </body>
</html>
