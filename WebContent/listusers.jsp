<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>

function myFunction() {
	var msg = "Are you sure to delete the user?"; 
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
<title>List Users</title>
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
			<div class="navbar-collapse collapse">
				<form class="navbar-form navbar-right" name="courseForm" role="form" method="post" action="UserController?action=search" onsubmit="return validateForm()">
		 			<div class="form-group">
			 			<input type="text" maxlength="45" class="form-control"  name="keyword" placeholder=""> <br>
			 			<input type="radio" class="form-control" name="role" value="student" checked>student
						<input type="radio" class="form-control" name="role" value="staff">staff
			 		</div>
					
					<div class="form-group"> 
						<input class="btn btn-primary" type="submit" value="search"> 
					</div>  			
				</form>
			<hr>			
		</div>
		<hr>
				
				<table class="table table-hover">
					<thead>
						<tr class="active">
							<th>User Name</th>
							<th>Role</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Year</th>	
							<th>Semester</th>	
							<th>Program</th>
							<th>Status</th>
							<th></th>
							<th></th>
													
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="us">
					   		<tr>
					   			<td > <c:out value="${us.username}" /></td>
					   			<td > <c:out value="${us.role}" /></td>
								<td > <c:out value="${us.firstname}" /></td>
								<td > <c:out value="${us.lastname}" /></td>
								<td > <c:out value="${us.email}" /></td>
								<td > <c:out value="${us.year}" /></td>
								<td > <c:out value="${us.semester}" /></td>
								<td > <c:out value="${us.program}" /></td>
						<!--  		<td > <c:out value="${us.status}" /></td>
						-->	
								<td > <a type="button" class="btn btn-primary btn-xs" href="UserController?action=updateUser&name=<c:out value="${us.username}"/>">update</a></td>							
								<td > <a type="button" id="delet" class="btn btn-primary btn-xs" href="UserController?action=delete&name=<c:out value="${us.username}"/> " onclick="javascript:return myFunction()" >delete</a></td>
							</tr>	
							
						</c:forEach>
					</tbody>
				</table>
			<a type="button" class="btn btn-primary btn-xs" href="UserController?action=add_user">add</a></td>
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