package com.jin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jin.util.DbUtil;
import com.jin.model.Department;

import java.util.List;
import java.util.ArrayList;

public class DepartmentDao {
	
	private Connection connection;

    public DepartmentDao() {
        connection = DbUtil.getConnection();
    }
    
    public List<Department> getDeptInfo(){
    	
    	List <Department> dept = new ArrayList <Department>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from department";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			Department dp = new Department ();
    			dp.setId(rs.getString("id"));
    			dp.setDeptname(rs.getString("deptname"));
    			dp.setContact(rs.getString("contact"));
    			
    			dept.add(dp);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return dept;
    	
    }
    public List<Department> getDeptInfoExceptUnknown(){
    	
    	List <Department> dept = new ArrayList <Department>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from department where id!='unknown'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			Department dp = new Department ();
    			dp.setId(rs.getString("id"));
    			dp.setDeptname(rs.getString("deptname"));
    			dp.setContact(rs.getString("contact"));
    			
    			dept.add(dp);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return dept;
    	
    }
    
    public List<String> getDeptName(){
    	
    	List <String> dept = new ArrayList <String>();
    	String str="";
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select deptname from department";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			
    			str=rs.getString("deptname");
    		
    			
    			dept.add(str);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return dept;
    	
    }
    
    public List<String> getDeptNameExceptCurrent(String str){
    	
    	List <String> dept = new ArrayList <String>();
    	String str1="";
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select deptname from department where id!='"+str+"'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			
    			str1=rs.getString("deptname");
    		
    			
    			dept.add(str1);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return dept;
    	
    }
    public List<String> getDeptId(){
    	
    	List <String> ids = new ArrayList <String>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select id from department";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			String dept = "";
    			dept = rs.getString("id");

    			
    			ids.add(dept);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return ids;
    	
    }
    public Department getDeptInfoById(String id){
    	
    	Department dp = new Department ();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from department where id= '"+id+"'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			
    			dp.setId(rs.getString("id"));
    			dp.setDeptname(rs.getString("deptname"));
    			dp.setContact(rs.getString("contact"));

    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return dp;
    	
    }
    
    public void deleteDept(String id){
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "delete from department where id = '"+id+"'";
    		stmt.executeUpdate(sql);
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    }
    
    public void updateDept(Department dept){
    	
    	try{
    		
    		PreparedStatement preparedStatement = connection
                    .prepareStatement( "update department set deptname=?,contact=? where id=?");
           
        	preparedStatement.setString(1,dept.getDeptname());
        	preparedStatement.setString(2,dept.getContact());
        	preparedStatement.setString(3,dept.getId());
        	preparedStatement.executeUpdate();
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    }
    public void updateDeptContact(String id, String con){
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql ="update department set contact='"+con+"' where id='"+id+"'";
    		stmt.executeUpdate(sql);
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    }
    public void addDept(Department dept){
    	
    	try{
    		
    		PreparedStatement preparedStatement = connection
                    .prepareStatement( "insert into department (id,deptname,contact) values (?,?,?)");
           
    		preparedStatement.setString(1,dept.getId());
    		preparedStatement.setString(2,dept.getDeptname());
        	preparedStatement.setString(3,dept.getContact());
        	
        	preparedStatement.executeUpdate();
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    }
    

}
