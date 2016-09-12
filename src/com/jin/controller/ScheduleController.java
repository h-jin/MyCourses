package com.jin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.dao.ClassscheduleDao;
import com.jin.dao.CourseDao;
import com.jin.model.Classschedule;

import java.util.*;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class ScheduleController extends HttpServlet{
	 private static final long serialVersionUID = 1L;
	 private ClassscheduleDao dao;
	 private CourseDao daoc;
	 private static String LIST_SCHEDULES_BY_USER = "/schedule.jsp";
	 private static String INSERT_OR_EDIT = "/user.jsp";
	 private static String LIST_SCHEDULES = "/display_schedule.jsp";
	 private static String LIST__LAB_SCHEDULES = "/display_lab_schedule.jsp";
	 private static String UPDATE_SCHEDULE = "/update_schedule.jsp";
	 private static String ADD_SCHEDULE = "/add_schedule.jsp";
	 private static String ADD_LAB = "/add_lab.jsp";
	 private static String ERROR_MSG = "/error.jsp";
	 
	public ScheduleController() {
        super();
        dao = new ClassscheduleDao();
        daoc = new CourseDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        String username = request.getParameter("user");
        String sid = request.getParameter("id");
      
        if (action.equalsIgnoreCase("checkschedule")){
        	forward = LIST_SCHEDULES_BY_USER;
            request.setAttribute("schedule", dao.getAllScheduleByUser(username));           
        } 
        else if (action.equalsIgnoreCase("viewschedule")){
        	forward = LIST_SCHEDULES;
            request.setAttribute("schedules", dao.getAllSchedule()); 
        }else if (action.equalsIgnoreCase("view_lab_schedule")){
        	forward = LIST__LAB_SCHEDULES;
            request.setAttribute("schedules", dao.getAllLabSchedule()); 
        }
        else if (action.equalsIgnoreCase("add")){
        	forward = ADD_SCHEDULE;
            request.setAttribute("ids", daoc.getCoursesId());           
        }else if (action.equalsIgnoreCase("add_lab")){
        	forward = ADD_LAB;
            request.setAttribute("ids", daoc.getCoursesId());           
        }   
        else if (action.equalsIgnoreCase("delete")){
        	forward = LIST_SCHEDULES;
        	dao.deleteSchedule(sid);
        	request.setAttribute("schedules", dao.getAllSchedule());  
            
        }else if (action.equalsIgnoreCase("delete_lab")){
        	forward = LIST__LAB_SCHEDULES;
        	dao.deleteLabSchedule(sid);
        	request.setAttribute("schedules", dao.getAllLabSchedule());  
            
        }
        else if (action.equalsIgnoreCase("update")){
        	forward = UPDATE_SCHEDULE;
            String id = request.getParameter("id");  
            request.setAttribute("schedule", dao.getScheduleById(id)); 
		    request.setAttribute("ids", daoc.getCoursesId());  
		    		    
            
        } 
        else {
            forward = INSERT_OR_EDIT;
        }
   

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    } 
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String forward="";
	     String action = request.getParameter("action");
	     
		if (action.equalsIgnoreCase("addschedule")){
			 
			 	Classschedule cs= new Classschedule();
      
	            int year = Integer.parseInt(request.getParameter("year"));
	            String semester = request.getParameter("semester");
	            String day = request.getParameter("day");
	            String classroom = request.getParameter("classroom");
	            String start = request.getParameter("starthour");
	            String end = request.getParameter("endhour");
	            String instructor = request.getParameter("instructor");
	            String course_id = request.getParameter("courseid");
	       
	            cs.setYear(year);
	            cs.setSemester(semester);
	            cs.setDay(day);
	            cs.setClassroom(classroom);
	            cs.setCourseid(course_id);
	            cs.setInstructor(instructor);
	        
	            
            try{
	            SimpleDateFormat sdf1 = new SimpleDateFormat ("HH:mm");
	            Date t1=sdf1.parse(start);
	            SimpleDateFormat sdf2 = new SimpleDateFormat ("HH:mm");
	            Date t2=sdf2.parse(end);
	            cs.setStarthour(new java.sql.Time(t1.getTime()));
	            cs.setEndhour(new java.sql.Time(t2.getTime()));
	            
	            
	            int s=dao.getSchedule(year, semester, day, classroom).size();
	        	
	        	String msg="";
	           
	            for(int i=0;i<s;i++){
	            	       	
	            		if(!(new java.sql.Time(t1.getTime()).after(dao.getSchedule(year, semester, day, classroom).get(i).getEndhour())||
	            			new java.sql.Time(t2.getTime()).before(dao.getSchedule(year, semester, day, classroom).get(i).getStarthour())))
	            		
	                    	{
	            			msg="Class schedule conflicts!"; 
	            			forward = ERROR_MSG;
	            			request.setAttribute("str14",msg);
	            			
	            		}          		
	            	    	
	            }
	            
	            if(!msg.equals("Class schedule conflicts!")){
	            	
	            	 dao.addSchedule(cs);
 	            
	 	            forward = LIST_SCHEDULES;
	 	            request.setAttribute("schedules", dao.getAllSchedule()); 
	 	          
	            	
	            }      
	           
	            
	            }catch (java.text.ParseException e){
	            	
	            	e.printStackTrace();	            	
	            }
		 
		 }else if (action.equalsIgnoreCase("addlab")){
			 
			 	Classschedule cs= new Classschedule();
	        	
	            int year = Integer.parseInt(request.getParameter("year"));
	            String semester = request.getParameter("semester");
	            String day = request.getParameter("day");
	            String classroom = request.getParameter("classroom");
	            String start = request.getParameter("starthour");
	            String end = request.getParameter("endhour");
	            String instructor = request.getParameter("instructor");
	            String course_id = request.getParameter("courseid");
	       
	            cs.setYear(year);
	            cs.setSemester(semester);
	            cs.setDay(day);
	            cs.setClassroom(classroom);
	            cs.setCourseid(course_id);
	            cs.setInstructor(instructor);
	        
	            
         try{
	            SimpleDateFormat sdf1 = new SimpleDateFormat ("HH:mm");
	            Date t1=sdf1.parse(start);
	            SimpleDateFormat sdf2 = new SimpleDateFormat ("HH:mm");
	            Date t2=sdf2.parse(end);
	            cs.setStarthour(new java.sql.Time(t1.getTime()));
	            cs.setEndhour(new java.sql.Time(t2.getTime()));
	            
	            
	            int s=dao.getLab(year, semester, day, classroom).size();
	        	
	        	String msg="";
	           
	            for(int i=0;i<s;i++){
	            	       	
	            		if(!(new java.sql.Time(t1.getTime()).after(dao.getLab(year, semester, day, classroom).get(i).getEndhour())||
	            			new java.sql.Time(t2.getTime()).before(dao.getLab(year, semester, day, classroom).get(i).getStarthour())))
	            		
	                    	{
	            			msg="Class schedule conflicts!"; 
	            			forward = ERROR_MSG;
	            			request.setAttribute("str14",msg);
	            			
	            		}          		
	            	    	
	            }
	            
	            if(!msg.equals("Class schedule conflicts!")){
	            	
	            	 dao.addLab(cs);
	            
	 	            forward = LIST__LAB_SCHEDULES;
	 	            request.setAttribute("schedules", dao.getAllLabSchedule()); 
	 	          
	            	
	            }      
	           
	            
	            }catch (java.text.ParseException e){
	            	
	            	e.printStackTrace();	            	
	            }
		 
		 }
		 
		 RequestDispatcher view = request.getRequestDispatcher(forward);
	     view.forward(request, response);
	 }
}
