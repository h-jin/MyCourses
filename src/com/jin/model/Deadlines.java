package com.jin.model;

import java.sql.Timestamp;

public class Deadlines {
	
	
    private String semester;
    private int year;
    private Timestamp regdeadline;
    private Timestamp dpdeadline;
    private Timestamp pdeadline;
    
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    
    public Timestamp getRegdeadline() {
        return regdeadline;
    }
    public void setRegdeadline (Timestamp regdeadline) {
        this.regdeadline = regdeadline;
    }
    public Timestamp getDpdeadline() {
        return dpdeadline;
    }
    public void setDpdeadline(Timestamp dpdeadline) {
        this.dpdeadline = dpdeadline;
    }
    public Timestamp getPdeadline() {
        return pdeadline;
    }
    public void setPdeadline(Timestamp pdeadline) {
        this.pdeadline = pdeadline;
    }

}

