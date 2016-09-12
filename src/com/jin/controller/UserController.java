package com.jin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.dao.UserDao;
import com.jin.dao.DepartmentDao;
import com.jin.dao.ProgramDao;
import com.jin.dao.AccountDao;
import com.jin.model.User;
import com.jin.validator.Validate;
import com.jin.generator.SessionIdentifierGenerator;

public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/edit.jsp";
    private static String LIST_USER = "/listusers.jsp";
    private static String LIST_STUDENTS = "/list_students.jsp";
    private static String STUDENT ="/success.jsp";
    private static String TEACHER ="/teacher.jsp";
    private static String ADMIN ="/admin.jsp";
    private static String ACADEMIC ="/academic.jsp";
    private static String FALSE="/false.jsp";
    private static String UPDATE="/update.jsp";
    private static String EDIT_USER="/edituser.jsp";
    private static String ERROR_MSG="/error.jsp";
    private static String USERS="/user.jsp";
    private static String ACCOUNTING="/accounting.jsp";
    private static String PASSWORD="/password.jsp";
    private static String CONFIRM_ADMIN="/confirm_admin.jsp";
    private static String LOGOUT="/logout.jsp";
    private static String STUDENT_PROFILE="/student_profile.jsp";
   
    
    private UserDao dao;
    private DepartmentDao daod;
    private ProgramDao daop;
    private AccountDao daoa;

    public UserController() {
        super();
        dao = new UserDao();
        daod = new DepartmentDao();
        daop = new ProgramDao();
        daoa = new AccountDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        String name = request.getParameter("user");
        String uname = request.getParameter("name");
        
        if (action.equalsIgnoreCase("delete")){
            dao.deleteUser(uname);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());    
        } else if (action.equalsIgnoreCase("view")){ //student page
            forward = INSERT_OR_EDIT;
            User us = dao.getUser(name);
            request.setAttribute("user",us);
        } else if (action.equalsIgnoreCase("listStudents")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getStudents());
            
        } else if (action.equalsIgnoreCase("view_students")){
            forward = LIST_STUDENTS;
            String deptid = request.getParameter("dept");
            request.setAttribute("users", dao.getStudentsByDept(deptid));
            
        }        
        else if (action.equalsIgnoreCase("listStaff")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getStaff());
            
        }
        else if (action.equalsIgnoreCase("updateUser")){
            forward = EDIT_USER;
            User us = dao.getUser(uname);
            request.setAttribute("user", us);
            request.setAttribute("depts", daod.getDeptId());
            request.setAttribute("programs", daop.getAllProgram());
            
        } else if (action.equalsIgnoreCase("add_user")){
            forward = USERS;           
            request.setAttribute("depts", daod.getDeptId());
            request.setAttribute("programs", daop.getAllProgram());
            
        } else if (action.equalsIgnoreCase("user_password")){
            forward = CONFIRM_ADMIN;           
 
        } else if (action.equalsIgnoreCase("graduate")){
            forward = STUDENT_PROFILE;           
            dao.updateStudentStatus(uname);
            request.setAttribute("student", dao.getUser(uname));
        }else if (action.equalsIgnoreCase("profile")){
            forward = STUDENT_PROFILE;           
            request.setAttribute("student", dao.getUser(name));
        }  
        
        else {
            forward = LOGOUT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
   
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String username = request.getParameter("user");
        String password = request.getParameter("pwd");
        String action = request.getParameter("action");

        String forward="";
        String role="";
        Validate va= new Validate();
       
               
        if (action.equalsIgnoreCase("eml_update")){
        	forward = UPDATE;     
        	String eml= request.getParameter("email");
        	String trimed=eml.trim();
        	
        	
        	if(trimed.equals(dao.getUser(username).getEmail())||va.validator(trimed)
        			&&!(dao.getAllEmail().contains(trimed))){
       		
        		dao.updateEmail(username,trimed);
        		String message="OK";
            	request.setAttribute("msg", message);   
        		
        	}else if(!trimed.equals(dao.getUser(username).getEmail())
        			&&dao.getAllEmail().contains(trimed)){
            	
        		forward = UPDATE; 
            	String message="Invalid1";
            	request.setAttribute("msg", message); 
            }else{
        		
        		String message="Invalid2";
            	request.setAttribute("msg", message);        		
        	}
        }else if(action.equalsIgnoreCase("pass_update")){
        	forward = UPDATE; 
        	String original= request.getParameter("original");
        	String pass1= request.getParameter("pass1");
        	String pass2= request.getParameter("pass2");
        	
        	if(!original.equals(dao.getUser(username).getPassword())){
        		String message="pass_unmatch";
            	request.setAttribute("msg", message); 
            	//request.setAttribute("orig", original); 
        	}else{
        		
	        	if(pass1.equals(pass2)&&va.validatorP(pass1)){
	       		
	        		dao.updatePassword(username,pass1);
	        		String message="Password";
	            	request.setAttribute("msg", message);   
	        		
	        	}else if(!pass1.equals(pass2)){
	        		
	        		String message="Unmatch";
	            	request.setAttribute("msg", message);        		
	        	}else{
	        		
	        		String message="InvalidP";
	            	request.setAttribute("msg", message);
	        		
	        	}
        	}
        	
        }
        else if (action.equalsIgnoreCase("login")){
        	 role=dao.checkUser(username.trim(),password);
        	 if(role.equals("student")){
             	
             	forward=STUDENT;
             	request.getSession().setAttribute("user", username);
        	 }else if (role.equals("teacher")){
             	
             	forward=TEACHER;
             	request.getSession().setAttribute("user", username);
     	
             }else if(role.equals("academic")){
            	 
            	forward=ACADEMIC;
              	request.getSession().setAttribute("user", username);
            	 
             }else if (role.equals("admin")){
             	
             	forward=ADMIN;
             	request.getSession().setAttribute("user", username);
      	
             }else if(role.equals("accounting")){
            	 
            	forward=ACCOUNTING;
              	request.getSession().setAttribute("user", username);
            	 
             }
             else{
             	
             	forward=FALSE;
             	request.getSession().setAttribute("user", username);
             	
             }
        	
        }else if (action.equalsIgnoreCase("addUser")){
            
            User us= new User();
            String name = request.getParameter("uname");
            String rl = request.getParameter("role");
            String id = request.getParameter("id");          
            String dpid = request.getParameter("deptid");
            String pm = request.getParameter("program");
            String yr = request.getParameter("year");
            String semst = request.getParameter("semester");
            String fname = request.getParameter("fname");
            String mname = request.getParameter("mname");
            String lname = request.getParameter("lname");
            String eml = request.getParameter("email");
            
            String trimed_name= name.trim();
 
            if(va.validatorU(trimed_name)&&!(dao.getAllUserName().contains(trimed_name))){
            	//validate user name;
            	us.setUsername(trimed_name); 
	           	     	
            }else if(dao.getAllUserName().contains(trimed_name)){
            	forward=ERROR_MSG;
	          	String message="user name already exist!";
	           	request.setAttribute("str1", message);          	
            }else{
            	
            	forward=ERROR_MSG;
	          	String message="invalid user name";
	           	request.setAttribute("str1", message);                	
            }
            
           
         
            SessionIdentifierGenerator generator= new SessionIdentifierGenerator();
            String pass = generator.passwordGenerator();
            us.setPassword(pass); 
            
            
            String trimed= eml.trim();
            
            if(va.validator(trimed)&&!(dao.getAllEmail().contains(trimed))){
            	//validate email address
            	 us.setEmail(trimed);    
            }else if(dao.getAllEmail().contains(trimed)){
            	
            	forward=ERROR_MSG;
            	String message="email already exists!";
            	request.setAttribute("str3", message); 
            }else{
            	forward=ERROR_MSG;
            	String message="invalid email";
            	request.setAttribute("str3", message);            	
            }
            
           
            if(va.validatorR(rl)){//validate role
            	us.setRole(rl);   
            }else{
	           	forward=ERROR_MSG;
	           	String message="invalid role";
	           	request.setAttribute("str4", message);            	
            }
            
           
            String trimed_id=id.replaceAll("\\s+","");
          
            if("".equals(trimed_id)){ //validate id
            	trimed_id="unknown";
            	us.setId(trimed_id); 
            }else if(va.validatorId(trimed_id)&&!(dao.getAllUserIdExceptUnknown().contains(trimed_id))){
            	 us.setId(trimed_id);   
            	 
            }else if(dao.getAllUserIdExceptUnknown().contains(trimed_id)){
            	forward=ERROR_MSG;
	           	String message="id already exists!";
	           	request.setAttribute("str5", message);            	
            }
            else{
	           	forward=ERROR_MSG;
	           	String message="invalid id";
	           	request.setAttribute("str5", message);            	
            }
            if("".equals(dpid)){ //validate department id
            	dpid="unknown";
            	us.setDeptid(dpid); 
            	
            }else if(daod.getDeptId().contains(dpid)){
            	
            	us.setDeptid(dpid); 
            	
            }else {
            	
            	forward=ERROR_MSG;
	           	String message="invalid department id";
	           	request.setAttribute("str6", message);  
            }
            
            
            if(va.validatorPm(pm)){//validate program name
            	if("".equals(pm)){
                	pm="unknown";
                	us.setProgram(pm);
                	
                }else{  
                	us.setProgram(pm);
                	
                }  
           }else{
	           	forward=ERROR_MSG;
	           	String message="invalid program";
	           	request.setAttribute("str7", message);            	
           }
                    
               us.setYear(yr);
        	   us.setSemester(semst);
        	   us.setStatus("IP");
          
           if(va.validatorN(fname)){//validate first name
        	   us.setFirstname(fname);
           }else{
	           	forward=ERROR_MSG;
	           	String message="invalid first name";
	           	request.setAttribute("str10", message);            	
           }
           
           if(va.validatorN(mname)){//validate middle name
        	   us.setMiddlename(mname);
           }else{
	           	forward=ERROR_MSG;
	           	String message="invalid middle name";
	           	request.setAttribute("str11", message);            	
           }
           
           if(va.validatorN(lname)){//validate last name
        	   us.setLastname(lname);
           }else{
	           	forward=ERROR_MSG;
	           	String message="invalid last name";
	           	request.setAttribute("str12", message);            	
           }

     
           if(va.validatorU(trimed_name)&&!(dao.getAllUserName().contains(trimed_name))	   
        	 &&va.validatorP(pass)&&va.validatorR(rl)
        	 &&(trimed_id.equals("unknown")||(va.validatorId(trimed_id)&&!(dao.getAllUserIdExceptUnknown().contains(trimed_id))))
        	 &&(dpid.equals("unknown")||daod.getDeptId().contains(dpid))&&va.validatorPm(pm)
        	 &&va.validatorY(yr)&&va.validatorN(fname)
        	 &&va.validatorN(mname)&&va.validatorN(lname)
        	 &&va.validator(trimed)&&!(dao.getAllEmail().contains(trimed))){
        	   
        	    if(rl.equals("academic")&&(!trimed_name.equals(dpid)||trimed_name.equals("unknown"))){
                  	forward=ERROR_MSG;
       	           	String message="invalid academic user name";
       	           	request.setAttribute("str4", message);  
                   	
                 }else if(!daop.getProgramNameById(dpid).contains(pm)){
                	 forward=ERROR_MSG;
        	         String message="invalid program";
        	         request.setAttribute("str4", message);  
                 }
        	    else{
                      	
               	forward = LIST_USER;
               	dao.addUser(us);
               	request.setAttribute("users", dao.getAllUsers()); 
                }
            }
            
             
        }else if (action.equalsIgnoreCase("updateUser")){
 
            User us= new User();
            String uname = request.getParameter("name");
            String rl = request.getParameter("role");
            String id = request.getParameter("id");
            String dpid = request.getParameter("deptid");
            String pm = request.getParameter("program");
            String yr = request.getParameter("year");
            String semst = request.getParameter("semester");
            String fname = request.getParameter("fname");
            String mname = request.getParameter("mname");
            String lname = request.getParameter("lname");
            String eml = request.getParameter("email");
             
            
            
            us.setUsername(uname);
            us.setRole(rl);  
            us.setDeptid(dpid);
            us.setProgram(pm);
            us.setYear(yr);
            us.setSemester(semst);
            us.setMiddlename(mname);
            
            String trimed_email= eml.replaceAll("\\s+","");
            if("".equals(trimed_email)){
            	us.setEmail(dao.getUser(uname).getEmail()); 
            }else if(!(dao.getAllUserEmailExceptCurrent(trimed_email).contains(trimed_email))){
            	us.setEmail(trimed_email);
            }else{
            	
            }
           
            String trimed_id=id.replaceAll("\\s+","");
            if("".equals(trimed_id)){ 
            	
            	us.setId(dao.getUser(uname).getId()); 
            }else if(!dao.getAllUserIdExceptCurrent(trimed_id).contains(trimed_id)){
            	 us.setId(trimed_id);   
            	 
            }
            
            String trimed_fname=fname.replaceAll("\\s+","");
            if("".equals(trimed_fname)){ 
            	
            	us.setFirstname(dao.getUser(uname).getFirstname()); 
            }else {
            	 us.setFirstname(trimed_fname);   
            	 
            }
            
            String trimed_lname=lname.replaceAll("\\s+","");
            if("".equals(trimed_lname)){ 
            	
            	us.setLastname(dao.getUser(uname).getLastname()); 
            }else {
            	 us.setLastname(trimed_lname);   
            	 
            }
            
            if(!dao.getAllUserEmailExceptCurrent(trimed_email).contains(trimed_email)
            		&&!dao.getAllUserIdExceptCurrent(trimed_id).contains(trimed_id)){
            	 if(rl.equals("academic")&&!uname.equals(dpid)){
                   	forward=ERROR_MSG;
        	           	String message="invalid academic department";
        	           	request.setAttribute("str4", message);  
                    	
                  }else if(!daop.getProgramNameById(dpid).contains(pm.trim())){
                 	 forward=ERROR_MSG;
         	         String message="invalid program";
         	         request.setAttribute("str4", message);  
                  }
         	    else{
            	
               	forward = LIST_USER;
               	dao.updateUser(us);
               	request.setAttribute("users", dao.getAllUsers());  
         	    }
            }
               
        }else if (action.equalsIgnoreCase("admin_confirm")){
        	 String admin_user = request.getParameter("user");
        	
             String admin_pass = request.getParameter("password");
             
             
             if(dao.checkUser(admin_user, admin_pass).equals("admin")){
            	 forward = PASSWORD;
            	 request.setAttribute("users", dao.getAllUsers()); 
            	// request.getSession().setAttribute("password", admin_pass);
           	 
             }else{
            	 
     	           	forward=ERROR_MSG;
     	           	String message="wrong password";
     	           	request.setAttribute("str12", message);            	
                            	 
             }
        	       	
        	
        }else if (action.equalsIgnoreCase("search")){
        	
        	String keyword = request.getParameter("keyword");
        	role = request.getParameter("role");
            	
            	if(role.equals("student")){
            		
            		forward = LIST_USER;
                    request.setAttribute("users", dao.searchStudent(keyword.trim()));
                    
            	}else if (role.equals("staff")){
            		forward = LIST_USER;
                    request.setAttribute("users", dao.searchStaff(keyword.trim()));
            		
            	}      	
        }else if (action.equalsIgnoreCase("search_students")){
        	
        	String keyword = request.getParameter("keyword");
        	String deptid = request.getParameter("dept");
            	
            	
            		
			forward = LIST_STUDENTS;
	        request.setAttribute("users", dao.searchStudentWithinDept(keyword.trim(),deptid));
                    
            	
        }
        else{
        	forward=TEACHER;
        	
        }
                
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
    } 
}