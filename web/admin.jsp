<%-- 
    Document   : admin
    Created on : Feb 25, 2021, 7:04:07 PM
    Author     : BlankSpace
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Admin Page</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	<link rel="stylesheet" href="assets/css/customize.css">
	<script src="assets/js/main.js"></script>
    </head>
    <body>
	<jsp:include page="header.jsp"></jsp:include>
	    <div class="container-fluid my-4">
		<div class="row">
		    <!--left: search form-->
		    <div class="col-12 col-lg-3">
			<div class="text-center">
			    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#setting-quiz" >Setting Quiz</button>
			</div>
			<div class="card border-dark my-2">
			    <div class="card-header bg-success text-white text-uppercase">
				<i class="fa fa-search"></i> Search Question
			    </div>
			<c:set var="searchValue" value="${param.txtSearch}" />
			<c:set var="searchStatus" value="${param.cmbStatus}" />
			<c:set var="searchSubject" value="${param.cmbSubject}" />
			<c:set var="subjectList" value="${requestScope.SUBJECT_LIST}" />
			<div class="card-body">
			    <form id="search-question" action="search-question" class="form-group" method="POST">
				<label>Question Content</label>
				<input type="text" name="txtSearch" class="form-control" value="${searchValue}" placeholder="enter content" />
				<label>Subject</label>
				<select name="cmbSubject" class="form-control">
				    <option></option>
				    <c:forEach var="item" items="${subjectList}">
					<option value="${item.subId}"
						<c:if test="${item.subId eq searchSubject}">
						    selected="true"
						</c:if>>
					    ${item.subName}
					</option>
				    </c:forEach>
				</select>
				<label>Status</label>
				<select name="cmbStatus" class="form-control">
				    <option></option>
				    <option value="active"
					    <c:if test="${searchStatus eq 'active'}">
						selected="true"
					    </c:if>>
					Active
				    </option>
				    <option value="deactive"
					    <c:if test="${searchStatus eq 'deactive'}">
						selected="true"
					    </c:if>>
					Deactivate
				    </option>
				</select>
			    </form>
			</div>
			<div class="card-footer text-center">
			    <input class="btn btn-success" type="submit" value="Search" name="btAction" form="search-question" />
			</div>
		    </div>
		    <!--set time and num_of_ques modal-->
		    <div class="modal fade" id="setting-quiz">
			<div class="modal-dialog">
			    <div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
				    <h4 class="modal-title">Setting Quiz</h4>
				    <button type="button" class="close" data-dismiss="modal">×</button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
				    <label>Subject</label>
				    <select id="cmbSubject" name="cmbSubject" class="form-control">
					<c:forEach var="item" items="${subjectList}">
					    <option value="${item.subId}"
						    <c:if test="${item.subName eq question.subId}">
							selected="true"
						    </c:if>>
						${item.subName}
					    </option>
					</c:forEach>
				    </select>
				    <label>Number Of Question</label>
				    <select id="cmbNumOfQues" name="cmbNumOfQues" class="form-control">
					<option value="5">5</option>
					<option value="10">10</option>
				    </select>
				    <label>Time (minutes)</label>
				    <select id="cmbTimeTake" name="cmbTimeTake" class="form-control">
					<option value="1">1</option>
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
				    </select>
				</div>
				<!-- Modal footer -->
				<div class="modal-footer">
				    <button onclick="saveSettingQuiz()" class="btn btn-success">Save</button>
				    <button id="cancelSettingModal" type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
				</div>
			    </div>
			</div>
		    </div> 
		    <!--end of modal-->
		    <script>
			function saveSettingQuiz() {
			    var subId = document.getElementById("cmbSubject").value;
			    var numOfQues = document.getElementById("cmbNumOfQues").value;
			    var timeTake = document.getElementById("cmbTimeTake").value;

			    $.ajax({
				url: '/QuizOnline/setting-quiz',
				type: 'POST', //send it through method POST
				data: {
				    cmbSubject: subId,
				    cmbNumOfQues: numOfQues,
				    cmbTimeTake: timeTake
				},
				success: function () {
				    alert('Setting Saved!');
				    document.getElementById('cancelSettingModal').click();
				}
			    });
			}
		    </script>
		</div>
		<!-- end of search form-->

		<!--right: question card-->
		<div class="col-12 col-lg-9">
		    <h2 class="text-center">Question</h2>
		    <c:set var="questionList" value="${requestScope.QUESTION_LIST}" />
		    <c:if test="${not empty questionList}">

			<c:forEach var="question" items="${questionList}">
			    <c:set var="quesId" value="${question.questionId}"/>
			    <div class="card border-dark my-4">
				<div class="card-header">
				    <h5>ID Question: ${question.questionId}</h5>
				</div>
				<div class="card-body">
				    <div class="row">
					<!--question-->
					<div class="col-8 border-right">
					    <span style="font-weight: bold">${question.content}</span><br><br>
					    <p id="${question.questionId}-A">A. ${question.optionA}</p>
					    <p id="${question.questionId}-B">B. ${question.optionB}</p>
					    <p id="${question.questionId}-C">C. ${question.optionC}</p>
					    <p id="${question.questionId}-D">D. ${question.optionD}</p>
					    <span style="font-weight: bold">Answer: ${question.correct_answer}</span>
					    <style>
						#${quesId}-${question.correct_answer}{
						    color: red;
						}
					    </style>
					</div>
					<!--answer and modify-->
					<div class="col-4">
					    <p style="font-weight: bold">
						Status:
						<c:set var="status" value="${question.status}" />

						<c:if test="${status eq 'active'}">
						    <span style="color: green">${question.status}</span>
						</c:if>
						<c:if test="${status eq 'deactive'}">
						    <span style="color: red">${question.status}</span>
						</c:if>
					    </p>
					    <span style="font-weight: bold">
						Subject: 
					    </span> ${question.subId}
					    <br>
					    <span style="font-weight: bold">
						Create Date: 
					    </span> ${question.create_date}
					</div>
				    </div>
				</div>
				<div class="card-footer">
				    <div class="row justify-content-center">
					<form action="change-status" method="POST">
					    <input type="hidden" name="id" value="${quesId}" />
					    <input type="hidden" name="status" value="${question.status}" />
					    <input type="hidden" name="txtSearch" value="${searchValue}" />
					    <input type="hidden" name="cmbSubject" value="${searchSubject}" />
					    <input type="hidden" name="cmbStatus" value="${searchStatus}" />
					    <c:if test="${status eq 'active'}">
						<input class="btn btn-danger mr-4" type="submit" value="Deactive" onclick="return confirm('Confirm Deactive this question?');"/>
					    </c:if>
					    <c:if test="${status eq 'deactive'}">
						<input class="btn btn-success mr-4" type="submit" value="Active" />
					    </c:if>
					</form>
					<button type="button" class="btn btn-info" data-toggle="modal" data-target="#update-question-${quesId}" >Update</button>
				    </div>
				</div>
			    </div>
			    <!-- Update Question Modal -->
			    <div class="modal fade" id="update-question-${quesId}">
				<div class="modal-dialog">
				    <div class="modal-content">
					<form class="form-group" action="update-question" method="POST" onsubmit="return checkEqualString('${quesId}');">
					    <!-- Modal Header -->
					    <div class="modal-header">
						<h4 class="modal-title">Update Question</h4>
						<button type="button" class="close" data-dismiss="modal">×</button>
					    </div>
					    <!-- Modal body -->
					    <div class="modal-body">
						<input type="hidden" name="txtQuestionId" value="${quesId}" />
						<label>Content</label>
						<textarea class="form-control" aria-label="Question Content" name="txtContent" required>${question.content}</textarea>
						<label>Option A</label>
						<input id="optionA-${quesId}" class="form-control" type="text" name="txtOptionA" value="${question.optionA}" required/>
						<label>Option B</label>
						<input id="optionB-${quesId}" class="form-control" type="text" name="txtOptionB" value="${question.optionB}" required/>
						<label>Option C</label>
						<input id="optionC-${quesId}" class="form-control" type="text" name="txtOptionC" value="${question.optionC}" required/>
						<label>Option D</label>
						<input id="optionD-${quesId}" class="form-control" type="text" name="txtOptionD" value="${question.optionD}" required/>
						<label>Correct Option</label>
						<select name="cmbCorrectOption" class="form-control">
						    <option value="A"<c:if test="${question.correct_answer eq 'A'}">selected="true"</c:if>>A</option>
						    <option value="B"<c:if test="${question.correct_answer eq 'B'}">selected="true"</c:if>>B</option>
						    <option value="C"<c:if test="${question.correct_answer eq 'C'}">selected="true"</c:if>>C</option>
						    <option value="D"<c:if test="${question.correct_answer eq 'D'}">selected="true"</c:if>>D</option>
						    </select>
						    <label>Subject</label>
						    <select name="cmbSubject" class="form-control">
						    <c:forEach var="item" items="${subjectList}">
							<option value="${item.subId}"
								<c:if test="${item.subName eq question.subId}">
								    selected="true"
								</c:if>>
							    ${item.subName}
							</option>
						    </c:forEach>
						</select>
					    </div>
					    <!-- Modal footer -->
					    <div class="modal-footer">
						<input class="btn btn-success" type="submit" value="Save" name="btAction" />
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
					    </div>
					</form>
				    </div>
				</div>
			    </div>
			</c:forEach>

			<!--get current page-->
			<c:set var="currentPage" value="${requestScope.DEFAULT_PAGE_INDEX}"/>
			<c:if test="${empty currentPage}">
			    <c:set var="currentPage" value="${param.page}"/>
			</c:if>
			<c:set var="totalPages" value="${requestScope.TOTAL_PAGES}"/>
			<!--Page indicator-->
			<nav>
			    <ul class="pagination justify-content-center my-4">
				<c:forEach var="page" begin="1" end="${totalPages}" step="1">
				    <c:url var="urlPaging" value="search-question">
					<c:param name="txtSearch" value="${searchValue}"/>
					<c:param name="cmbSubject" value="${searchSubject}"/>
					<c:param name="cmbStatus" value="${searchStatus}"/>
					<c:param name="page" value="${page}"/>
				    </c:url>
				    <c:if test="${currentPage eq page}">
					<li class="page-item active">
					    <a class="page-link" href="${urlPaging}">${page}</a>
					</li>
				    </c:if>
				    <c:if test="${currentPage ne page}">
					<li class="page-item">
					    <a class="page-link" href="${urlPaging}">${page}</a>
					</li>
				    </c:if>
				</c:forEach>
			    </ul>
			</nav>
		    </div>
		    <!--end of column-->
		</c:if>
		<c:if test="${empty questionList}">
		    <h2 class="text-center">Questions not found!!</h2>
		</c:if>
	    </div>
	    <!--end of row-->

	    <!--error modal-->
	    <c:set var="errors" value="${requestScope.ERRORS}"/>
	    <c:if test="${not empty errors}">
		<!-- The Modal -->
		<div class="modal fade" id="errorModal">
		    <div class="modal-dialog">
			<div class="modal-content">
			    <div class="modal-header">
				<img src="assets/img/warning.png" alt="warning" width="40" height="40"><br/>
				<h4 class="modal-title">Warning</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
			    </div>
			    <div class="modal-body">
				<c:if test="${not empty errors.questionIdExistErr}">
				    <p class="text-danger">${errors.questionIdExistErr}</p>
				</c:if>
			    </div>
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

	    <!--success modal-->
	    <c:set var="success" value="${requestScope.SUCCESS}"/>
	    <c:if test="${success}">
		<c:set var="questionId" value="${requestScope.QUES_ID}"/>
		<!-- The Modal -->
		<div class="modal fade" id="successModal">
		    <div class="modal-dialog">
			<div class="modal-content">
			    <div class="modal-header">
				<img src="assets/img/success.png" alt="success" width="40" height="40"><br/>
				<h4 class="modal-title">Congratulations</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
			    </div>
			    <div class="modal-body">
				<h5>Update question: ${questionId} successfully!</h5>
			    </div>
			    <div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
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
	</div>
	<!--end of container-->
	<jsp:include page="footer.jsp"></jsp:include>
    </body>

</html>