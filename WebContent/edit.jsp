<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function validateEmail() {
	var email = /^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9-]+)*(\.[A-Za-z]{2,4})$/; 	
    var x = document.forms["emailForm"]["email"].value;
    if( !((x.trim()).match(email))){
    	 alert("invalid email address");
         return false;
    	
    }else if( x.length > 45){
    	alert("email must be within 45 length");
        return false;
    	
    }
}

function validatePassword() {
	var password = /^([_A-Za-z0-9-@*#]{6,32})$/;  
    var y = document.forms["passwordForm"]["pass1"].value;
    var z = document.forms["passwordForm"]["pass2"].value;
    if(y!=z){
    	alert("second input of password doesn't match the first one");
    	return false;
    	
    }
    if( !y.match(password)){
   	 alert("invalid password");
     return false;
    }
    
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Edit</title>
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
					<p>Update Email or Password</p>
					
					<form role="form" name="emailForm" method="post" action="UserController?action=eml_update&user=<%=session.getAttribute("user")%>" onsubmit="return validateEmail()">					  		
					  <div class="form-group">
					    <label>Email</label>
					    <input type="text" maxlength='45' class="form-control" name="email" placeholder="<c:out value="${user.email}" />">
					  </div>
					  <div class="form-group">
					  <button type="submit" class="btn btn-primary pull-right" >update</button>
		
					  </div>
					</form>
					<div> <hr> </div>
					<form role="form" name="passwordForm" method="post" action="UserController?action=pass_update&user=<%=session.getAttribute("user")%>" onsubmit="return validatePassword()">					  		
				      <div class="form-group">
					    <label>Old Password</label>
					    <input type="password" maxlength="32" class="form-control" name="original" placeholder="">
					  </div>
					  
					  <div class="form-group">
					    <label>New Password</label>
					    <input type="password" maxlength="32" class="form-control" name="pass1" placeholder="">
					  </div>
					   <div class="form-group">
					    <label>Confirm new password</label>
					    <input type="password" maxlength="32" class="form-control" name="pass2" placeholder="">
					  </div>
					 
					  <button type="submit" class="btn btn-primary pull-right" >update</button>
					 </form>
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