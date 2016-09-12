package com.jin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jin.dao.GradeDao;


public class GradeController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
   
    private GradeDao dao;
   
    private static String GRADE = "/grade.jsp";
    private static String COURSES_INSTRUCTED = "/course_instructed.jsp";
    private static String GRADE_SUCCESS= "/grade_success.jsp";
    
    public GradeController() {
        super();     
        dao = new GradeDao();
       
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        String username = request.getParameter("user");
        String courseid = request.getParameter("course_id");
        String student = request.getParameter("student");
        String grade = request.getParameter("grade");
        String course = request.getParameter("course_name");
        String year = request.getParameter("year");
        String semester = request.getParameter("semester");
        
        if (action.equalsIgnoreCase("viewcourses")){
        	forward = COURSES_INSTRUCTED;
      	
        	request.setAttribute("year_semester", dao.getYearSemesterByInstructor(username));
        	request.setAttribute("courses", dao.getCoursesByInstructor(username.trim()));
        	
           
        }else if (action.equalsIgnoreCase("grading")){
        	forward = GRADE;
        	request.setAttribute("courses", dao.getGradeByCourse(username,year,semester,course)); 
        	request.setAttribute("course_name", course); 
        	request.setAttribute("course_id", courseid); 
        	request.setAttribute("year", year); 
        	request.setAttribute("semester", semester); 
        	
           
        }else if (action.equalsIgnoreCase("addGrade")){
        	forward = GRADE;
        	dao.updateGrade(student, courseid, grade);
        	
           
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String forward="";
        String action = request.getParameter("action");
        String [] students =  request.getParameterValues("id");
        String [] grades =  request.getParameterValues("grade");
        String course_id = request.getParameter("courseid");
        String course_name = request.getParameter("coursename");
        String year = request.getParameter("year");
        String semester = request.getParameter("semester");
        String instructor = request.getParameter("user");

        
        
        if (action.equalsIgnoreCase("addGrade")){
        	//forward = GRADE;
        	forward = GRADE_SUCCESS;
            for(int i=0; i<students.length;i++){
            	
            	dao.updateGrade(students[i], course_id, grades[i]);
            }   
            request.setAttribute("courses", dao.getGradeByCourse(instructor,year,semester,course_name));
            request.setAttribute("course_name", course_name); 
        	request.setAttribute("course_id", course_id); 
        	request.setAttribute("year", year); 
        	request.setAttribute("semester", semester); 
     }else if (action.equalsIgnoreCase("search_students")){
     	forward = GRADE;
     	instructor = request.getParameter("instructor");
     	String courseid = request.getParameter("courseid");
     	String student = request.getParameter("keyword");
     	request.setAttribute("courses", dao.searchStudent(instructor, year, semester, student, courseid));
     	request.setAttribute("instructor", instructor); 
    	request.setAttribute("courseid", courseid); 
    	request.setAttribute("year", year); 
    	request.setAttribute("semester", semester);
     	
        
     }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
