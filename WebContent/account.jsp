<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="now" class="java.util.Date" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function myFunction() {
    window.open("payment.jsp");
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Account</title>
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
						<li><a  href="/MyCourses/success.jsp">Home</a></li>
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
			<c:forEach items="${year_semester}" var="ys">
				<c:out value="${ys.year}" />
				<c:out value="${ys.semester}" /> <br><br>
				
				<table class="table table-bordered">
					
					<tr>
						<th>Tuition Fees</th>
						<td> $ <c:out value="${fees}" /> </td>	
					</tr>
						<th>Registration Fee</th>
						<td> $ <c:out value="${other_fees.registration}" />  </td>
					</tr>
					<tr>				
						<th>Health Insurance</th>
						<td> $ <c:out value="${other_fees.insurance}" /> </td>
					</tr>
					<tr>
					
				</table>
			</c:forEach>
			
			
			<table class="table table-hover">
			<tr>
				<th>Amount Paid</th>
				<td> $ <c:out value="${account.payment}" /> </td>
			</tr>
			<tr>			
				<th>Balance</th>
				<td> $ <c:out value="${balance}" /> </td>
			</tr>
			</table>
			<c:set var="time1"><fmt:formatDate type="both"  value="${deadline.pdeadline}" /></c:set> 
			<c:set var="time2"><fmt:formatDate type="both"  value="${now}" /></c:set> 
			
		<!-- <c:if test="${time1 > time2 && balance>0}"> Deadline:<fmt:formatDate type="both"  value="${deadline.pdeadline}" /></c:if>
			<c:if test="${time1 < time2 && balance>0}"> Payment Date is expired.</c:if>
			
		 -->	Payment Deadline: <fmt:formatDate type="both"  value="${deadline.pdeadline}" />
			<a type="button" class="btn btn-primary btn-sm pull-right"  onclick="myFunction()">Make Payment</a>	
			
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