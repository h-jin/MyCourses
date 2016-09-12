<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function validateCard(x) {
	 var y = document.getElementById(x).value;
     var number = /^([0-9]{16,16})$/; 
    
    if (!y.match(number)) {
        y="invalid card number";      
        document.getElementById(x).value=y;
    }
   
   
}
function validateCode(x) {
	 var y = document.getElementById(x).value;
     var code = /^([0-9]{3,3})$/; 
    
    if (!y.match(code)) {
    	 y="invalid code";      
         document.getElementById(x).value=y;
    }
   
}
window.onload=function(){ 
	  var myDate=new Date(); 
	  var startYear=myDate.getFullYear();
	  var endYear=myDate.getFullYear()+3;
	  var obj=document.getElementById('selectedyear'); 
	  for(var i=startYear;i<=endYear;i++){ 
	    obj.options.add(new Option(i,i)); 
	  } 
	  var startMonth=myDate.getMonth()-myDate.getMonth()+1;
	  var endMonth=myDate.getMonth()+12-myDate.getMonth();	
	  var obj=document.getElementById('selectedmonth'); 
	  for(var j=startMonth;j<=endMonth;j++){ 
	    obj.options.add(new Option(j,j)); 
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
			<h3><font color="grey">Payment</font></h3>
		</div>
		<div class="navbar-collapse collapse">
          		
        </div>	
	  </div>
</div>
<div class="container">
	<div class="row row-offcanvas row-offcanvas-right">
		<div class="col-xs-12 col-sm-12">
			<div class="jumbotron">
			Payment Details
			<hr>
			Please enter a credit card and total amount will be calculated.<br>
			Transaction amount: $<c:out value="${paid}" /><br>
			Please complete the following details exactly as they appear on your credit card.<br>
			Do not input space and hyphens in the credit number.
				<form role="form" method="post" name="visaForm" action="AccountController?action=process&account=<%=session.getAttribute("user")%>&amount=<c:out value="${paid}" /> " onsubmit="return validateForm()" >
				
					<div class="form-group">
					  
					CardNumber: <input type="text" class="form-control" name="CardNumber" id="card" maxlength="16" onChange="validateCard(this.id)"> 
					</div>
					  
				 	 <div class="form-group">
					  
					  Expiry Date: 
					  <select class="btn btn-default btn-sm dropdown-toggle" id="selectedmonth" name="month" form="visaForm">
					    </select> /
					  	<select class="btn btn-default btn-sm dropdown-toggle" id="selectedyear" name="year" form="visaForm">
					    </select>
					  </div>
					  <div class="form-group">
					    
					   Security Code: <input type="text" class="form-control" name="SecurityCode" id="code" maxlength="3" onChange="validateCode(this.id)">
					  </div>
					  <button type="submit" class="btn btn-success pull-left" >Process Transaction</button>
					  
					</form>
					
					<a type="button" class="btn btn-danger pull-right" href="AccountController?action=cancel">Cancel Transaction</a>
					
				
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