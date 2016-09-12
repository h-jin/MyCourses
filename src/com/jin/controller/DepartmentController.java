package com.jin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.dao.DepartmentDao;
import com.jin.model.Department;
import com.jin.validator.Validate;

public class DepartmentController extends HttpServlet {
	
	 private static final long serialVersionUID = 1L;
     private DepartmentDao dao;
     private static String DISPPLAY_DEPT = "department.jsp";
     private static String EDIT_DEPT = "edit_dept.jsp";
     private static String EDIT_DEPT_INFO = "edit_deptinfo.jsp";
     private static String ERROR_MSG ="/error.jsp";
     private static String USER ="/user.jsp";
     
	 public DepartmentController() {
	        super();
	        dao = new DepartmentDao();	       
	    }
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String forward="";
	     String action = request.getParameter("action");
	     
	     
	     if(action.equalsIgnoreCase("display_dept")){
	    	 
	    	 forward = DISPPLAY_DEPT;
	    	 request.setAttribute("depts", dao.getDeptInfoExceptUnknown());
	    	 
	     }else if (action.equalsIgnoreCase("delete")){
	    	 
	    	 forward = DISPPLAY_DEPT;
	    	 String dpid = request.getParameter("id");
	    	 dao.deleteDept(dpid);
	    	 request.setAttribute("depts", dao.getDeptInfoExceptUnknown());
	    	 
	     }else if (action.equalsIgnoreCase("update")){
	    	 
	    	 forward= EDIT_DEPT;
	    	 String dpid = request.getParameter("id");
	    	 request.setAttribute("dept", dao.getDeptInfoById(dpid));
	    	 	    	 
	     }else if(action.equalsIgnoreCase("edit_dept")){
	    	 
	    	 forward= EDIT_DEPT_INFO;
	    	 String dept_id = request.getParameter("id");
	    	 request.setAttribute("dept", dao.getDeptInfoById(dept_id));
	    	 
	     }else{
	    	 forward= USER;
	    	 request.setAttribute("deptids", dao.getDeptId());
	     }
	     
	     RequestDispatcher view = request.getRequestDispatcher(forward);
	     view.forward(request, response);
	 }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String forward ="";
	     String action = request.getParameter("action");
	     
	     String dept_id = request.getParameter("id");
		 String dept_name = request.getParameter("deptname");
		 String dept_contact = request.getParameter("contact");
		 
		 Department dept = new Department ();
		 Validate va = new Validate();
	 
	     
		 if (action.equalsIgnoreCase("update_dept")){
			 
			 dept.setId(dept_id);
			 
			 String trimed_name=dept_name.replaceAll("\\s+","");
			 String trimed_name2=dept_name.trim();
			 
			 if("".equals(trimed_name)){
					
				 dept.setDeptname(dao.getDeptInfoById(dept_id).getDeptname());				
			} else if(!dao.getDeptNameExceptCurrent(dept_id).contains(trimed_name2)){
				 dept.setDeptname(trimed_name2);
			}else{
				 forward=ERROR_MSG;
		         String message="invalid contact!";
		         request.setAttribute("str3", message);
			 }
				
			 
			
			 if(va.validatorContact(dept_contact)){
				 
				 dept.setContact(dept_contact);
				 
			 }else{
				 forward=ERROR_MSG;
		         String message="invalid contact!";
		         request.setAttribute("str3", message);
			 }
			 
			 
			 if(!dao.getDeptNameExceptCurrent(dept_id).contains(trimed_name)
				 &&va.validatorContact(dept_contact)
					 ){
				 
				 dao.updateDept(dept);
				 forward = DISPPLAY_DEPT;
		    	 request.setAttribute("depts", dao.getDeptInfoExceptUnknown());
				 
			 }
		 
			 
			 
		 }else if (action.equalsIgnoreCase("update_dept_contact")){
			 
			 
			 if(va.validatorContact(dept_contact)){
				 forward=EDIT_DEPT_INFO;
				 dept.setContact(dept_contact);
				 dao.updateDeptContact(dept_id, dept_contact);
				 request.setAttribute("dept", dao.getDeptInfoById(dept_id));
				 
			 }else{
				 forward=ERROR_MSG;
		         String message="invalid contact!";
		         request.setAttribute("str3", message);
			 }
			 
		 
		 }else if (action.equalsIgnoreCase("add_dept")){
			 
			 if(va.validatorDpid(dept_id)&&!(dao.getDeptId().contains(dept_id))){
				 //validate dept id
				 dept.setId(dept_id);
				 
			 }else if(dao.getDeptId().contains(dept_id)){
				 
				 forward=ERROR_MSG;
		         String message="department id already exists!";
		         request.setAttribute("str3", message);
				 
			 }else{
				forward=ERROR_MSG;
	         	String message="invalid department id!";
	         	request.setAttribute("str3", message);
			 }
			 
			 String trimed_name=dept_name.trim();
			 if(va.validatorDN(trimed_name)&&!dao.getDeptName().contains(trimed_name)){ 
				 //validate dept name

				 dept.setDeptname(trimed_name);
				 
			 }else if(dao.getDeptName().contains(trimed_name)){
				 
				 forward=ERROR_MSG;
		         String message="department name already exists!";
		         request.setAttribute("str13", message);
				 
			 }
			 else{
				 forward=ERROR_MSG;
		         String message="invalid department name!";
		         request.setAttribute("str4", message);
			 }
			 
			 if(va.validatorContact(dept_contact)){
				 
				 dept.setContact(dept_contact);
				 
			 }else{
				 forward=ERROR_MSG;
		         String message="invalid contact!";
		         request.setAttribute("str3", message);
			 }
			 
			 
			 if(va.validatorDpid(dept_id)&&!(dao.getDeptId().contains(dept_id))
				&&va.validatorDN(trimed_name)&&!dao.getDeptName().contains(trimed_name)
				&&va.validatorContact(dept_contact)){
				 
				 dao.addDept(dept);
				 forward = DISPPLAY_DEPT;
		    	 request.setAttribute("depts", dao.getDeptInfoExceptUnknown());
				 
			 }
			 
		 }
		 
		 		 
		 RequestDispatcher view = request.getRequestDispatcher(forward);
	     view.forward(request, response);
	 }

}
