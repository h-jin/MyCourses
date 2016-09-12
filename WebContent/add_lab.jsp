<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

function validateForm() {
  
    var i = document.forms["scheduleForm"]["classroom"].value;
    var m = document.forms["scheduleForm"]["starthour"].value;
    var n = document.forms["scheduleForm"]["endhour"].value;
    var a = document.forms["scheduleForm"]["instructor"].value;
   
    var starthour= /^([0-9]{2,2})+([:]{1,1})+([0-9]{2,2})$/;
    var endhour= /^([0-9]{2,2})+([:]{1,1})+([0-9]{2,2})$/;
    var classroom = /^([A-Z]{1,1})+([0-9]{3,3})$/; 
    var name = /^([A-Za-z]+)+([A-Za-z\s\.])*$/;
    
    if( !((a.trim()).match(name))){
     	 alert("invalid instructor name");
         return false;    	
   	}
    if (!i.match(classroom)) {
        alert("classroom must be in form like \"H411\"");
        return false;
    }
    
    var times= new Array();
    times=m.split(":");
    
    var s_hour = parseInt(times[0]);
    var s_min = parseInt(times[1]);
   
    if (!m.match(starthour)) {
        alert("starthour must be in form hh:mm");
        return false;
    }else if(!(s_hour>0&&s_hour<24&&s_min>0&&s_min<60)){
    	alert("invalid starthour ");
        return false;
    	
    }
     times=n.split(":");
     var e_hour = parseInt(times[0]);
     var e_min = parseInt(times[1]);
	    if (!n.match(endhour)) {
	        alert("endhour must be in form hh:mm");
	        return false;
	    }else if(!(e_hour>0&&e_hour<24&&e_min>0&&e_min<60)){
	    	alert("invalid endhour ");
	        return false;	    	
	    }
	    if(s_hour>e_hour){
	    	alert("starthour must be before endhour! ");
	        return false;	  
	    	
	    }
}

window.onload=function(){ 
	  var myDate=new Date(); 
	  var startYear=myDate.getFullYear();
	  var endYear=myDate.getFullYear()+1;
	  var obj=document.getElementById('selectedyear'); 
	  for(var i=startYear;i<=endYear;i++){ 
	    obj.options.add(new Option(i,i)); 
	  } 
	} 

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link href="offcanvas.css" rel="stylesheet" type="text/css">
<title>Add Schedule</title>
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
					
				<form role="form"  name="scheduleForm" id="schedule" method="post" action="ScheduleController?action=addlab"  onsubmit="return validateForm()">	
					 		  	
					  <div class="form-group">
			
						<select class="btn btn-default btn-sm dropdown-toggle" id="selectedyear" name="year" form="schedule">
					   </select>
					  </div>
					  <div class="form-group">
				
						<select class="btn btn-default btn-sm dropdown-toggle" name="semester" form="schedule">
			    			   <option value="Winter"> Winter</option>
							   <option value="Summer"> Summer</option>
							   <option value="Fall"> Fall</option>
							  
						</select> 
					  </div>
					  <div class="form-group">
					  
			    		<select class="btn btn-default btn-sm dropdown-toggle" name="day" form="schedule">
			    			   <option value="Monday">Monday</option>
							   <option value="Tuesday">Tuesday</option>
							   <option value="Wednesday">Wednesday</option>
							   <option value="Thursday">Thursday</option>
							   <option value="Friday">Friday</option>
							   <option value="Saturday">Saturday</option>
						</select> 
					  </div>
					  <div class="form-group">
					    
					    <input type="text" class="form-control" name="classroom" placeholder="classroom">
					  </div>
					  <div class="form-group">
					    
					    <input type="text" class="form-control" name="starthour" placeholder="starthour">
					  </div>
					  <div class="form-group">
					    
					    <input type="text" class="form-control" name="endhour" placeholder="endhour">
					  </div>
					   <div class="form-group">
					    
					    <input type="text" class="form-control" name="instructor" placeholder="instructor">
					  </div>
					   <div class="form-group">
					    
					    <select class="btn btn-default btn-sm dropdown-toggle" name="courseid" form="schedule">
			    			   <c:forEach items="${ids}" var="id">
			    			   <option value=<c:out value="${id}" />><c:out value="${id}" /></option>
							  </c:forEach> 
						</select> 
					    
					  </div>
		
					  <button type="submit" class="btn btn-primary" >add</button>
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