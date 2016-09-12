package com.jin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;

import com.jin.dao.AccountDao;
import com.jin.dao.DeadlinesDao;
import com.jin.dao.ProgramDao;
import com.jin.dao.UserDao;
import com.jin.dao.RegistrationDao;
import com.jin.model.Deadlines;



public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountDao dao;
    private DeadlinesDao dao1;
    private ProgramDao dao2;
    private UserDao dao3;
    private RegistrationDao daoreg;
    private static String ACCOUNT = "/account.jsp";
    private static String PAYMENT = "/payment.jsp";
    private static String TUITION = "/tuition.jsp";
    private static String VISA = "/visa.jsp";
    private static String SUMMARY = "/payment_summary.jsp";
    private static String CANCEL = "/cancel.jsp";
    private static String PROGRAM_LIST = "/pro_list.jsp";
    private static String UPDAET_FEES = "/update_fees.jsp";
    private static String STUDENT_ACCOUNTS = "/student_account.jsp";
    
    public AccountController() {
        super();
        dao = new AccountDao(); 
        dao1 = new DeadlinesDao(); 
        dao2 = new ProgramDao(); 
        dao3 = new UserDao();
        daoreg = new RegistrationDao();
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        String id = request.getParameter("user");
        
        if (action.equalsIgnoreCase("view_account")){
        	
        	List<String> accts = new ArrayList<String>();
        	accts=dao.getAccountId();
        	if(!accts.contains(id)){
				dao.insertAccountUser(id);
				dao.setNewAccount(id);
			}
        	
        	int program_length = dao2.getProgramByName(dao3.getUser(id).getProgram()).getLength();
        	
        	forward = ACCOUNT;
        	dao.updateAccountByUser(id);
        	DecimalFormat df = new DecimalFormat("#.00");
        	
        	Double tuition =  dao.getFeesById(id)/program_length- dao.getOtherFees().getRegistration()
        			- dao.getOtherFees().getInsurance();
        	
            request.setAttribute("account", dao.getAccountById(id)); 
            request.setAttribute("fees", df.format(tuition)); 
            request.setAttribute("balance", dao.getBalanceById(id)); 
            request.setAttribute("year_semester", daoreg.getYearSemesterByInstructor(id)); 
            request.setAttribute("other_fees", dao.getOtherFees()); 
            if(dao1.getLatestDeadlines(id).size()==0){
            	int year = Integer.parseInt(dao3.getUser(id).getYear());
            	String semester =dao3.getUser(id).getSemester();
            	
            	request.setAttribute("deadline", dao1.getDeadlines(semester, year));
            	
            }else{
            	 request.setAttribute("deadline", dao1.getLatestDeadlines(id).get(0));
            }
                
        }else if (action.equalsIgnoreCase("payment")){
        	forward = PAYMENT;
        	
        }else if (action.equalsIgnoreCase("mastercard")){
        	forward = TUITION;
        	request.setAttribute("fees", dao.getFeesById(id)); 
        }else if (action.equalsIgnoreCase("pro_fees")){
        	forward = PROGRAM_LIST;
        	
        	request.setAttribute("programs", dao2.getPrograms()); 
        }else if (action.equalsIgnoreCase("update_fees")){
        	forward = UPDAET_FEES;
        	String pro_name = request.getParameter("name");
        	request.setAttribute("pro", dao2.getProgramByName(pro_name)); 
        	request.setAttribute("name", pro_name); 
        }else if (action.equalsIgnoreCase("account_users")){
        	forward = STUDENT_ACCOUNTS;
        	List<String> users = new ArrayList<String>();
        	List<String> accts = new ArrayList<String>();
        	users= dao3.getAllStudents();
        	accts=dao.getAccountId();
        	
        	for(int i=0; i<users.size();i++){
        		
    				if(!accts.contains(users.get(i))){
    					dao.insertAccountUser(users.get(i));
    					dao.setNewAccount(users.get(i));
    				}else{
    					dao.updateAccountByUser(users.get(i));
    					  					
    				}
        	}
        	
        
        	request.setAttribute("accounts", dao.getAccounts()); 
        	
        }
        else if (action.equalsIgnoreCase("cancel")){
			 forward = CANCEL; 
		 }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String forward ="";
	     String action = request.getParameter("action");	     
	     String user = request.getParameter("account");
	     
	     
		 if (action.equalsIgnoreCase("pay")){
			 Double amount = Double.parseDouble(request.getParameter("amount"));			 
			 forward = VISA; 
			 request.setAttribute("paid", amount);
			 
		 }else if (action.equalsIgnoreCase("process")){
			 Double amount = Double.parseDouble(request.getParameter("amount"));
			 forward = SUMMARY; 
			 dao.updateAccountById(user, amount);
			 request.setAttribute("paid", amount);
		 }else if (action.equalsIgnoreCase("update_fees")){
			 
			 String name = request.getParameter("name");
			 Double fees = Double.parseDouble(request.getParameter("pro_fees"));
			 dao2.updateProgramFees(name, fees);
        	 forward = PROGRAM_LIST;
        	 request.setAttribute("programs", dao2.getPrograms()); 
	     }
		 		 
		 RequestDispatcher view = request.getRequestDispatcher(forward);
	     view.forward(request, response);
	 }

}
