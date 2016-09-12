<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

			<table class="table table-hover">
				<thead>
					<tr class="active">
						<th>Student Name</th>
						<th>Grade</th>
						<th></th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${students}" var="ss">
				   		<tr>
							<td > <c:out value="${ss.firstname}" /></td>
							<td > <c:out value="${ss.lastname}" /></td>
							<td > <c:out value="${ss.id}" /></td>
							<td > <c:out value="${ss.grade}" /></td>
							<td >
					<!--  <form class="navbar-form navbar-right" role="form" method="post" action="RegistrationController?act=updategrade&student=<c:out value="${ss.username}" />" >	-->				  		
								  <div class="form-group">
								   
								    <input type="text" class="form-control" name="grade" placeholder="<c:out value="${ss.grade}" />">
								  </div>
								  <div class="form-group">
								  <button type="submit" class="btn btn-primary pull-right" >update</button>				
								  </div>
							</form>
						   </td>
						</tr>	
						
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    		<div class="list-group">
			<a class="list-group-item active" href="RegistrationController?action=viewstudents&user=<%=session.getAttribute("user")%>">View Student</a>
			<a class="list-group-item" href=#>View Courses</a>
			<a class="list-group-item" href="/MyCourses/updategrade.jsp">Update Grade</a>
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