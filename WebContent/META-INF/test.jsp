<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Course Deleted</title>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
 		<div class="navbar-header">
			<h3> <font color="grey"> MyCourses</font></h3>
		</div>
		<div class="navbar-collapse collapse">
			<form class="navbar-form navbar-right" >
				<div class="form-group"> 
					<a class="btn btn-success" href="login.jsp"> Try Again</a>
			    </div>
			</form>
		</div>  
	</div>		
</div>
<div class="container">
	<div class="row row-offcanvas row-offcanvas-right">
		<div class="jumbotron">
        <p>Course has been deleted!</p> 
        <a type="button" class="btn btn-primary btn-xs" href="RegistrationController?action=display&user=<%=session.getAttribute("user")%>">back</a>
        
      </div>
    </div>
    <hr>
	<footer>
        <p>Copyright © Team A</p>
      </footer>
</div>	

</body>
</html>