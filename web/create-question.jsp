<%-- 
    Document   : create-question-page
    Created on : Mar 4, 2021, 4:13:29 PM
    Author     : BlankSpace
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link rel="stylesheet" href="assets/css/customize.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
	<jsp:include page="header.jsp"></jsp:include>
	    <div class="container-fluid my-4">
		<div class="row justify-content-center">
		    <div class="col-6">			    
			<div class="card border border-dark">
			    <div class="card-header">
				<h2>Question's Info:</h2>
			    </div>
			    <div class="card-body">
			    <c:set var="subjectList" value="${requestScope.SUBJECT_LIST}"/>
			    <c:set var="status" value="${param.cmbStatus}" />
			    <c:set var="subject" value="${param.cmbSubject}" />
			    <form id="save-new-question" class="form-group" action="save-new-question" method="POST">
				<label>Question ID</label>
				<input id="txtQuesId" class="form-control" type="text" name="txtQuestionId" value="${param.txtQuestionId}" required/>
				<label>Content</label>
				<textarea id="content" class="form-control" aria-label="Question Content" name="txtContent" required>${param.txtContent}</textarea>
				<label>Option A</label>
				<input id="optionA" class="form-control" type="text" name="txtOptionA" value="${param.txtOptionA}" required/>
				<label>Option B</label>
				<input id="optionB" class="form-control" type="text" name="txtOptionB" value="${param.txtOptionB}" required/>
				<label>Option C</label>
				<input id="optionC" class="form-control" type="text" name="txtOptionC" value="${param.txtOptionC}" required/>
				<label>Option D</label>
				<input id="optionD" class="form-control" type="text" name="txtOptionD" value="${param.txtOptionD}" required/>
				<label>Correct Option</label>
				<select name="cmbCorrectOption" class="form-control">
				    <option value="A">A</option>
				    <option value="B">B</option>
				    <option value="C">C</option>
				    <option value="D">D</option>
				</select>
				<label>Subject</label>
				<select name="cmbSubject" class="form-control">
				    <c:forEach var="item" items="${subjectList}">
					<option value="${item.subId}"
						<c:if test="${item.subId eq subject}">
						    selected="true"
						</c:if>>
					    ${item.subName}
					</option>
				    </c:forEach>
				</select>
				<label>Status</label>
				<select name="cmbStatus" class="form-control">
				    <option value="active"
					    <c:if test="${status eq 'active'}">
						selected="true"
					    </c:if>>
					Active
				    </option>
				    <option value="deactive"
					    <c:if test="${status eq 'deactive'}">
						selected="true"
					    </c:if>>
					Deactivate
				    </option>
				</select>
			    </form>
			</div>
			<div class="card-footer text-center">
			    <input class="btn btn-danger" type="reset" value="Clear" form="save-new-question"/>
			    <input class="btn btn-success" type="submit" value="Save" name="btAction" form="save-new-question" />
			</div>
		    </div>
		</div>
	    </div>

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
				<c:if test="${not empty errors.optionEqualErr}">
				    <p class="text-danger">${errors.optionEqualErr}</p>
				</c:if>
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
		<!-- The Modal -->
		<div class="modal fade" id="successModal">
		    <div class="modal-dialog">
			<div class="modal-content">
			    <div class="modal-header">
				<img src="assets/img/success.png" alt="success" width="40" height="40"><br/>
				<h4 class="modal-title"> Congratulations</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>
			    </div>
			    <div class="modal-body">
				<h5>Created question successfully!</h5>
			    </div>
			    <div class="modal-footer">
				<a class="btn btn-danger" href="create-question">Okay</a>
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
	<jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
