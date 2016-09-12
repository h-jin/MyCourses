<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
</script>

<script type="text/javascript">

/*function validateForm() {
	var x = document.forms["gradeForm"]["grade"].value;
	  
	 var user = /^[A-Z\&]+$/;
    
    if (!(x.trim().match(user))) {
        alert("invalid grade");
        return false;
    }
}*/

$(document).ready(function(){
	   $("#button").click(function(){
	     $("#myForm").serialize();
	   });
	});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">

<title>Update grade</title>
</head>
<body>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
			<h3><font color="grey">MyCourses</font></h3>
		</div>
		<div class="navbar-collapse collapse">
          		<ul class="navbar-form navbar-right" >
				  <li><a class="btn btn-success " href="logout.jsp"> Log Out</a></li>
				 </ul>		
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/MyCourses/teacher.jsp">Home</a></li>
					<li><a  >About</a></li>
					<li><a  >Contact</a></li>
				</ul>
        </div>	
	  </div>
</div>
<div class="container">
	
	<div class="row row-offcanvas row-offcanvas-right">
	
		<div class="col-xs-12 col-sm-9">
			<div class="jumbotron">	
			
			<c:out value="${year}" />
			<c:out value="${semester}" /> <br>
			<c:out value="${course_name}" />
		
			<hr>
	     <form role="form" id="grade" name="gradeForm" method="post" action="GradeController?action=addGrade&courseid=<c:out value="${course_id}" />&coursename=<c:out value="${course_name}" />&year=<c:out value="${year}" />&semester=<c:out value="${semester}" />&user=<%=session.getAttribute("user")%>" onsubmit="return validateForm()" >	
	 			   
	 						
		<table class="table table-hover">
				<thead>
					<tr class="active">
						<th>First Name</th>
						<th>Last Name</th>
						<th>Student ID</th>
						<th>Grade</th>
						
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courses}" var="cs">
					
			   		<tr>
						<td > <input type="text"  class="form-control" name="fname" value="<c:out value="${cs.firstname}" />" readonly></td>
						<td > <input type="text"  class="form-control" name="lname" value="<c:out value="${cs.lastname}" />" readonly></td>
						<td > <input type="text"  class="form-control" name="id" value="<c:out value="${cs.id}" />" readonly></td>
						<td > <input type="text" maxlength="2" class="form-control" name="grade" value="<c:out value="${cs.grade}" />"></td>
				
					</tr>	
						
					</c:forEach>
				</tbody>
			</table>
			<button id="button" type="submit" class="btn btn-primary pull-right" >save </button>
			</form>
			<c:out value="${grades}" />		
			</div>	
		</div>
		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    		<div class="list-group">
			<a class="list-group-item active" href="GradeController?action=viewcourses&user=<%=session.getAttribute("user")%>">View Courses</a>

			</div>
		</div>
	</div>
	<hr>
	<footer>
        <p>Copyright © Team A</p>
      </footer>	
</div>
</body>
</html>