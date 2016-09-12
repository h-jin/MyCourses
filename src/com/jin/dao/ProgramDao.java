package com.jin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jin.util.DbUtil;
import com.jin.model.Program;

import java.util.List;
import java.util.ArrayList;

public class ProgramDao {
	
	private Connection connection;
	
	public ProgramDao() {
        connection = DbUtil.getConnection();
    }

	public List<Program> getProgramById(String id){
    	
    	List <Program> prolist = new ArrayList <Program>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from program where deptid ='"+id+"'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			Program pro = new Program();
    			pro.setName(rs.getString("name"));
    			pro.setDeptid(rs.getString("deptid"));
    			pro.setFees(rs.getDouble("fees"));
    			
    			prolist.add(pro);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return prolist;    	
    }
	public List<String> getProgramNameById(String id){
    	
    	List <String> prolist = new ArrayList <String>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select name from program where deptid ='"+id+"'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			String pro = "";
    			pro=rs.getString("name");
    			
    			
    			prolist.add(pro);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return prolist;    	
    }
	public Program getProgramByName(String name){
    	
		Program pro = new Program();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from program where name ='"+name+"'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			
    			pro.setName(rs.getString("name"));
    			pro.setDeptid(rs.getString("deptid"));
    			pro.setFees(rs.getDouble("fees"));
    			pro.setLength(rs.getInt("length"));
    			pro.setDegree(rs.getString("degree"));
    			pro.setCreditrequired(rs.getString("credit_required"));
    			
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return pro;    	
    }
	
	public List<Program> getPrograms(){
    	
    	List <Program> prolist = new ArrayList <Program>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from program where name!='unknown'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			Program pro = new Program();
    			pro.setName(rs.getString("name"));
    			pro.setDeptid(rs.getString("deptid"));
    			pro.setFees(rs.getDouble("fees"));
    			
    			prolist.add(pro);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return prolist;    	
    }
	
	public List<String> getAllProgram(){
    	
		List <String> prolist = new ArrayList <String>();
		
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select name from program";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			String pro="";
    			pro=rs.getString("name");
    			
    			
    			prolist.add(pro);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
    	return prolist;    	
    }
	public void deleteProgramByName(String name){
    	
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= " delete from program where name ='"+name+"'";
    		stmt.executeUpdate(sql);
    		
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
   
    }
	public void updateProgramFees(String name, Double fee){
    	
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "update program set fees='"+fee+"' where name ='"+name+"'";
    		stmt.executeUpdate(sql);
    		
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
   
    }
	public void addProgram(Program pro){
    	
    	
    	try{
    		
    		PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into program (name, deptid) values (?,?)");
    		preparedStatement.setString(1,pro.getName());
    		preparedStatement.setString(2,pro.getDeptid());
    		
    		preparedStatement.executeUpdate();    		
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
   
    }
	
	
	
}
