<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function validateForm() {
	 var number = /^([0-9]{4,10})$/; 
	 var number_s = /^[\s]*$/; 
	 var name = /^([A-Za-z]+)+([A-Za-z\s])*$/; 
	 var name_s = /^[\s]*$/; 
	 var instructor = /^([A-Za-z\s\.])*$/; 
	 var instructor_s = /^[\s]*$/; 
     var x = document.forms["courseForm"]["coursenumber"].value;
     var y = document.forms["courseForm"]["coursename"].value;
     var z = document.forms["courseForm"]["instructor"].value;
   
    if( !x.match(number)&&!x.match(number_s)){
    	 alert("course number must be numerical and 4 length");
         return false;    	
    }   
    if( !y.match(name)&&!y.match(name_s)){
   	 alert("course name must be filled out with alphabets");
     return false;
    }else if( y.length > 45){
    	 alert("length of program name must be within 45 characters");
         return false;   	
    }
    
    if (!z.match(instructor)&&!z.match(instructor_s)) {
        alert("invalid instructor name");
        return false;
    }else if(i.length>45){
   	 alert("instructor name must be within 45 length");
     return false;
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Update Course</title>
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
						<li><a  href="/MyCourses/academic.jsp">Home</a></li>
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
					
		  	<form role="form" name="courseForm" method="post" action="CourseController?action=update&deptid=<c:out value="${course.deptid}"/>&courseid=<c:out value="${course.courseid}"/>" onsubmit="return validateForm()" >
		
				
				 	 <div class="form-group">
					  
					    <input type="text" maxlength="4" class="form-control" name="coursenumber" placeholder="<c:out value="${course.coursenumber}"/>">
					  </div>
					  <div class="form-group">
					    
					    <input type="text" maxlength="45" class="form-control" name="coursename" placeholder="<c:out value="${course.coursename}"/>">
					  </div>
					  
					  <div class="form-group">
					    
					    <input type="text" maxlength="45" class="form-control" name="instructor" placeholder="<c:out value="${course.instructor}"/>">
					  </div>
					  
					  <button type="submit" class="btn btn-primary pull-right" >update</button>
					</form>
				
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    		<div class="list-group">
			<a class="list-group-item active" href="DepartmentController?action=edit_dept&id=<%=session.getAttribute("user")%>">Departments</a>
			<a class="list-group-item" href="CourseController?action=courses_provided&id=<%=session.getAttribute("user")%>">Courses</a>	
			<a class="list-group-item" href="ProgramController?action=program_list&id=<%=session.getAttribute("user")%>">Program</a>
			<a class="list-group-item" href="UserController?action=view_students&dept=<%=session.getAttribute("user")%>">View Student</a>
			<a class="list-group-item" href="CourseController?action=advisor_view_courses">View Courses</a>
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