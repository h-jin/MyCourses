package com.jin.model;

public class Grade {
	
	private String username; //student user name
	private String id;		// student ID
	private String courseid;	
    private String firstname;
    private String lastname;
    private String grade;
    private String instructor;
    private String year;
    private String semester;
    private String coursename;
    
   
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCourseid() {
        return courseid;
    }
    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
    
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
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
    
    public String getCoursename() {
        return coursename;
    }
    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

}
