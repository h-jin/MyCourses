package com.jin.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.dao.ProgramDao;
import com.jin.dao.UserDao;
import com.jin.dao.RegistrationDao;
import com.jin.model.Program;

public class ProgramController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private ProgramDao dao;
    private UserDao daou;
    private RegistrationDao daor;
    private static String LIST_PROGRAM = "/program_list.jsp";
    private static String ADD_PROGRAM = "/program.jsp";
    private static String ACADEMIC_HOME = "/academic.jsp";
    private static String ERROR_MSG = "/error_msg.jsp";
    private static String DEGREE_AUDIT= "/degree_audit.jsp";
    
    public ProgramController() {
        super();
        dao = new ProgramDao();
        daou = new UserDao();
        daor = new RegistrationDao();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        String deptid = request.getParameter("id");
        String dept_name = request.getParameter("name");
        
        if (action.equalsIgnoreCase("program_list")){
        	
        	forward = LIST_PROGRAM;
        	
        	request.setAttribute("programs", dao.getProgramById(deptid)); 
            request.setAttribute("count", dao.getProgramById(deptid).size()); 
        	
        }else if (action.equalsIgnoreCase("delete")){
        	
        	forward = LIST_PROGRAM;
        	dao.deleteProgramByName(dept_name);
        	request.setAttribute("programs", dao.getProgramById(deptid)); 
            request.setAttribute("count", dao.getProgramById(deptid).size()); 
        	
        
        }else if (action.equalsIgnoreCase("add")){
        	
        	forward = ADD_PROGRAM;
        	
        }else if (action.equalsIgnoreCase("degree_audit")){
        	
        	forward = DEGREE_AUDIT;
        	String student = request.getParameter("user");
        	String program_name = daou.getUser(student).getProgram();
        	request.setAttribute("degree_aduit", dao.getProgramByName(program_name));
        	request.setAttribute("start_year", daou.getUser(student).getYear()); 
        	request.setAttribute("start_semester", daou.getUser(student).getSemester());
        	
        	List <Double> list1= new ArrayList<Double>();
            List <Double> list2= new ArrayList<Double>();
            double grade = 0.0;
            double credit = 0.0;
            double grade_credit =0.0;
            for(int i=0; i< daor.getCoursesWithGrade(student).size(); i++){
            	if(daor.getCoursesWithGrade(student).get(i).getGrade().equals("A+")){
            		grade=4.3; 
            		credit= Double.parseDouble(daor.getCoursesWithGrade(student).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(daor.getCoursesWithGrade(student).get(i).getGrade().equals("A")){
            		grade=4.0; 
            		credit= Double.parseDouble(daor.getCoursesWithGrade(student).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(daor.getCoursesWithGrade(student).get(i).getGrade().equals("A-")){
            		grade=3.7; 
            		credit= Double.parseDouble(daor.getCoursesWithGrade(student).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(daor.getCoursesWithGrade(student).get(i).getGrade().equals("B+")){
            		grade=3.3;
            		credit= Double.parseDouble(daor.getCoursesWithGrade(student).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(daor.getCoursesWithGrade(student).get(i).getGrade().equals("B")){
            		grade=3.0;
            		credit= Double.parseDouble(daor.getCoursesWithGrade(student).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(daor.getCoursesWithGrade(student).get(i).getGrade().equals("B-")){
            		grade=2.7;
            		credit= Double.parseDouble(daor.getCoursesWithGrade(student).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(daor.getCoursesWithGrade(student).get(i).getGrade().equals("C+")){
            		grade=2.3; 
            		credit= Double.parseDouble(daor.getCoursesWithGrade(student).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(daor.getCoursesWithGrade(student).get(i).getGrade().equals("C")){
            		grade=2.0;
            		credit= Double.parseDouble(daor.getCoursesWithGrade(student).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else if(daor.getCoursesWithGrade(student).get(i).getGrade().equals("C-")){
            		grade=1.7;  
            		credit= Double.parseDouble(daor.getCoursesWithGrade(student).get(i).getCredit());
            		grade_credit=grade*credit;
            		list1.add(grade_credit);
            		list2.add(credit);
            	}else{
            		
            		grade=0.0;  
            		credit= Double.parseDouble(daor.getCoursesWithGrade(student).get(i).getCredit());
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
            
            
            request.setAttribute("earned_credit", earned_credit);
            DecimalFormat df = new DecimalFormat("#.00");
            request.setAttribute("GPA", df.format(GPA));


        }
        else {
        	forward = ACADEMIC_HOME;
        	
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String forward ="";
	     String action = request.getParameter("action");
	     String dept_id = request.getParameter("dept_id");
	     String pro_name = request.getParameter("program_name");
	     Program pro= new Program();
	     pro.setDeptid(dept_id);
	     pro.setName(pro_name.trim());
	     String msg="";
	     if (action.equalsIgnoreCase("add_program")){
	 
	    	 if(dao.getAllProgram().contains(pro_name.trim())){
	    		 msg="pro_existed";
	    		 forward = ERROR_MSG;
	    		 request.setAttribute("msg", msg); 
	    		 
	    	 }else{
	    	 forward = LIST_PROGRAM; 
	         dao.addProgram(pro);	
	         request.setAttribute("programs", dao.getProgramById(dept_id)); 
	         request.setAttribute("count", dao.getProgramById(dept_id).size()); 
	         
	    	 }
	    	 
	     }
	     RequestDispatcher view = request.getRequestDispatcher(forward);
	     view.forward(request, response);
    }
    

}
