package com.jin.model;



public class Course {
	
	private String courseid;
    private String deptid;
    private String coursenumber;
    private String coursename;
    private String program;
    private String credit;
    private int year;
    private String semester;
   
    
    
    public String getCourseid() {
        return courseid;
    }
    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
    public String getDeptid() {
        return deptid;
    }
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
    public String getCoursenumber() {
        return coursenumber;
    }
    public void setCoursenumber(String coursenumber) {
        this.coursenumber = coursenumber;
    }
    public String getCoursename() {
        return coursename;
    }
    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
    public String getCredit() {
        return credit;
    }
    public void setCredit(String credit) {
        this.credit = credit;
    }
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
      
}