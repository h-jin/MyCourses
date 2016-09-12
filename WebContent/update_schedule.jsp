<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function validateForm() {
    var x = document.forms["scheduleForm"]["year"].value;
    var i = document.forms["scheduleForm"]["classroom"].value;
    var m = document.forms["scheduleForm"]["starthour"].value;
    var n = document.forms["scheduleForm"]["endhour"].value;
    var year = /^([2]{1,1})+([0-9]{3,3})$/; 

    var space = /^([\s]*)$/;  
    var starthour= /^([0-9]{2,2})+([:]{1,1})+([0-9]{2,2})$/;
    var endhour= /^([0-9]{2,2})+([:]{1,1})+([0-9]{2,2})$/;
    var classroom = /^([A-Z]{1,1})+([0-9]{3,3})$/; 
    if (!x.match(year)&&!x.match(space)) {
        alert("year must be numerica,start with 2 and between 4 length");
        return false;
    }
    
    if (!i.match(classroom)&&!i.match(space)) {
        alert("classroom must be in form like \"H411\"");
        return false;
    }
    
    var times= new Array();
    times=m.split(":");
    
    var s_hour = parseInt(times[0]);
    var s_min = parseInt(times[1]);
   
    if (!m.match(starthour)&&!m.match(space)) {
        alert("starthour must be in form hh:mm");
        return false;
    }else if(!(s_hour>0&&s_hour<24&&s_min>0&&s_min<60)&&!m.match(space)){
    	alert("invalid starthour ");
        return false;   	
    }
     times=n.split(":");
     e_hour = parseInt(times[0]);
     e_min = parseInt(times[1]);
    if (!n.match(endhour)&&!n.match(space)) {
        alert("endhour must be in form hh:mm");
        return false;
    }else if(!(e_hour>0&&e_hour<24&&e_min>0&&e_min<60)&&!n.match(space)){
    	alert("invalid endhour ");
        return false;    	
    }
    if(s_hour>e_hour){
    	alert("starthour must be before endhour! ");
        return false;	  
    	
    }
} 
/*function myFunction() {
	var x = document.forms["scheduleForm"]["year"].value;
    var year = /^([2]{1,1})+([0-9]{3,3})$/; 

    var space = /^([\s]*)$/;  
   
    if (!x.match(year)&&!x.match(space)) {
    	var notice = "invalid year";
        document.getElementById("demo").innerHTML = notice;
    }	
	
}*/
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>update schedule</title>
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
					
			<form role="form" method="post" id="schedule" name="scheduleForm" action="ScheduleController?action=updateschedule&id=<c:out value="${schedule.id}"/>&original_courseid=<c:out value="${schedule.courseid}"/>" onsubmit="return validateForm()">	
					 		  	
					  <div class="form-group">
					    
					    <input type="text" class="form-control" name="year" placeholder="<c:out value="${schedule.year}"/>">
					    <p id="demo"></p>
					  </div>
					  <div class="form-group">
					  
				<!-- 	    <input type="text" class="form-control" name="semester" placeholder="<c:out value="${schedule.semester}"/>">
				-->	  
				
						<c:set var="semst" value="${schedule.semester}"/>  
							<c:if test="${fn:contains(semst,'1')}">
						      <select class="btn btn-default btn-sm dropdown-toggle" name="semester" form="schedule">
			    			   <option value="1" selected>Semester 1</option>
							   <option value="2">Semester 2</option>
							   <option value="3">Semester 3</option>
							
							   
							 </select> 
						   </c:if> 
						   <c:if test="${fn:contains(semst,'2')}">
						      <select class="btn btn-default btn-sm dropdown-toggle" name="semester" form="schedule">
			    			   <option value="1">Semester 1</option>
							   <option value="2" selected>Semester 2</option>
							   <option value="3">Semester 3</option>
							 
							   
							 </select> 
						   </c:if> 
						  <c:if test="${fn:contains(semst,'3')}">
						      <select class="btn btn-default btn-sm dropdown-toggle" name="semester" form="schedule">
			    			   <option value="1">Semester 1</option>
							   <option value="2">Semester 2</option>
							   <option value="3" selected>Semester 3</option>
							
							   
							 </select> 
						  </c:if> 
						  
					  </div>
					  <div class="form-group">
					  
					<!--     <input type="text" class="form-control" name="day" placeholder="<c:out value="${schedule.day}"/>">-->
				 	
				  	<c:set var="day" value="${schedule.day}"/>  
					<c:if test="${fn:contains(day,'Monday')}">
					<select class="btn btn-default btn-sm dropdown-toggle" name="day" form="schedule">
			    			   <option value="Monday" selected>Monday</option>
							   <option value="Tuesday">Tuesday</option>
							   <option value="Wednesday">Wednesday</option>
							   <option value="Thursday">Thursday</option>
							   <option value="Friday">Friday</option>
							   <option value="Saturday">Saturday</option>
					</select> 
					</c:if> 
					<c:if test="${fn:contains(day,'Tuesday')}">
					<select class="btn btn-default btn-sm dropdown-toggle" name="day" form="schedule">
			    			   <option value="Monday" >Monday</option>
							   <option value="Tuesday" selected>Tuesday</option>
							   <option value="Wednesday">Wednesday</option>
							   <option value="Thursday">Thursday</option>
							   <option value="Friday">Friday</option>
							   <option value="Saturday">Saturday</option>
					</select> 
					</c:if> 
					<c:if test="${fn:contains(day,'Wednesday')}">
					<select class="btn btn-default btn-sm dropdown-toggle" name="day" form="schedule">
			    			   <option value="Monday" >Monday</option>
							   <option value="Tuesday" >Tuesday</option>
							   <option value="Wednesday" selected>Wednesday</option>
							   <option value="Thursday">Thursday</option>
							   <option value="Friday">Friday</option>
							   <option value="Saturday">Saturday</option>
					</select> 
					</c:if> 
					<c:if test="${fn:contains(day,'Thursday')}">
					<select class="btn btn-default btn-sm dropdown-toggle"  name="day" form="schedule">
			    			   <option value="Monday" >Monday</option>
							   <option value="Tuesday" >Tuesday</option>
							   <option value="Wednesday" >Wednesday</option>
							   <option value="Thursday" selected>Thursday</option>
							   <option value="Friday">Friday</option>
							   <option value="Saturday">Saturday</option>
					</select> 
					</c:if> 
					<c:if test="${fn:contains(day,'Friday')}">
					<select class="btn btn-default btn-sm dropdown-toggle" name="day" form="schedule">
			    			   <option value="Monday" >Monday</option>
							   <option value="Tuesday" >Tuesday</option>
							   <option value="Wednesday" >Wednesday</option>
							   <option value="Thursday" >Thursday</option>
							   <option value="Friday" selected>Friday</option>
							   <option value="Saturday">Saturday</option>
					</select> 
					</c:if> 
					<c:if test="${fn:contains(day,'Saturday')}">
					<select class="btn btn-default btn-sm dropdown-toggle" name="day" form="schedule">
			    			   <option value="Monday" >Monday</option>
							   <option value="Tuesday" >Tuesday</option>
							   <option value="Wednesday" >Wednesday</option>
							   <option value="Thursday" >Thursday</option>
							   <option value="Friday" >Friday</option>
							   <option value="Saturday" selected>Saturday</option>
					</select> 
					</c:if> 
					  </div>
					  <div class="form-group">
					    
					    <input type="text" class="form-control" name="classroom" placeholder="<c:out value="${schedule.classroom}"/>">
					  </div>
					  <div class="form-group">
					    
					    <input type="text" class="form-control" name="starthour" placeholder="<fmt:formatDate type="time" value="${schedule.starthour}"/>">
					  </div>
					  <div class="form-group">
					    
					    <input type="text" class="form-control" name="endhour" placeholder="<fmt:formatDate type="time"  value="${schedule.endhour}"/>">
					  </div>
					   <div class="form-group">
					    
					    <select class="btn btn-default btn-sm dropdown-toggle" name="course_id" form="schedule">
						  <c:forEach items="${ids}" var="id">
						   		
						   		<option  value="<c:out value="${id}" />" ><c:out value="${id}" /> </option>
						  </c:forEach>
						
			         	 </select>
					  </div>
					  <div class="form-group">
					    
					    <input type="radio" class="form-control" name="course_lab" value="course" checked> course schedule<br>
					    <input type="radio" class="form-control" name="course_lab" value="lab">lab schedule<br>
					  </div>
		
					  <button type="submit" class="btn btn-primary" >update</button>
					</form>
			
			</div>
		</div>
		<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    		<div class="list-group">
			<a class="list-group-item active" href="UserController?action=listUser">Users</a>
			<a class="list-group-item" href="DepartmentController?action=display_dept">Departments</a>
			<a class="list-group-item" href="ScheduleController?action=viewschedule">Class Schedule</a>	
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