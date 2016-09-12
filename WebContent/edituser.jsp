<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function validateForm() {
    var y = document.forms["userForm"]["id"].value;
    var m = document.forms["userForm"]["fname"].value;
    var n = document.forms["userForm"]["mname"].value;
    var a = document.forms["userForm"]["lname"].value;
    var e = document.forms["userForm"]["email"].value;
    
    var id = /^([0-9]{4,10})$/; 
    var space = /^([\s]*)$/; 
	var email = /^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,4})$/; 	  
    var name = /^([A-Za-z]+)*$/; 
    
    
    if (!y.match(id)&&!y.match(space)) {
        alert("id must be numberical with length between 4 and 10");
        return false;
    }
    if( !((m.trim()).match(name))&&!m.match(space)){
      	 alert("invalid first name");
           return false;
      	
    }
    if( !((n.trim()).match(name))&&!n.match(space)){
      	 alert("invalid middle name");
           return false;
      	
    }
    if( !((a.trim()).match(name))&&!a.match(space)){
      	 alert("invalid last name");
           return false;
      	
    }
    if( !((e.trim()).match(email))&&!e.match(space)){
   	 alert("invalid email address");
        return false;
   	
   }    
}


</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Edit User</title>
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
					
			<form role="form" id="users" name="userForm" method="post" action="UserController?action=updateUser&name=<c:out value="${user.username}"/>" onsubmit="return validateForm()" >	
					  <p><c:out value="${user.username}"/></p>		  	
				
					  <div class="form-group">
					    <c:set var="role" value="${user.role}"/>  
							<c:if test="${fn:contains(role,'student')}">
						      <select class="btn btn-default btn-sm dropdown-toggle" name="role" form="users">
				    			   <option value="student" selected>Student</option>
								   <option value="teacher">Instructor</option>
								   <option value="academic">Academic Unit</option>
								   <option value="accounting">Accountant</option>
								   
							 </select> 
						 	</c:if> 
						 <c:set var="role" value="${user.role}"/>  
							<c:if test="${fn:contains(role,'teacher')}">
						      <select class="btn btn-default btn-sm dropdown-toggle" name="role" form="users">
				    			   <option value="student" >Student</option>
								   <option value="teacher" selected>Instructor</option>
								   <option value="academic">Academic Unit</option>
								   <option value="accounting">Accountant</option>
								   
							 </select> 
						 </c:if> 
						 <c:set var="role" value="${user.role}"/>  
							<c:if test="${fn:contains(role,'academic')}">
						      <select class="btn btn-default btn-sm dropdown-toggle" name="role" form="users">
				    			   <option value="student" >Student</option>
								   <option value="teacher">Instructor</option>
								   <option value="academic" selected>Academic Unit</option>
								   <option value="accounting">Accountant</option>
								   
							 </select> 
						 	</c:if>
						 	<c:set var="role" value="${user.role}"/>  
						<c:if test="${fn:contains(role,'accounting')}">
					      <select class="btn btn-default btn-sm dropdown-toggle" name="role" form="users">
			    			   <option value="student">Student</option>
							   <option value="teacher">Instructor</option>
							   <option value="academic">Academic Unit</option>
							   <option value="accounting" selected>Accountant</option>
							   
						 </select> 
						 </c:if>  
					  </div>
					  <div class="form-group">
					  
					   ID <input type="text" class="form-control" name="id" placeholder="<c:out value="${user.id}"/>"> 
					  </div>
					  <div class="form-group">
					  
					   	   <c:set var="deptid" value="${user.deptid}"/>  
						   <select class="btn btn-default btn-sm dropdown-toggle" name="deptid" form="users">
							  <c:forEach items="${depts}" var="dept">
							   <c:choose>
							   	<c:when test="${fn:contains(dept,deptid)}">	
							   		<option  value="<c:out value="${dept}" />" selected><c:out value="${dept}" /> </option>
							  	</c:when> 
							  	<c:otherwise>
							  	    <option  value="<c:out value="${dept}" />" ><c:out value="${dept}" /> </option>
							  	
							  	</c:otherwise>
							  	</c:choose>
							  </c:forEach>
				          </select>
					  </div>
					  <div class="form-group">
					    
					     <c:set var="program" value="${user.program}"/>  
						   <select class="btn btn-default btn-sm dropdown-toggle" name="program" form="users">
							  <c:forEach items="${programs}" var="pro">
							  <c:choose>
							   	<c:when test="${fn:contains(program, pro)}">	
							   		<option  value="<c:out value="${pro}" />" selected><c:out value="${pro}" /> </option>
							  	</c:when>
							  	<c:otherwise>
							  		<option  value="<c:out value="${pro}" />" ><c:out value="${pro}" /> </option>
							  	
							  	</c:otherwise> 
							  	</c:choose>
							  </c:forEach>
				          </select>
					  </div>
					  <div class="form-group">
					    
					   Year <input type="text" class="form-control" name="year" placeholder="<c:out value="${user.year}"/>">
					  </div>
					  <div class="form-group">
					    
					   <c:set var="semst" value="${user.semester}"/>  
							<c:if test="${fn:contains(semst,'Winter')}">
						      <select class="btn btn-default btn-sm dropdown-toggle" name="semester" form="users">
			    			   <option value="Winter" selected>Semester Winter</option>
							   <option value="Summer">Semester Summer</option>
							   <option value="Fall">Semester Fall</option>
							    <option value=""></option>
							   
							 </select> 
						   </c:if> 
						   <c:if test="${fn:contains(semst,'Summer')}">
						      <select class="btn btn-default btn-sm dropdown-toggle" name="semester" form="users">
			    			   <option value="Winter">Semester Winter</option>
							   <option value="Summer" selected>Semester Summer</option>
							   <option value="Fall">Semester Fall</option>
							    <option value=""></option>
							   
							 </select> 
						   </c:if> 
						  <c:if test="${fn:contains(semst,'Fall')}">
						      <select class="btn btn-default btn-sm dropdown-toggle" name="semester" form="users">
			    			   <option value="Winter">Semester Winter</option>
							   <option value="Summer">Semester Summer</option>
							   <option value="Fall" selected>Semester Fall</option>
							   <option value=""></option>
							   
							 </select> 
						  </c:if> 
						  <c:if test="${semst==''}">
						      <select class="btn btn-default btn-sm dropdown-toggle" name="semester" form="users">
			    			   <option value="Winter">Semester Winter</option>
							   <option value="Summer">Semester Summer</option>
							   <option value="Fall">Semester Fall</option>
							   <option value="" selected></option>
							   
							 </select> 
						  </c:if> 
						 	
					  </div>
					  <div class="form-group">
					  
					  First Name  <input type="text" class="form-control" name="fname" placeholder="<c:out value="${user.firstname}"/>">
					  </div>
					  <div class="form-group">
					    
					  Middle Name  <input type="text" class="form-control" name="mname" placeholder="<c:out value="${user.middlename}"/>">
					  </div>
					  <div class="form-group">
					   
					  Last Name  <input type="text" class="form-control" name="lname" placeholder="<c:out value="${user.lastname}"/>">
					  </div>
					  <div class="form-group">
					
					  Email  <input type="text" class="form-control" name="email" placeholder="<c:out value="${user.email}"/>">
					  </div>
		
					  <button type="submit" class="btn btn-primary" >update</button>
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