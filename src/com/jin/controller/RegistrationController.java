package com.jin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.dao.ClassscheduleDao;
import com.jin.dao.DeadlinesDao;
import com.jin.dao.RegistrationDao;
import com.jin.dao.GradeDao;
import com.jin.dao.UserDao;
import com.jin.dao.CourseDao;
import com.jin.dao.AccountDao;
import com.jin.dao.ProgramDao;
import com.jin.model.YearSemester;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RegistrationDao dao;
    private DeadlinesDao daod;
    private ClassscheduleDao daoc;
    private GradeDao daog;
    private UserDao daou;
    private CourseDao daocs;
    private AccountDao daoa;
    private ProgramDao daop;
    private static String LIST_COURSES = "/registration.jsp";
    private static String LIST_COURSES_DETAILS = "/details.jsp";
    private static String DELETE_COURSE = "/delete.jsp";
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_STUDENTS = "/studentlist.jsp";
    private static String EDIT_GRADE = "/updategrade.jsp";
    private static String STUDENT_RECORD = "/student_record.jsp";
    private static String ERROR_MSG = "/update.jsp";
    private static String LIST_COURSES_BY_STUDENT = "/advisor_drop.jsp";
    private static String ADVISOR_REGISTRATION = "/advisor_registration.jsp";
    private static String ADVISOR_ADD_SUCCESS = "/advisor_add_success.jsp";
    private static String ERROR = "/error_msg.jsp";
    private static String ACADEMIC_ERROR = "/academic_error.jsp";
    
    public RegistrationController() {
        super();
        dao = new RegistrationDao();
        daod = new DeadlinesDao();
        daoc = new ClassscheduleDao();
        daog = new GradeDao();
        daou = new UserDao();
        daocs = new CourseDao();
        daoa = new AccountDao();
        daop = new ProgramDao();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        String username = request.getParameter("user");
        String courseid = request.getParameter("id");
        String uname = request.getParameter("name");
      
        if (action.equalsIgnoreCase("display")){
        	
            List <Double> list1= new ArrayList<Double>();
            List <Double> list2= new ArrayList<Double>();
            double grade = 0.0;
            double credit = 0.0;
            double grade_credit =0.0;
            for(int i=0; i< dao.getCoursesWithGrade(username).size(); i++){
            	if(dao.getCoursesWithGrade(username).get(i).getGrade().equals("A+")){
            		grade=4.3; 
            		credit= Double.parseDouble(dao.getCoursesWithGrade(username).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(dao.getCoursesWithGrade(username).get(i).getGrade().equals("A")){
            		grade=4.0; 
            		credit= Double.parseDouble(dao.getCoursesWithGrade(username).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(dao.getCoursesWithGrade(username).get(i).getGrade().equals("A-")){
            		grade=3.7; 
            		credit= Double.parseDouble(dao.getCoursesWithGrade(username).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(dao.getCoursesWithGrade(username).get(i).getGrade().equals("B+")){
            		grade=3.3;
            		credit= Double.parseDouble(dao.getCoursesWithGrade(username).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(dao.getCoursesWithGrade(username).get(i).getGrade().equals("B")){
            		grade=3.0;
            		credit= Double.parseDouble(dao.getCoursesWithGrade(username).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(dao.getCoursesWithGrade(username).get(i).getGrade().equals("B-")){
            		grade=2.7;
            		credit= Double.parseDouble(dao.getCoursesWithGrade(username).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(dao.getCoursesWithGrade(username).get(i).getGrade().equals("C+")){
            		grade=2.3; 
            		credit= Double.parseDouble(dao.getCoursesWithGrade(username).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(dao.getCoursesWithGrade(username).get(i).getGrade().equals("C")){
            		grade=2.0;
            		credit= Double.parseDouble(dao.getCoursesWithGrade(username).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(dao.getCoursesWithGrade(username).get(i).getGrade().equals("C-")){
            		grade=1.7;  
            		credit= Double.parseDouble(dao.getCoursesWithGrade(username).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else{
            		
            		grade=0.0;  
            		credit= Double.parseDouble(dao.getCoursesWithGrade(username).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}
            }
            
            double earned_credit = 0.0;
            for(int i=0; i<list2.size(); i++){
            	earned_credit += list2.get(i);            	
            }
            double earned_grade = 0.0;
            for(int i=0; i<list1.size(); i++){
            	earned_grade += list1.get(i);            	
            }
            double GPA=0.0;
            if(earned_credit!=0.0){
            	
            	GPA = earned_grade/earned_credit;
            }
            
            if(dao.getYearSemesterByInstructor(username).size()==0){
           		forward = ERROR_MSG;  
           		request.setAttribute("msg", "No_Record");        		 
           	 }else{
                 forward = STUDENT_RECORD;        		            

           	 }
            
            request.setAttribute("courses", dao.getCourses(username));
            request.setAttribute("year_semester", dao.getYearSemesterByInstructor(username));
            DecimalFormat df = new DecimalFormat("#.00");
            request.setAttribute("GPA",  df.format(GPA));
        } 
        else if (action.equalsIgnoreCase("details")){
        	forward = LIST_COURSES_DETAILS;
            int year = Integer.parseInt(request.getParameter("year"));
            String semester = request.getParameter("semester");
            request.setAttribute("course", daoc.getCScheduleByCourseId(courseid, year, semester));
        	request.setAttribute("lab", daoc.getLScheduleByCourseId(courseid, year, semester));  
            request.setAttribute("deadline", daod.getDeadlines(semester, year)); 
                 
        } else if (action.equalsIgnoreCase("advisor_add")){
        	
            String year = request.getParameter("year");
            int year1 = Integer.parseInt(request.getParameter("year"));
            String semester = request.getParameter("semester");
            String student = request.getParameter("student");
            String instructor = request.getParameter("instructor");
            String course_id = request.getParameter("id");
            String msg="noconflict";
            String msg1="clear";
            String msg2="noregistered";
            int program_length = daop.getProgramByName(daou.getUser(student).getProgram()).getLength();
            Double fees_per_term = daoa.getFeesById(student)/program_length;
            
            if(dao.getYearSemesterByInstructor(student).size()>1){
            	Double fees= fees_per_term*(dao.getYearSemesterByInstructor(student).size()-1);
            	Double payment= daoa.getPaymentById(student);
            	if(payment<fees){
            		forward = ACADEMIC_ERROR;
            		msg1="noclear";
   	      		 	request.setAttribute("msg", "payment" ); 
            	}
            }
            if(dao.getCoursesId(student).contains(course_id.trim())){
            	forward = ACADEMIC_ERROR;
            	 msg2="registered";
	      		request.setAttribute("msg", "registered" ); 
            }
            
       	 
            
            for(int i=0; i<daoc.getAllScheduleBySemester(year, semester, student).size();i++){
            	if(daoc.getAllScheduleBySemester(year, semester, student).get(i).getDay().equals(daoc.getCScheduleByCourseId(course_id, year1, semester).getDay())){
    	    			 if(!(daoc.getCScheduleByCourseId(course_id, year1, semester).getStarthour().after(daoc.getAllScheduleBySemester(year, semester, student).get(i).getEndhour())
    	    				||daoc.getCScheduleByCourseId(course_id, year1, semester).getEndhour().before(daoc.getAllScheduleBySemester(year, semester, student).get(i).getStarthour()))){
    		        			msg="schedule_conflicts";  
    		        			request.setAttribute("message",msg );
    	    			 }
        		 
            		 }
            		if(daoc.getAllScheduleBySemester(year, semester, student).get(i).getDay().equals(daoc.getCScheduleByCourseId(course_id, year1, semester).getDay())){
            			 if(!(daoc.getLScheduleByCourseId(course_id, year1, semester).getStarthour().after(daoc.getAllScheduleBySemester(year, semester, student).get(i).getEndhour())
      	    	              ||daoc.getLScheduleByCourseId(course_id, year1, semester).getEndhour().before(daoc.getAllScheduleBySemester(year, semester, student).get(i).getStarthour()))){
      		        			msg="schedule_conflicts";  
      		        			request.setAttribute("message",msg );
      	    	        }
            		 }  		
            	 }
            	 
            	 for(int i=0; i<daoc.getAllLabScheduleBySemester(year, semester, student).size();i++){
            		 if(daoc.getAllLabScheduleBySemester(year, semester, student).get(i).getDay().equals(daoc.getCScheduleByCourseId(course_id, year1, semester).getDay())){
            			 
    	    			 if(!(daoc.getCScheduleByCourseId(course_id, year1, semester).getStarthour().after(daoc.getAllLabScheduleBySemester(year, semester, student).get(i).getEndhour())
    	    				||daoc.getCScheduleByCourseId(course_id, year1, semester).getEndhour().before(daoc.getAllLabScheduleBySemester(year, semester, student).get(i).getStarthour()))){
    		        			msg="schedule_conflicts";  
    		        			request.setAttribute("message",msg );
    	    			 }
        		 
            		 }
            		 
            		 if(daoc.getAllLabScheduleBySemester(year, semester, student).get(i).getDay().equals(daoc.getCScheduleByCourseId(course_id, year1, semester).getDay())){
        				 
    					 if(!(daoc.getLScheduleByCourseId(course_id, year1, semester).getStarthour().after(daoc.getAllLabScheduleBySemester(year, semester, student).get(i).getEndhour())
    	    	              ||daoc.getLScheduleByCourseId(course_id, year1, semester).getEndhour().before(daoc.getAllLabScheduleBySemester(year, semester, student).get(i).getStarthour()))){
    		        			msg="schedule_conflicts";  
    		        			request.setAttribute("message",msg );
    	    	        }
        				 
        			 }
            	 }
            	 
            	 
            	 if(msg.equals("schedule_conflicts")){
                  	
            		 forward = ACADEMIC_ERROR;
            		 request.setAttribute("msg",msg );
                 }
            	 
            if(msg.equalsIgnoreCase("noconflict")&&msg1.equalsIgnoreCase("clear")&&msg2.equalsIgnoreCase("noregistered")){
       		 	
            	forward = ADVISOR_ADD_SUCCESS;
	            dao.addCourse(student.trim(), course_id.trim(), year, semester, instructor); 
	        	request.setAttribute("course", daocs.getCoursesByCourseId(course_id)); 
	            request.setAttribute("student", daou.getUser(student)); 
	            request.setAttribute("year", year); 
	            request.setAttribute("semester", semester);   
	       		
       	 	}
        }
        else if (action.equalsIgnoreCase("add")){
        	
        	 String year = request.getParameter("year");
        	 int year1= Integer.parseInt(year);
        	 String semester = request.getParameter("semester");
        	 String instructor = request.getParameter("instructor");
        	 String msg="noconflict";
        	 String msg1="noregistered";
        	 String msg2="clear";
        	 
        	 for(int i=0; i<daoc.getAllScheduleBySemester(year, semester, uname).size();i++){
        	//	 if(daoc.getAllScheduleBySemester(year, semester, uname).contains(daoc.getCScheduleByCourseId(courseid, year1, semester))){
        		if(daoc.getAllScheduleBySemester(year, semester, uname).get(i).getDay().equals(daoc.getCScheduleByCourseId(courseid, year1, semester).getDay())){
	    			 if(!(daoc.getCScheduleByCourseId(courseid, year1, semester).getStarthour().after(daoc.getAllScheduleBySemester(year, semester, uname).get(i).getEndhour())
	    				||daoc.getCScheduleByCourseId(courseid, year1, semester).getEndhour().before(daoc.getAllScheduleBySemester(year, semester, uname).get(i).getStarthour()))){
		        			msg="schedule_conflicts";  
		        			request.setAttribute("message",msg );
	    			 }
    		 
        		 }
        	//	 if(daoc.getAllLabScheduleBySemester(year, semester, uname).contains(daoc.getLScheduleByCourseId(courseid, year1, semester))){
        			 if(daoc.getAllScheduleBySemester(year, semester, uname).get(i).getDay().equals(daoc.getCScheduleByCourseId(courseid, year1, semester).getDay())){
        			 if(!(daoc.getLScheduleByCourseId(courseid, year1, semester).getStarthour().after(daoc.getAllScheduleBySemester(year, semester, uname).get(i).getEndhour())
  	    	              ||daoc.getLScheduleByCourseId(courseid, year1, semester).getEndhour().before(daoc.getAllScheduleBySemester(year, semester, uname).get(i).getStarthour()))){
  		        			msg="schedule_conflicts";  
  		        			request.setAttribute("message",msg );
  	    	        }
        		 }  		
        	 }
        	 
        	 for(int i=0; i<daoc.getAllLabScheduleBySemester(year, semester, uname).size();i++){
        		 if(daoc.getAllLabScheduleBySemester(year, semester, uname).get(i).getDay().equals(daoc.getCScheduleByCourseId(courseid, year1, semester).getDay())){
        		 //if(daoc.getAllScheduleBySemester(year, semester, uname).contains(daoc.getCScheduleByCourseId(courseid, year1, semester))){
        			 
	    			 if(!(daoc.getCScheduleByCourseId(courseid, year1, semester).getStarthour().after(daoc.getAllLabScheduleBySemester(year, semester, uname).get(i).getEndhour())
	    				||daoc.getCScheduleByCourseId(courseid, year1, semester).getEndhour().before(daoc.getAllLabScheduleBySemester(year, semester, uname).get(i).getStarthour()))){
		        			msg="schedule_conflicts";  
		        			request.setAttribute("message",msg );
	    			 }
    		 
        		 }
        		 
        		 if(daoc.getAllLabScheduleBySemester(year, semester, uname).get(i).getDay().equals(daoc.getCScheduleByCourseId(courseid, year1, semester).getDay())){
        		// if(daoc.getAllLabScheduleBySemester(year, semester, uname).contains(daoc.getLScheduleByCourseId(courseid, year1, semester))){
    				 
					 if(!(daoc.getLScheduleByCourseId(courseid, year1, semester).getStarthour().after(daoc.getAllLabScheduleBySemester(year, semester, uname).get(i).getEndhour())
	    	              ||daoc.getLScheduleByCourseId(courseid, year1, semester).getEndhour().before(daoc.getAllLabScheduleBySemester(year, semester, uname).get(i).getStarthour()))){
		        			msg="schedule_conflicts";  
		        			request.setAttribute("message",msg );
	    	        }
    				 
    			 }
        	 }
        	 
        	 if(dao.getCoursesId(uname).contains(courseid.trim())){
        		 
        		 forward = ERROR_MSG;
        		 msg1="registered";
        		 request.setAttribute("msg", msg1 );    
        	 }
        	 
        	 if(msg.equals("schedule_conflicts")){
              	
        		 forward = ERROR_MSG;
        		 request.setAttribute("msg",msg );
             }
        	 
        	 int program_length = daop.getProgramByName(daou.getUser(uname).getProgram()).getLength();
             Double fees_per_term = daoa.getFeesById(uname)/program_length;
             
             if(dao.getYearSemesterByInstructor(uname).size()>1){
             	Double fees= fees_per_term*(dao.getYearSemesterByInstructor(uname).size()-1);
             	Double payment= daoa.getPaymentById(uname);
             	if(payment<fees){
             		forward = ERROR_MSG;
             		msg2="noclear";
    	      		request.setAttribute("msg", "Please make payment first!" ); 
             	}
             }
             
        	 if(msg1.equals("noregistered")&&msg.equals("noconflict")
        	    &&msg2.equals("clear")){
        		 
        		 dao.addCourse(uname.trim(), courseid.trim(), year, semester, instructor); 
            	 forward = LIST_COURSES;
            	 
        	 }
        	 
        	 /*if(msg1.equals("noregistered")&&msg.equals("noconflict")){
        		 dao.addCourse(uname.trim(), courseid.trim(), year, semester, instructor); 
             	 forward = LIST_COURSES;
        		 
        	 }*/
             	
            
        	 List<YearSemester> list = new ArrayList<YearSemester>();
        	 
        	 for(int i=0; i<dao.getYearSemesterByInstructor(uname).size(); i++){
        		 int yr = Integer.parseInt(dao.getYearSemesterByInstructor(uname).get(i).getYear());
        		 String semst = dao.getYearSemesterByInstructor(uname).get(i).getSemester();
        		 
        		 if(daod.getDeadlines(semst,yr).getRegdeadline()!=null){
        			 if(daod.getDeadlines(semst,yr).getRegdeadline().after(new java.util.Date())){
            			 list.add(dao.getYearSemesterByInstructor(uname).get(i));
            		 }
        		 }
        		 
        	 }
        	
        	 request.setAttribute("year_semester", list); 
        	 request.setAttribute("courses", dao.getCourses(uname)); 
             
        } else if (action.equalsIgnoreCase("display_course")){
        	
         String user = request.getParameter("user");	     
       	 List<YearSemester> list = new ArrayList<YearSemester>();
       	 
       	 for(int i=0; i<dao.getYearSemesterByInstructor(user).size(); i++){
       		 int yr = Integer.parseInt(dao.getYearSemesterByInstructor(user).get(i).getYear());
       		 String semst = dao.getYearSemesterByInstructor(user).get(i).getSemester();
       		 
       		 if(daod.getDeadlines(semst,yr).getDpdeadline()!=null){
       			 if(daod.getDeadlines(semst,yr).getDpdeadline().after(new java.util.Date())){
           			 list.add(dao.getYearSemesterByInstructor(user).get(i));
           		 }
       		 }
       		 
       	 }
       	
       	 if(dao.getYearSemesterByInstructor(user).size()==0){
       		forward = ERROR_MSG;  
       		request.setAttribute("msg", "No_Record");        		 
       	 }else {
           	 forward = LIST_COURSES;      	 

       	 }
       	 request.setAttribute("year_semester", list); 
       	 request.setAttribute("courses", dao.getCourses(user)); 
            
       } 
        else if (action.equalsIgnoreCase("delete")){
        	forward = DELETE_COURSE;
        	dao.deleteCourse(uname,courseid);
    //        request.setAttribute("courses", dao.getAllCourses(username)); 
           
        }else if (action.equalsIgnoreCase("advisor_drop")){
        	forward = LIST_COURSES_BY_STUDENT;
        	String student = request.getParameter("student");
            request.setAttribute("courses", dao.getCoursesWithoutGradeNotDisc(student)); 
       //     request.setAttribute("courses", dao.getCourses(student)); 

            request.setAttribute("year_semester", dao.getYearSemesterByInstructor(student));
            request.setAttribute("student", student);
           
        } else if (action.equalsIgnoreCase("advisor_delete")){
        	forward = LIST_COURSES_BY_STUDENT;
        	String student = request.getParameter("student");
        	String year = request.getParameter("year");
        	int year1 = Integer.parseInt(year);
        	String semester = request.getParameter("semester");
        	courseid = request.getParameter("courseid");
       	   
	    	if(daod.getDeadlines(semester, year1).getDpdeadline().before(new java.util.Date())){
	    		dao.updateStatus(student,courseid);
	    	}else{
	    		dao.deleteCourse(student,courseid);
	    	}      	
            request.setAttribute("courses", dao.getCoursesWithoutGradeNotDisc(student)); 
        	request.setAttribute("year_semester", dao.getYearSemesterByInstructor(student));
            request.setAttribute("student", student); 
           
        }else if (action.equalsIgnoreCase("advisor_register")){
        	forward = ADVISOR_REGISTRATION;
        	String student = request.getParameter("student");
        	
            List<YearSemester> list = new ArrayList<YearSemester>();
          	 
          	 for(int i=0; i<daocs.getYearSemester().size(); i++){
          		 int yr = Integer.parseInt(daocs.getYearSemester().get(i).getYear());
          		 String semst = daocs.getYearSemester().get(i).getSemester();
          		 
          		 if(daod.getDeadlines(semst,yr).getRegdeadline()!=null){
          			 if(daod.getDeadlines(semst,yr).getRegdeadline().after(new java.util.Date())){
              			 list.add(daocs.getYearSemester().get(i));
              		 }
          		 }
          		 
          	 }         	
          	 request.setAttribute("year_semester", list); 
	         request.setAttribute("courses", daoc.getAllSchedule()); 
	         request.setAttribute("student", student);
           
        } 
        else if (action.equalsIgnoreCase("viewstudents")){
        	forward = LIST_STUDENTS;
           // request.setAttribute("students", dao.viewStudents(username)); 
       // 	request.setAttribute("students", daog.getGradeByInstructor(request.getParameter("user"))); 
           
        }
        else {
            forward = INSERT_OR_EDIT;
        }
   

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    } 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String studentname = request.getParameter("student");
        String gd = request.getParameter("grade");
        String action = request.getParameter("action");
        String forward="";
        
        if (action.equalsIgnoreCase("updategrade")){
        	forward = EDIT_GRADE;
             dao.updateGrade(studentname, gd); 
           
        } else{
        	forward = LIST_STUDENTS;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
        
}






