package com.jin.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jin.util.DbUtil;

import java.sql.Timestamp;

import com.jin.model.Classschedule;
import com.jin.model.Deadlines;

import java.util.ArrayList;
import java.util.List;

public class DeadlinesDao {
	
	private Connection connection;

    public DeadlinesDao() {
        connection = DbUtil.getConnection();
    }
    
    public Deadlines getDeadlines(String semst,int yr){
    	Deadlines dl= new Deadlines();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from deadlines where semester='"+semst+"' and year='"+yr+"'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			dl.setSemester(rs.getString("semester"));
    			dl.setYear(rs.getInt("year"));
    			dl.setDpdeadline(rs.getTimestamp("dpdeadline"));
    			dl.setPdeadline(rs.getTimestamp("pdeadline"));
    			dl.setRegdeadline(rs.getTimestamp("regdeadline"));
    			
    		}
    		
    	}catch (SQLException e ){
    		
    		e.printStackTrace();
    		
    	}  	
    	return dl;
    }
    public Deadlines getAllDeadlines(){
    	Deadlines dl= new Deadlines();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from deadlines";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			dl.setSemester(rs.getString("semester"));
    			dl.setYear(rs.getInt("year"));
    			dl.setDpdeadline(rs.getTimestamp("dpdeadline"));
    			dl.setPdeadline(rs.getTimestamp("pdeadline"));
    			dl.setRegdeadline(rs.getTimestamp("regdeadline"));
    			
    		}
    		
    	}catch (SQLException e ){
    		
    		e.printStackTrace();
    		
    	}  	
    	return dl;
    }
    
    public List <Deadlines> getLatestDeadlines(String student){
    	
    	List<Deadlines> list = new ArrayList<Deadlines>();
   	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from deadlines, registration where username='"+student+"' "
    				+ "and registration.year=deadlines.year and registration.semester=deadlines.semester order by pdeadline DESC";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			Deadlines dl= new Deadlines();
    			
    			dl.setSemester(rs.getString("semester"));
    			dl.setYear(rs.getInt("year"));
    			dl.setDpdeadline(rs.getTimestamp("dpdeadline"));
    			dl.setPdeadline(rs.getTimestamp("pdeadline"));
    			dl.setRegdeadline(rs.getTimestamp("regdeadline"));
    			
    			list.add(dl);
    			
    		}
    		
    	}catch (SQLException e ){
    		
    		e.printStackTrace();
    		
    	}  	
    	return list;
    }

}
