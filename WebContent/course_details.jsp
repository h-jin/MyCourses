<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Course Details</title>
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
			<p> 
				 
				<hr>	
				<table class="table table-hover">
				<thead>
					<tr class="active">
						<th>Department</th>
						<th>Course Number</th>
						<th>Course Name</th>
						<th>Instructor</th>
						<th>Class Location</th>
						<th>Day</th>
						<th>Start Hour</th>
						<th>End hour</th>
						
						
					</tr>
				</thead>
				<tbody>
				   		<tr>
				   			
				   			<td > <c:out value="${course.deptid}" /></td>
							<td > <c:out value="${course.coursenumber}" /></td>
							<td > <c:out value="${course.coursename}" /></td>
							<td > <c:out value="${course.instructor}" /></td>
							<td > <c:out value="${course.classroom}" /><hr><hr>
								  <c:out value="${lab.classroom}" />
							</td>
							<td > <c:out value="${course.day}" /> <hr><hr>
								  <c:out value="${lab.day}" />
							</td>
							<td > <fmt:formatDate type="time"  value="${course.starthour}" /><hr>
								<fmt:formatDate type="time"  value="${lab.starthour}" />
							</td>
							<td > <fmt:formatDate type="time"  value="${course.endhour}" /><hr>
							<fmt:formatDate type="time"  value="${lab.endhour}" />
							</td>
							
						</tr>	
				</tbody>
			</table>
			<hr>
		<!-- <a type="button" class="btn btn-primary btn-xs pull-right" href="RegistrationController?action=display&user=<%=session.getAttribute("user")%>">back</a>  -->
			<c:set var="time1"><fmt:formatDate value="${deadline.regdeadline}" pattern="yyyy-MM-dd HH:mm:ss"/></c:set> 
			<c:set var="time2"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/></c:set> 
		    <c:if test="${time1 > time2 }">  <a type="button" class="btn btn-primary btn-xs" href="RegistrationController?action=add&id=<c:out value="${course.courseid}"/> &name=<%=session.getAttribute("user")%>&instructor=<c:out value="${course.instructor}"/>&year=<c:out value="${course.year}"/>&semester=<c:out value="${course.semester}"/>">add </a>			
			</c:if>
			
			</p>
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    		<div class="list-group">
			<a class="list-group-item active" href="UserController?action=profile&user=<%=session.getAttribute("user")%>" >Student Profile</a>
			<a class="list-group-item" href="RegistrationController?action=display_course&user=<%=session.getAttribute("user")%>">Registration</a>
			<a class="list-group-item" href="CourseController?action=checkcourse">Courses</a>
			<a class="list-group-item" href="ScheduleController?action=checkschedule&user=<%=session.getAttribute("user")%>" >Class Schedule</a>
			<a class="list-group-item" href="RegistrationController?action=display&user=<%=session.getAttribute("user")%>">Student Record</a>			
			<a class="list-group-item" href="UserController?action=view&user=<%=session.getAttribute("user")%>" >Personal Information</a>
			<a class="list-group-item" href="AccountController?action=view_account&user=<%=session.getAttribute("user")%>" >Student Account</a>
			<a class="list-group-item" href="ProgramController?action=degree_audit&user=<%=session.getAttribute("user")%>" >Degree Audit</a>
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