<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Add Course</title>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
 		<div class="navbar-header">
			<h3> <font color="grey"> MyCourses</font></h3>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="navbar-form navbar-right" >
				  <li><a class="btn btn-success " href="logout.jsp"> Log Out</a></li>
				</ul>		
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/MyCourses/success.jsp">Home</a></li>
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
		
	        <p><c:out value="${message}"/></p> 
	        <fmt:formatDate type="time"  value="${dead.regdeadline}" />
	       
	        <hr>
	        <a type="button" class="btn btn-primary btn-xs pull-right" href="RegistrationController?action=display&user=<%=session.getAttribute("user")%>">back</a>
      		</div>
		</div>
		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    		<div class="list-group">
			<a class="list-group-item active" href="RegistrationController?action=display&user=<%=session.getAttribute("user")%>">Registration</a>
			<a class="list-group-item" href="CourseController?action=checkcourse">Courses</a>
			<a class="list-group-item" href="ScheduleController?action=checkschedule&user=<%=session.getAttribute("user")%>" >Class Schedule</a>			
			<a class="list-group-item" href="UserController?action=view&user=<%=session.getAttribute("user")%>" >Personal Information</a>
			<a class="list-group-item" href=# >Student Account</a>
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