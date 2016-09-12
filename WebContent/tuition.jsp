<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function validateForm() {
    var x = document.forms["amountForm"]["amount"].value;
  
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
<title>Payment</title>
</head>
<body>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
			<h3><font color="grey">Tuition Fee Payment</font></h3>
		</div>
		<div class="navbar-collapse collapse">
          		
					
        </div>	
	  </div>
</div>
<div class="container">
	<div class="row row-offcanvas row-offcanvas-right">
		<div class="col-xs-12 col-sm-12">
			<div class="jumbotron">
			This screen only accepts payments made by Credit Card(Visa).<br>
			Please enter the amount of your payment, e.g.100.00:<br>
			<form role="form" method="post" name="amountForm" action="AccountController?action=pay&account=<%=session.getAttribute("user")%>" onsubmit="return validateForm()" >
				
				      <div class="form-group">
					    
					   Amount: <input type="text" maxlength="8" class="form-control" name="amount" placeholder="0.00">
					  </div>
					  <button type="submit" class="btn btn-primary" >Continue</button>
					</form>
				
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