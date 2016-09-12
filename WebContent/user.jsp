<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function validateForm() {
    var x = document.forms["userForm"]["uname"].value;
    var y = document.forms["userForm"]["id"].value;
    var m = document.forms["userForm"]["fname"].value;
    var n = document.forms["userForm"]["mname"].value;
    var a = document.forms["userForm"]["lname"].value;
    var e = document.forms["userForm"]["email"].value;
    
    var user = /^([A-Za-z0-9\_]{4,10})$/; 
    var id = /^([0-9]{4,10})$/; 
    var space = /^([\s]*)$/; 
	var email = /^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,4})$/; 	  
    var name = /^([A-Za-z]+)+([A-Za-z\s\.])*$/; 
    
    if (!x.trim().match(user)) {
        alert("user name must be composed of apphbet and numbers with length between 4 and 10");
        return false;
    }
    if (!y.match(id)&&!y.match(space)) {
        alert("id must be numberical with length between 4 and 10");
        return false;
    }
    if( !((m.trim()).match(name))){
      	 alert("invalid first name");
           return false;
      	
    }
    if( !((n.trim()).match(name))&&!n.match(space)){
      	 alert("invalid middle name");
           return false;
      	
    }
    if( !((a.trim()).match(name))){
      	 alert("invalid last name");
           return false;
      	
    }
    if( !((e.trim()).match(email))){
   	 alert("invalid email address");
        return false;
   	
   }    
}

 window.onload=function(){ 
	  var myDate=new Date(); 
	  var startYear=myDate.getFullYear()-10;
	  var endYear=myDate.getFullYear()+2;
	  var obj=document.getElementById('selectedyear'); 
	  for(var i=startYear;i<=endYear;i++){ 
	    obj.options.add(new Option(i,i)); 
	  } 
	} 

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>User</title>
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
			<!--  	<c:forEach items="${deptids}" var="deptid">
					    <input type="radio" class="form-control" name="deptid" value="<c:out value="${deptid.id}" />" >
					    </c:forEach>	-->
				<form role="form" id="users" name="userForm" method="post" action="UserController?action=addUser" onsubmit="return validateForm()" >	
					  <div class="form-group">
		
					    <input type="text" maxlength="10" class="form-control" name="uname" placeholder="User Name">
					  </div>				  	
					
					  <div class="form-group">
					  
					    <select class="btn btn-default btn-sm dropdown-toggle" name="role" form="users">
			    			   <option value="student">Student</option>
							   <option value="teacher">Instructor</option>
							   <option value="academic">Academic Unit</option>
							   <option value="accounting">Accountant</option>
							   
						</select> 
					  </div>
					  <div class="form-group">
					  
					    <input type="text" maxlength="10" class="form-control" name="id" placeholder="ID">
					  </div>
					  <div class="form-group" >
					  <select class="btn btn-default btn-sm dropdown-toggle" name="deptid" form="users">
						  <c:forEach items="${depts}" var="dept">
						   		
						   		<option  value="<c:out value="${dept}" />" ><c:out value="${dept}" /> </option>
						  </c:forEach>
			          </select>
				  	</div>
					  <div class="form-group">
					    
					    <select class="btn btn-default btn-sm dropdown-toggle" name="program" form="users">
						  <c:forEach items="${programs}" var="pro">
						   		
						   		<option  value="<c:out value="${pro}" />" ><c:out value="${pro}" /> </option>
						  </c:forEach>
			         	 </select>
					  </div>
					  <div class="form-group">
						 <select class="btn btn-default btn-sm dropdown-toggle" id="selectedyear" name="year" form="users">
					   </select>
					  </div>
					  <div class="form-group">
					    					 
					   <select class="btn btn-default btn-sm dropdown-toggle" name="semester" form="users">
			    			   <option value="Winter">Semester Winter</option>
							   <option value="Summer">Semester Summer</option>
							   <option value="Fall">Semester Fall</option>
							   <option value=""></option>
						</select> 
					 
					  </div>
					  <div class="form-group">
					  
					    <input type="text" maxlength="45" class="form-control" name="fname" placeholder="First Name">
					  </div>
					  <div class="form-group">
					    
					    <input type="text" maxlength="45" class="form-control" name="mname" placeholder="Middle Name">
					  </div>
					  <div class="form-group">
					   
					    <input type="text" maxlength="45"  class="form-control" name="lname" placeholder="Last Name">
					  </div>
					  <div class="form-group">
					
					    <input type="text" maxlength="32" class="form-control" name="email" placeholder="Email">
					  </div>
		
					  <button type="submit" class="btn btn-primary pull-right" >add</button>
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