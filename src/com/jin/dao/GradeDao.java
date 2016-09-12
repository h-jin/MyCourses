package com.jin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jin.util.DbUtil;
import com.jin.model.YearSemester;
import com.jin.model.Grade;

import java.util.List;
import java.util.ArrayList;

public class GradeDao {
	
	private Connection connection;

    public GradeDao() {
        connection = DbUtil.getConnection();
    }
    
    public List<Grade> getGradeByInstructor(String instructor, String year, String semester){
    	
    	List<Grade> list = new ArrayList<Grade>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select registration.username,id,firstname,lastname,grade,instructor,coursename,registration.year,registration.semester from registration, users, courses where registration.username = users.username and"
    				+ " courses.courseid = registration.courseid and instructor ='"+instructor+"' and registration.year ='"+year+"'"
    						+ " and registration.semester ='"+semester+"' group by coursename";
    						
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			Grade grad = new Grade();
    			
    			grad.setId(rs.getString("id"));
    			grad.setFirstname(rs.getString("firstname"));
    			grad.setLastname(rs.getString("lastname"));
    			grad.setGrade(rs.getString("grade"));
    			grad.setInstructor(rs.getString("instructor"));
    			grad.setCoursename(rs.getString("coursename"));
    			grad.setYear(rs.getString("year"));
    			grad.setSemester(rs.getString("semester"));
    			
    			list.add(grad);
    			    	
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
      	return list;
    }
    
    public List<Grade> getCoursesByInstructor(String instructor){
    	
    	List<Grade> list = new ArrayList<Grade>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select registration.username,id,firstname,lastname,grade,instructor,coursename,registration.year,registration.semester, registration.courseid from registration, users, courses where registration.username = users.username and"
    				+ " courses.courseid = registration.courseid and instructor ='"+instructor+"' group by coursename,year,semester";
    						
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			Grade grad = new Grade();
    			
    			grad.setId(rs.getString("id"));
    			grad.setFirstname(rs.getString("firstname"));
    			grad.setLastname(rs.getString("lastname"));
    			grad.setGrade(rs.getString("grade"));
    			grad.setInstructor(rs.getString("instructor"));
    			grad.setCoursename(rs.getString("coursename"));
    			grad.setYear(rs.getString("year"));
    			grad.setSemester(rs.getString("semester"));
    			grad.setCourseid(rs.getString("courseid"));
    			
    			list.add(grad);
    			    	
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
      	return list;
    }
    
    public List<YearSemester> getYearSemesterByInstructor(String instructor){
    	
    	List<YearSemester> list = new ArrayList<YearSemester>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select year, semester from registration where instructor ='"+instructor+"' group by year,semester";
    						
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
    
   
    
    public List<Grade> getGradeByCourse(String instructor, String year, String semester, String course){
    	
    	List<Grade> list = new ArrayList<Grade>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select registration.username,id,courses.courseid,firstname,lastname,grade,instructor,coursename,registration.year,registration.semester from registration, users, courses where registration.username = users.username and"
    				+ " courses.courseid = registration.courseid and instructor ='"+instructor+"' and registration.year ='"+year+"'"
    						+ " and registration.semester ='"+semester+"' and courses.coursename ='"+course+"' and registration.status IS NULL";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			Grade grad = new Grade();
    			
    			grad.setUsername(rs.getString("username"));
    			grad.setId(rs.getString("id"));
    			grad.setCourseid(rs.getString("courseid"));
    			grad.setFirstname(rs.getString("firstname"));
    			grad.setLastname(rs.getString("lastname"));
    			grad.setGrade(rs.getString("grade"));
    			grad.setInstructor(rs.getString("instructor"));
    			grad.setCoursename(rs.getString("coursename"));
    			grad.setYear(rs.getString("year"));
    			grad.setSemester(rs.getString("semester"));
    			
    			list.add(grad);
    			    	
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
      	return list;
    }
 public List<Grade> searchStudent(String instructor, String year, String semester, String student,String courseid){
    	
    	List<Grade> list = new ArrayList<Grade>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select registration.username,id,courses.courseid,firstname,lastname,grade,instructor,coursename,registration.year,registration.semester from registration, users, courses where registration.username = users.username and"
    				+ " courses.courseid = registration.courseid and instructor ='"+instructor+"' and registration.year ='"+year+"'"
    						+ " and registration.semester ='"+semester+"' and courses.courseid ='"+courseid+"'"
    								+ " and (firstname='"+student+"' or lastname='"+student+"' or id='"+student+"')";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			Grade grad = new Grade();
    			
    			grad.setUsername(rs.getString("username"));
    			grad.setId(rs.getString("id"));
    			grad.setCourseid(rs.getString("courseid"));
    			grad.setFirstname(rs.getString("firstname"));
    			grad.setLastname(rs.getString("lastname"));
    			grad.setGrade(rs.getString("grade"));
    			grad.setInstructor(rs.getString("instructor"));
    			grad.setCoursename(rs.getString("coursename"));
    			grad.setYear(rs.getString("year"));
    			grad.setSemester(rs.getString("semester"));
    			
    			list.add(grad);
    			    	
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
      	return list;
    }
    public void updateGrade(String student, String courseid, String grade){
    	  	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "update registration, users set grade='"+grade+"' where registration.username= users.username and id='"+student+"' and courseid='"+courseid+"'";
    				
    		stmt.execute(sql);
  		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}   	   
    }
    public void updateGradeObj(String obj){
	  	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "update registration set grade='"+obj+"' where username='"+obj+"' and courseid='"+obj+"'";
    				
    		stmt.execute(sql);
  		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	   
    }
}
