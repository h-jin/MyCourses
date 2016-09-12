<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
 		<div class="navbar-header">
			<div class="navbar-header">			
				<h3> <font color="grey"> MyCourses</font></h3>
			</div>			
		</div>
		<div class="navbar-collapse collapse">	
				  <ul class="navbar-form navbar-right" >
				  <li><a class="btn btn-success " href="logout.jsp"> Log Out</a></li>
				  </ul>		
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/MyCourses/academic.jsp">Home</a></li>
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
        		<p id="section">       		
        		<c:set var="str" value="${msg}"/>     		        		
 				<c:if test="${fn:contains(str,'id_existed')}">Course id already existed!</c:if><br>   				
				<c:if test="${fn:contains(str,'number_existed')}">Course number already existed!</c:if><br>   				
				<c:if test="${fn:contains(str,'name_existed')}">Course name already existed!</c:if><br>   				
				<c:if test="${fn:contains(str,'invalid_id')}">Invalid Course ID! <br>
				First character of course id should be the same as the first character of your department ID!</c:if><br>   				
				<c:if test="${fn:contains(str,'pro_existed')}">Program already existed!</c:if><br>   				
				<c:if test="${fn:contains(str,'coursename_existed')}">Course name already existed!</c:if><br>   				
				<c:if test="${fn:contains(str,'coursenumber_existed')}">Course number already existed!</c:if><br>   				
				<c:if test="${fn:contains(str,'invalid_number')}">Invalid Course number!</c:if><br>   				
				<c:if test="${fn:contains(str,'invalid')}">Invalid Course name or number!</c:if><br>
				<c:if test="${fn:contains(str,'registered')}">Course has been registered!</c:if><br> 
				<c:if test="${fn:contains(str,'payment')}">Please make payment first!</c:if><br>   				
				
				</p>
        		
        	</div> 	
        </div>
   
		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    		<div class="list-group">
			<a class="list-group-item active" href="DepartmentController?action=edit_dept&id=<%=session.getAttribute("user")%>">Departments</a>
			<a class="list-group-item" href="CourseController?action=courses_provided&id=<%=session.getAttribute("user")%>">Courses</a>	
			<a class="list-group-item" href="ProgramController?action=program_list&id=<%=session.getAttribute("user")%>">Program</a>
			<a class="list-group-item" href="UserController?action=view_students&dept=<%=session.getAttribute("user")%>">View Student</a>
			<a class="list-group-item" href="CourseController?action=advisor_view_courses">View Courses</a>
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

