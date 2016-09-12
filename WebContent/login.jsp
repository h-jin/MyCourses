<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>MyCourses</title>

<!--<link href="css/bootstrap.min.css" rel="stylesheet">-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">	
<link href="offcanvas.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
 		<div class="navbar-header">
			<h3> <font color="grey"> MyCourses</font></h3>
		</div>
		<div class="navbar-collapse collapse">
			<form class="navbar-form navbar-right"  role="form" method="post" action="UserController?action=login">
	 			<div class="form-group">
		 			<input type="text" maxlength='10' class="form-control"  name="user" placeholder="Username" >
		 		</div>
				<div class="form-group">
					<input type="password" maxlength='32' class="form-control"  name="pwd" placeholder="Password">
				<div class="form-group"> 
					<input class="btn btn-success" type="submit" value="Login">
				</div>  
				</div>		
			</form>
		</div>
	</div>
</div>

<div class="container">
	<div class="row row-offcanvas row-offcanvas-right">
		<div class="jumbotron">
        <p>Welcome to our website.</p>
        <P>You can manage your courses easily. </P>
        <P>Good place to share courses provided.</P>
        <P></P>
        
      </div>
   
    </div>
    <hr>
	<footer>
        <p>Copyright © Team A</p>
      </footer>
</div>	
</body>
</html>