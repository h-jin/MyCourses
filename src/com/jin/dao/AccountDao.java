package com.jin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jin.util.DbUtil;
import com.jin.model.Account;
import com.jin.model.Course;
import com.jin.model.Fees;

import java.util.List;
import java.util.ArrayList;



public class AccountDao {
	
private Connection connection;
	
	public AccountDao() {
        connection = DbUtil.getConnection();
    }
	
	public Account getAccountById(String user){
    	
    	Account acc = new Account();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from account where id ='"+user+"'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			acc.setId(rs.getString("id"));
    			acc.setPayment(rs.getDouble("payment"));
    			acc.setDeadline(rs.getTimestamp("deadline"));
    			
    	
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
      	return acc;
    }
	public List<Account> getAccounts(){
    	
		List<Account> list = new ArrayList<Account>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select * from account";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			Account acc= new Account();
    			acc.setId(rs.getString("id"));
    			acc.setPayment(rs.getDouble("payment"));
    			acc.setBalance(rs.getDouble("balance"));
    			acc.setDeadline(rs.getTimestamp("deadline"));
    			
    			list.add(acc);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
      	return list;
    }
	public List<String> getAccountId(){
    	
		List<String> list = new ArrayList<String>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select id from account";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			String acc= new String();
    			acc=rs.getString("id");
    			
    			
    			list.add(acc);
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
      	return list;
    }
	public Double getFeesById(String user){
    	    Double fee =0.0;	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select fees from users,account,program where username=account.id and program=name and account.id ='"+user+"'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			fee=rs.getDouble("fees");
    			
    	
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
      	return fee;
    }
	
		public Fees getOtherFees(){
		    Fees other_fee = new Fees();	
		try{
			Statement stmt= connection.createStatement();
			String sql= "select * from fees";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				other_fee.setInsurance(rs.getDouble("insurance"));
				other_fee.setRegistration(rs.getDouble("registration"));
				
		
			}
			
		}catch (SQLException e){
			
			e.printStackTrace();
			
		}
		
	  	return other_fee;
	}
	public Double getBalanceById(String user){
	    Double balance =0.0;	
	try{
		Statement stmt= connection.createStatement();
		String sql= "select balance from account where id ='"+user+"'";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			
			balance=rs.getDouble("balance");
			
	
		}
		
	}catch (SQLException e){
		
		e.printStackTrace();
		
	}
		
	
  	return balance;
 }
	
	public Double getPaymentById(String user){
	    Double payment =0.0;	
	try{
		Statement stmt= connection.createStatement();
		String sql= "select payment from account where id ='"+user+"'";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			
			payment=rs.getDouble("payment");
			
	
		}
		
	}catch (SQLException e){
		
		e.printStackTrace();
		
	}
		
	
  	return payment;
 }
	
	public void updateAccountById(String user, Double amount ){ //payment
    	
    
    	Double fee= getFeesById(user);
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "update account set payment=payment+'"+amount+"', balance='"+fee+"'-payment where id ='"+user+"'";
    		stmt.executeUpdate(sql);
    		
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}

    }
	public void updateAccountByUser(String user){ //change program
    	
	    
    	Double fee= getFeesById(user);
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "update account set balance='"+fee+"'-payment where id ='"+user+"'";
    		stmt.executeUpdate(sql);
    		
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}

    }
	public void insertAccountUser(String user){
    		
    	try{
    		Statement stmt= connection.createStatement();
    		
    		String sql= "insert into account set id='"+user+"'";
    		
    		stmt.executeUpdate(sql);
    		
    		
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}

    }
	public void setNewAccount(String user){
		
		Double fee= getFeesById(user);
    	try{
    		Statement stmt= connection.createStatement();
    		
    		String sql= "update account set payment='0', balance='"+fee+"'-payment where id='"+user+"'";
    		
    		stmt.executeUpdate(sql);
    		
    		
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    }
	
}
