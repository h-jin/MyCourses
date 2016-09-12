package com.jin.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jin.model.Course;
import com.jin.model.Registration;
import com.jin.model.Grade;
import com.jin.model.YearSemester;
import com.jin.util.DbUtil;

public class RegistrationDao {
	private Connection connection;

    public RegistrationDao() {
        connection = DbUtil.getConnection();
    }
    
    public List<Registration> getCoursesWithGrade(String user) {
        List<Registration> list = new ArrayList<Registration>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from registration,courses where username='"+user+"' "
            		+ "and courses.courseid=registration.courseid and grade IS NOT NULL";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Registration cs =new Registration();      
           
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setCoursename(rs.getString("coursename"));
                cs.setCoursenumber(rs.getString("coursenumber"));
            	cs.setDeptid(rs.getString("deptid"));  
            	cs.setGrade(rs.getString("grade"));
            	cs.setYear(rs.getString("year"));
            	cs.setSemester(rs.getString("semester"));
            	cs.setCredit(rs.getString("credit"));
                list.add(cs);

                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<Registration> getCoursesWithoutGradeNotDisc(String user) {
        List<Registration> list = new ArrayList<Registration>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from registration,courses where username='"+user+"' "
            		+ "and courses.courseid=registration.courseid and grade IS NULL"
            		+ " and status IS NULL";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Registration cs =new Registration();      
           
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setCoursename(rs.getString("coursename"));
                cs.setCoursenumber(rs.getString("coursenumber"));
            	cs.setDeptid(rs.getString("deptid"));  
            	cs.setGrade(rs.getString("grade"));
            	cs.setYear(rs.getString("year"));
            	cs.setSemester(rs.getString("semester"));
            	cs.setCredit(rs.getString("credit"));
                list.add(cs);

                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<Registration> getCourses(String user) {
        List<Registration> list = new ArrayList<Registration>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from registration,courses where username='"+user+"' "
            		+ "and courses.courseid=registration.courseid ";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Registration cs =new Registration();      
           
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setCoursename(rs.getString("coursename"));
                cs.setCoursenumber(rs.getString("coursenumber"));
            	cs.setDeptid(rs.getString("deptid"));  
            	cs.setGrade(rs.getString("grade"));
            	cs.setYear(rs.getString("year"));
            	cs.setSemester(rs.getString("semester"));
            	cs.setCredit(rs.getString("credit"));
            	cs.setStatus(rs.getString("status"));
            	
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<String> getCoursesId(String user) {
        List<String> list = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select registration.courseid from registration,courses where username='"+user+"' "
            		+ "and courses.courseid=registration.courseid and status IS NULL ";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	String cs = "";      
           
            	cs = rs.getString("courseid");
               
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public Course getDetails(String id) {
    	Course cs =new Course();  
    	
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from courses where courseid='"+id+"'";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {      	        
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setCoursename(rs.getString("coursename"));
                cs.setCoursenumber(rs.getString("coursenumber"));
          //  	cs.setInstructor(rs.getString("instructor"));
            	cs.setDeptid(rs.getString("deptid"));          
           
            }
                            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cs;
    } 
       
    public void deleteCourse(String user,String id) {  
    	
        try {
        	Statement statement = connection.createStatement();
        	String sql= "delete from registration where username='"+user+"' and courseid='"+id+"' and status IS NULL";
        	statement.execute(sql);
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    } 
    public List<Registration> viewStudents(String user) {  
    	List<Registration> list = new ArrayList<Registration>();
    	
        try {
        	Statement statement = connection.createStatement();
        	String sql= "select registration.username, registration.courseid, grade from users, registration, courses where users.username=registration.username and courses.courseid=registration.courseid and instructor='"+user+"'";
        	ResultSet rs = statement.executeQuery(sql);
        	while(rs.next()){
        		
        		Registration rg= new Registration ();
        		rg.setUsername(rs.getString("username"));
        		rg.setCourseid(rs.getString("courseid"));
        		rg.setGrade(rs.getString("grade"));
        		list.add(rg);
        	}
        	
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return list;
    } 
    public void updateGrade(String user, String gd) {  
    	
        try {
        	Statement statement = connection.createStatement();
        	String sql= "update registration set grade= '"+gd+"' where username='"+user+"'";
        	statement.execute(sql);
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    } 
    
    public void updateStatus(String user, String courseid) {  
    	
        try {
        	Statement statement = connection.createStatement();
        	String sql= "update registration set status= 'DISC' where username='"+user+"' and courseid='"+courseid+"'";
        	statement.execute(sql);
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    } 
    
    public List<YearSemester> getYearSemesterByInstructor(String student){
    	
    	List<YearSemester> list = new ArrayList<YearSemester>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select registration.year, registration.semester from registration,deadlines where username ='"+student+"' "
    				+ "and registration.year=deadlines.year and registration.semester=deadlines.semester group by year,semester order by regdeadline";
    						
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			YearSemester year_semester = new YearSemester();
    			
    			
    			year_semester.setYear(rs.getString("year"));
    			year_semester.setSemester(rs.getString("semester"));
    			
    			list.add(year_semester);
    			    	
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
      	return list;
    }
    
    public void addCourse(String user, String id, String year, String semester, String instructor) { 
    	
        try {
        	
            Statement statement = connection.createStatement();
            String sql= "insert into registration (username, courseid, year, semester, instructor) values ('"+user+"','"+id+"','"+year+"','"+semester+"','"+instructor+"')";

            statement.execute(sql);
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
      
    }
        
}
