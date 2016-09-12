<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>

function myFunction() {
	var msg = "Are you sure to delete the schedule?"; 
	if (confirm(msg)==true){ 
		return true; 
	}else{ 
		return false; 
	} 
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Display Schedule</title>
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
						<li><a  href="/MyCourses/admin.jsp">Home</a></li>
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
				<table class="table table-hover">
					<thead>
						<tr class="active">
							<th>ID</th>
							<th>Year</th>
							<th>Semester</th>
							<th>Day</th>
							<th>Classroom</th>
							<th>Start Hour</th>
							<th>End Hour</th>
							<th>Course ID</th>
							<th>Instructor</th>
							<th></th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${schedules}" var="ss">
					   		<tr>
					   			<td > <c:out value="${ss.id}" /></td>
								<td > <c:out value="${ss.year}" /></td>
								<td > <c:out value="${ss.semester}" /></td>
								<td > <c:out value="${ss.day}" /></td>
								<td > <c:out value="${ss.classroom}" /></td>
								<td > <fmt:formatDate type="time"  value="${ss.starthour}" /></td>
								<td > <fmt:formatDate type="time"  value="${ss.endhour}" /></td>
								<td > <c:out value="${ss.courseid}" /></td>
								<td > <c:out value="${ss.instructor}" /></td>
							<!--  	<td > <a type="button" class="btn btn-primary btn-xs" href="ScheduleController?action=update&id=<c:out value="${ss.id}"/>">update</a></td>	
							-->						
								<td > <a type="button" class="btn btn-primary btn-xs" href="ScheduleController?action=delete&id=<c:out value="${ss.id}"/> " onclick="javascript:return myFunction()">delete</a></td>				
							</tr>	
							
						</c:forEach>
						
					</tbody>
				</table>
			
				<a type="button" class="btn btn-primary btn-xs" href="ScheduleController?action=add">add</a></td>
		</div>
	</div>
		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    		<div class="list-group">
			<a class="list-group-item active" href="UserController?action=listStudents">Students</a>
			<a class="list-group-item " href="UserController?action=listStaff">Staff</a>			
			<a class="list-group-item" href="DepartmentController?action=display_dept">Departments</a>
			<a class="list-group-item" href="ScheduleController?action=viewschedule">Class Schedule</a>
			<a class="list-group-item" href="ScheduleController?action=view_lab_schedule">Lab Schedule</a>			
			<a class="list-group-item " href="UserController?action=user_password">Passwords</a>	
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