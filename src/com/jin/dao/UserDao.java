package com.jin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jin.model.User;
import com.jin.util.DbUtil;

public class UserDao {

    private Connection connection;

    public UserDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(User us){
       
    	try {
        	
    		PreparedStatement preparedStatement = connection
                    .prepareStatement( "insert into users (username,password,role,id,deptid,program,year,semester,firstname,middlename,lastname,email,status) values (?,AES_ENCRYPT(?,'salt'),?,?,?,?,?,?,?,?,?,?,?)");
    		preparedStatement.setString(1,us.getUsername());
    		preparedStatement.setString(2,us.getPassword());
    		preparedStatement.setString(3,us.getRole());
    		preparedStatement.setString(4,us.getId());    		
    		preparedStatement.setString(5,us.getDeptid());
    		preparedStatement.setString(6,us.getProgram());
    		preparedStatement.setString(7,us.getYear());
    		preparedStatement.setString(8,us.getSemester());
    		preparedStatement.setString(9,us.getFirstname());
    		preparedStatement.setString(10,us.getMiddlename());
    		preparedStatement.setString(11,us.getLastname());
    		preparedStatement.setString(12,us.getEmail());
    		preparedStatement.setString(13,us.getStatus());
    		preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(User us){
        
    	try {
        	
 //   		PreparedStatement preparedStatement = connection
  //                 .prepareStatement( "update users set password=?,role=?,id=?,deptid=?,program=?,year=?,semester=?,firstname=?,middlename=?,lastname=?,email=? where username=?");
    		PreparedStatement preparedStatement = connection
                    .prepareStatement( "update users set role=?,id=?,deptid=?,program=?,year=?,semester=?,firstname=?,middlename=?,lastname=?,email=? where username=?");
    		
    	//	preparedStatement.setString(1,us.getPassword());
    		preparedStatement.setString(1,us.getRole());
    		preparedStatement.setString(2,us.getId());
    		preparedStatement.setString(3,us.getDeptid());
    		preparedStatement.setString(4,us.getProgram());
    		preparedStatement.setString(5,us.getYear());
    		preparedStatement.setString(6,us.getSemester());
    		preparedStatement.setString(7,us.getFirstname());
    		preparedStatement.setString(8,us.getMiddlename());
    		preparedStatement.setString(9,us.getLastname());
    		preparedStatement.setString(10,us.getEmail());
    		preparedStatement.setString(11,us.getUsername());
    		preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(String user) {
        try {
        	Statement stmt=connection.createStatement();
            String sql= "delete from users where username='"+user+"'";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateEmail(String user,String eml) {
        try {

        	
        	Statement stmt=connection.createStatement();
            String sql= "update users set email='"+eml+"' where username='"+user+"'";        
            stmt.executeUpdate(sql);
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateStudentStatus(String user) {
        try {

        	
        	Statement stmt=connection.createStatement();
            String sql= "update users set status='Graduated' where username='"+user+"'";        
            stmt.executeUpdate(sql);
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updatePassword(String user,String pass) {
        try {

        	
        	Statement stmt=connection.createStatement();
      //      String sql= "update users set password='"+pass+"' where username='"+user+"'";  
            String sql= "update users set password=AES_ENCRYPT('"+pass+"','salt') where username='"+user+"'";  
            
            stmt.executeUpdate(sql);
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String checkUser(String user, String pwd){
    	String result="";
    	
        try {
	        	Statement stmt=connection.createStatement();
	  //          String sql= "select * from users where username='"+user+"' and password='"+pwd+"'";
	        	String sql= "select * from users where username='"+user+"' and AES_DECRYPT(password,'salt')='"+pwd+"'";

	        	ResultSet rs=stmt.executeQuery(sql);
	            
	            while (rs.next()) {
	            	if(rs.getString("role").equals("student")){
	            		
	            		result="student";
		
	            	}else if(rs.getString("role").equals("teacher")){
	            		
	            		result= "teacher";
	            		
	            	}else if(rs.getString("role").equals("academic")){
	            		
	            		result= "academic";
	            		
	            	}else if (rs.getString("role").equals("accounting")){
	            		result= "accounting";
	            	}
	            	else{
	            		
	            		result= "admin";
	            	}	
	            }
            	
            }catch (SQLException e) {
                e.printStackTrace();   
                
            }
            return result;       	
    }


    public List<User> getAllUsers() { 
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select username,AES_DECRYPT(password,'salt') as password,role,firstname,middlename,lastname,email,deptid,id,"
            		+ " program,semester,year from users");
            while (rs.next()) {
                User us = new User();
               
                us.setUsername(rs.getString("username"));
            	us.setPassword(rs.getString("password"));
            	us.setRole(rs.getString("role"));
           // 	us.setAccount(rs.getString("account"));
            	us.setDeptid(rs.getString("deptid"));
            	us.setId(rs.getString("id"));
            	us.setProgram(rs.getString("program"));
            	us.setSemester(rs.getString("semester"));
            	us.setYear(rs.getString("year"));
                us.setFirstname(rs.getString("firstname"));
                us.setMiddlename(rs.getString("middlename"));
                us.setLastname(rs.getString("lastname"));
                us.setEmail(rs.getString("email"));
                
                users.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    public List<User> searchStudent(String keyword) { 
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users where role='student' and (deptid='"+keyword+"' or "
            		+ "program='"+keyword+"' or firstname='"+keyword+"' or lastname='"+keyword+"' or id='"+keyword+"' "
            				+ "or email='"+keyword+"' or year='"+keyword+"' or middlename='"+keyword+"' or status='"+keyword+"')";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User us = new User();
               
                us.setUsername(rs.getString("username"));
            	us.setPassword(rs.getString("password"));
            	us.setRole(rs.getString("role"));
            	us.setAccount(rs.getString("account"));
            	us.setDeptid(rs.getString("deptid"));
            	us.setId(rs.getString("id"));
            	us.setProgram(rs.getString("program"));
            	us.setSemester(rs.getString("semester"));
            	us.setYear(rs.getString("year"));
                us.setFirstname(rs.getString("firstname"));
                us.setMiddlename(rs.getString("middlename"));
                us.setLastname(rs.getString("lastname"));
                us.setEmail(rs.getString("email"));
            //    us.setDegree(rs.getString("degree"));
                us.setStatus(rs.getString("status"));
                
                users.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    public List<User> searchStaff(String keyword) { 
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users where role!='student' and role!='admin' and (deptid='"+keyword+"' or "
            		+ "program='"+keyword+"' or firstname='"+keyword+"' or lastname='"+keyword+"' or id='"+keyword+"' "
            				+ "or email='"+keyword+"' or middlename='"+keyword+"')";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User us = new User();
               
                us.setUsername(rs.getString("username"));
            	us.setPassword(rs.getString("password"));
            	us.setRole(rs.getString("role"));
            	us.setAccount(rs.getString("account"));
            	us.setDeptid(rs.getString("deptid"));
            	us.setId(rs.getString("id"));
            	us.setProgram(rs.getString("program"));
            	us.setSemester(rs.getString("semester"));
            	us.setYear(rs.getString("year"));
                us.setFirstname(rs.getString("firstname"));
                us.setMiddlename(rs.getString("middlename"));
                us.setLastname(rs.getString("lastname"));
                us.setEmail(rs.getString("email"));
                
                users.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    public List<User> getStudents() { 
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users where role='student'");
            while (rs.next()) {
                User us = new User();
               
                us.setUsername(rs.getString("username"));
            	us.setPassword(rs.getString("password"));
            	us.setRole(rs.getString("role"));
            	us.setAccount(rs.getString("account"));
            	us.setDeptid(rs.getString("deptid"));
            	us.setId(rs.getString("id"));
            	us.setProgram(rs.getString("program"));
            	us.setSemester(rs.getString("semester"));
            	us.setYear(rs.getString("year"));
                us.setFirstname(rs.getString("firstname"));
                us.setMiddlename(rs.getString("middlename"));
                us.setLastname(rs.getString("lastname"));
                us.setEmail(rs.getString("email"));
             //   us.setDegree(rs.getString("degree"));
                us.setStatus(rs.getString("status"));
                
                users.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    
    public List<User> getStaff() { 
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users where role!='student' and role!='admin'");
            while (rs.next()) {
                User us = new User();
               
                us.setUsername(rs.getString("username"));
            	us.setPassword(rs.getString("password"));
            	us.setRole(rs.getString("role"));
            	us.setAccount(rs.getString("account"));
            	us.setDeptid(rs.getString("deptid"));
            	us.setId(rs.getString("id"));
            	us.setProgram(rs.getString("program"));
            	us.setSemester(rs.getString("semester"));
            	us.setYear(rs.getString("year"));
                us.setFirstname(rs.getString("firstname"));
                us.setMiddlename(rs.getString("middlename"));
                us.setLastname(rs.getString("lastname"));
                us.setEmail(rs.getString("email"));
                
                users.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    public List<String> getAllStudents() {
        List<String> users = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select username from users where role='student'");
            while (rs.next()) {
                String us = "";
               
               us=rs.getString("username");
            	
                
                users.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUser(String uname) {
        User us = new User();
        try {

            Statement statement = connection.createStatement();
     //       ResultSet rs = statement.executeQuery("select * from users where username='"+uname+"'");
            ResultSet rs = statement.executeQuery("select username,AES_DECRYPT(password,'salt') as password, role,account,users.deptid, id, program,semester,year,firstname, lastname,"
            		+ " middlename,email,status,degree from users,program where username='"+uname+"' and name=program");
            
            if (rs.next()) {
            	us.setUsername(rs.getString("username"));
            	us.setPassword(rs.getString("password"));
            	us.setRole(rs.getString("role"));
            	us.setAccount(rs.getString("account"));
            	us.setDeptid(rs.getString("deptid"));
            	us.setId(rs.getString("id"));
            	us.setProgram(rs.getString("program"));
            	us.setSemester(rs.getString("semester"));
            	us.setYear(rs.getString("year"));
                us.setFirstname(rs.getString("firstname"));
                us.setMiddlename(rs.getString("middlename"));
                us.setLastname(rs.getString("lastname"));
                us.setEmail(rs.getString("email"));
                us.setStatus(rs.getString("Status"));
                us.setDegree(rs.getString("degree"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return us;
    }
    public List<String> getAllUserName() {
        List<String> namelist = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select username from users");
            while (rs.next()) {
                String us = "";
               
                us=rs.getString("username");
            	
                
                namelist.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return namelist;
    }
    
    public List<String> getAllUserId() {
        List<String> idlist = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id from users");
            while (rs.next()) {
                String us = "";             
                us=rs.getString("id");
            	
                
                idlist.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idlist;
    }
    
    public List<String> getAllUserIdExceptUnknown() {
        List<String> idlist = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id from users where id!='unknown'");
            while (rs.next()) {
                String us = "";             
                us=rs.getString("id");
            	
                
                idlist.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idlist;
    }
    
    public List<String> getAllUserIdExceptCurrent(String str) {
        List<String> idlist = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select id from users where id!='"+str+"'");
            while (rs.next()) {
                String us = "";             
                us=rs.getString("id");
            	
                
                idlist.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idlist;
    }
    
    public List<String> getAllUserEmailExceptCurrent(String str) {
        List<String> idlist = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select email from users where email!='"+str+"'");
            while (rs.next()) {
                String us = "";             
                us=rs.getString("email");
            	
                
                idlist.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idlist;
    }
    
    public List<String> getAllEmail() {
        List<String> elist = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select email from users");
            while (rs.next()) {
                String us = "";             
                us=rs.getString("email");
            	
                
                elist.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return elist;
    }
    public List<User> getStudentsByDept(String dept) {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users where role='student' and deptid='"+dept+"'");
            while (rs.next()) {
                User us = new User();
               
                us.setUsername(rs.getString("username"));
            	us.setPassword(rs.getString("password"));
            	us.setRole(rs.getString("role"));
            	us.setAccount(rs.getString("account"));
            	us.setDeptid(rs.getString("deptid"));
            	us.setId(rs.getString("id"));
            	us.setProgram(rs.getString("program"));
            	us.setSemester(rs.getString("semester"));
            	us.setYear(rs.getString("year"));
                us.setFirstname(rs.getString("firstname"));
                us.setMiddlename(rs.getString("middlename"));
                us.setLastname(rs.getString("lastname"));
                us.setEmail(rs.getString("email"));
                us.setStatus(rs.getString("status"));
                
                users.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    public List<User> searchStudentWithinDept(String keyword, String dept) {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from users where role='student' and deptid='"+dept+"' and ("
            		+ "program='"+keyword+"' or firstname='"+keyword+"' or lastname='"+keyword+"' or id='"+keyword+"' "
            				+ "or email='"+keyword+"' or year='"+keyword+"' or middlename='"+keyword+"' or status='"+keyword+"')";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User us = new User();
               
                us.setUsername(rs.getString("username"));
            	us.setPassword(rs.getString("password"));
            	us.setRole(rs.getString("role"));
            	us.setAccount(rs.getString("account"));
            	us.setDeptid(rs.getString("deptid"));
            	us.setId(rs.getString("id"));
            	us.setProgram(rs.getString("program"));
            	us.setSemester(rs.getString("semester"));
            	us.setYear(rs.getString("year"));
                us.setFirstname(rs.getString("firstname"));
                us.setMiddlename(rs.getString("middlename"));
                us.setLastname(rs.getString("lastname"));
                us.setEmail(rs.getString("email"));
                us.setStatus(rs.getString("status"));
                
                users.add(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}