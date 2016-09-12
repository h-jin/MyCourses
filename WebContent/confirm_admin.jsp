<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Administrator Confirm</title>
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
					<p>Before view users' passwords, please confirm as administrator:</p>
				
					<div> <hr> </div>
					<form role="form" method="post" action="UserController?action=admin_confirm&user=<%=session.getAttribute("user")%>" >					  		
				      <div class="form-group">				
					    <input type="password" maxlength="32" class="form-control" name="password" placeholder="password">
					  </div>				
					 
					  <button type="submit" class="btn btn-primary pull-right" >confirm</button>
					 </form>
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