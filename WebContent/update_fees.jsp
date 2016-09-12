<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function validateForm() {
	var x = document.forms["feeForm"]["pro_fees"].value;
	  
    var double_number = /^([0-9]{1,4})+([\.]{1,1})+([0-9]{2,2})$/; 
    
    if (!x.match(double_number)) {
        alert("invalid amount");
        return false;
    }
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Update Fees</title>
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
						<li><a  href="/MyCourses/accounting.jsp">Home</a></li>
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
					
					<form role="form" name="feeForm" method="post" action="AccountController?action=update_fees&name=<c:out value="${pro.name}" />" onsubmit="return validateForm()">	
			
					 	 <div class="form-group">
						  
						    <input type="text" maxlength="45" class="form-control" name="pro_fees" placeholder="<c:out value="${pro.fees}" />">
						  </div>
						 
					  <button type="submit" class="btn btn-primary pull-right" >update</button>
					</form>
					
				
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    		<div class="list-group">
			<a class="list-group-item active" href="AccountController?action=pro_fees" >Program Fees</a>			
			<a class="list-group-item" href="AccountController?action=account_users" >Student Account</a>
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