<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList"%>
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
<title>registration</title>
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
						<table class="table table-hover">
						<thead>
							<tr class="active">
								<th>Department</th>
								<th>Program</th>
								<th>Degree</th>
								<th>Start </th>
								<th>Semester</th>
								<th>Normal Length</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
								
							   	<tr>	
									<td><c:out value="${degree_aduit.deptid}" /> </td>
									<td><c:out value="${degree_aduit.name}" /> </td>
									<td><c:out value="${degree_aduit.degree}" /> </td>
									<td><c:out value="${start_year}" /> </td>
									<td><c:out value="${start_semester}" /> </td>
									<td><c:out value="${degree_aduit.length}" /> semester(s) <td>
								<tr>
									
							
						</tbody>	
						</table> 
						<table class="table table-hover">
							<tr class="active">
								<td>Requirement 1</td>
								<td>Minimum Credit Required</td>
								<fmt:parseNumber var="credit_required" type="number" value="${degree_aduit.creditrequired}" />								
								<td><c:out value="${credit_required}" /> </td>
								<td>Credit Earned</td>
								<td><c:out value="${earned_credit}" /></td>
							</tr>	
							<tr>
								<td>Requirement 2</td>
								<th>Minimum GPA Required</th>
								<td>3.0 </td>
								<td>Cumulative GPA</td>
								<td><c:out value="${GPA}" /></td>
							</tr>
						
						</table>
					
					<c:choose>
						 
						<c:when test="${credit_required<=earned_credit && GPA >=3.0}">
						
					   	<a type="button" class="btn btn-primary btn-xs" href="UserController?action=graduate&name=<%=session.getAttribute("user")%>" >Graduate Application</a>
						</c:when>
						<c:otherwise>	
						<a type="button" disabled class="btn btn-primary btn-xs" href="UserController?action=graduate&name=<%=session.getAttribute("user")%>" >Graduate Application</a>
													
						</c:otherwise>
					</c:choose> 	
			<hr>
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