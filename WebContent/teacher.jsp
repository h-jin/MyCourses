<%@ page language="java" import="java.sql.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyCourses</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">

</head>

<body>
	
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
			<h3><font color="grey">MyCourses</font></h3>
			</div>	
  				<div class="navbar-collapse collapse">
          		<ul class="navbar-form navbar-right" >
						<%
						  	if ((session.getAttribute("user") == null)) {
						%>
						<li><a class="btn btn-success" href="login.jsp">Please Login</a></li>
							
						<%} else {
						%>
						
						<li> <a class="btn btn-success" href='logout.jsp'>Log Out</a> </li>
						<%
						  }
						%>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a  href="/MyCourses/teacher.jsp">Home</a></li>
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
        		<p id="section"> 
        		<%  
        		if ((session.getAttribute("user") == null)){ %>
   					You haven't login!<br>
   					Please try again!<br>
        		<% } else {
        			Date date = new Date();
   					out.print(date.toString());%>	
				<br>
				Welcome <%=session.getAttribute("user")%> ! <br> 
				<% }%>
				
				</p>
        		
        	</div> 	
        </div>
   
		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    		<div class="list-group">
			<a class="list-group-item active" href="GradeController?action=viewcourses&user=<%=session.getAttribute("user")%>">View Courses</a>
		
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