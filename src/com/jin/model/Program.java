package com.jin.model;

public class Program {
	
	private String name;
    private String deptid;
    private Double fees;
    private int length;
    private String degree;
    private String creditrequired;
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDeptid() {
        return deptid;
    }
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
    
    public Double getFees() {
        return fees;
    }
    public void setFees(Double fees) {
        this.fees = fees;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    
    public String getDegree() {
        return degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    public String getCreditrequired() {
        return creditrequired;
    }
    public void setCreditrequired(String creditrequired) {
        this.creditrequired = creditrequired;
    }

}
