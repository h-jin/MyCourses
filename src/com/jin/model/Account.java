package com.jin.model;

import java.sql.Timestamp;

public class Account {

	private String id;
    private Double payment;
    private Double balance;
    private Timestamp deadline;
    private String year;
    private String semester;
    private String name;
    private Double fees;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public Double getPayment() {
        return payment;
    }
    public void setPayment(Double payment) {
        this.payment = payment;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    public Timestamp getDeadline() {
        return deadline;
    }
    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getFees() {
        return fees;
    }
    public void setFees(Double fees) {
        this.fees = fees;
    }
    
}
