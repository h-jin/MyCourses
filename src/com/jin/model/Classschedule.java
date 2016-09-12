package com.jin.model;

import java.sql.Time;

public class Classschedule {	
	private int id;
    private int year;
    private String semester;
    private String day;
    private String classroom;
    private Time starthour;
    private Time endhour;
    private String courseid;
    private String instructor;
    private String deptid;
    private String coursenumber;
    private String coursename;
    private String program;
    private String credit;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public String getClassroom() {
        return classroom;
    }
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
   
    public Time getStarthour() {
        return starthour;
    }
    public void setStarthour(Time starthour) {
        this.starthour = starthour;
    }
    public Time getEndhour() {
        return endhour;
    }
    public void setEndhour(Time endhour) {
        this.endhour = endhour;
    }
    public String getCourseid() {
        return courseid;
    }
    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
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
}
