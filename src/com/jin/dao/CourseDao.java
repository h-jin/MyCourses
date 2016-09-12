package com.jin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jin.model.Course;
import com.jin.model.Deadlines;
import com.jin.model.Classschedule;
import com.jin.model.YearSemester;
import com.jin.util.DbUtil;


public class CourseDao {
	private Connection connection;

    public CourseDao() {
        connection = DbUtil.getConnection();
    }
    
    public List<Course> getCourses() {
        List<Course> list = new ArrayList<Course>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from courses,classschedule where courses.courseid=classschedule.courseid";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Course cs =new Course();      
           
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setCoursename(rs.getString("coursename"));
                cs.setCoursenumber(rs.getString("coursenumber"));
            	cs.setProgram(rs.getString("program"));
            	cs.setDeptid(rs.getString("deptid"));          
            	cs.setCredit(rs.getString("credit"));
            	cs.setYear(rs.getInt("year"));
            	cs.setSemester(rs.getString("semester"));
            	
                
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
 /*   public List<Classschedule> getSchedules() {
        List<Classschedule> list = new ArrayList<Classschedule>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from courses, classschedule where courses.courseschedule=classschedule.id";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Classschedule cs =new Classschedule();      
            	
            	cs.setId(rs.getInt("id"));
            	cs.setYear(rs.getInt("year"));
            	cs.setSemester(rs.getInt("semester"));
                cs.setDay(rs.getString("day"));
            	cs.setClassroom(rs.getString("classroom"));
            	cs.setStarthour(rs.getTime("starthour"));          
            	cs.setEndhour(rs.getTime("endhour")); 
            	cs.setCourseid(rs.getString("courseid"));
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }*/
    public List<YearSemester> getYearSemester(){
    	
    	List<YearSemester> list = new ArrayList<YearSemester>();
    	
    	try{
    		Statement stmt= connection.createStatement();
    		String sql= "select classschedule.year, classschedule.semester from courses,classschedule,deadlines where courses.courseid=classschedule.courseid "
    				+ "and classschedule.year=deadlines.year and classschedule.semester=deadlines.semester "
    				+ "group by year,semester order by regdeadline";
    						
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()){
    			
    			YearSemester year_semester = new YearSemester();
    			
    			
    			year_semester.setYear(rs.getString("year"));
    			year_semester.setSemester(rs.getString("semester"));
    			
    			list.add(year_semester);
    			    	
    		}
    		
    	}catch (SQLException e){
    		
    		e.printStackTrace();
    		
    	}
    	
      	return list;
    }

    public List<Course> selectCourses(int year, String semst) {
        List<Course> list = new ArrayList<Course>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from courses, classschedule where courses.courseid=classschedule.courseid and year='"+year+"' and semester='"+semst+"'";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Course cs =new Course();      
                
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setCoursename(rs.getString("coursename"));
                cs.setCoursenumber(rs.getString("coursenumber"));
            	cs.setProgram(rs.getString("program"));
            	cs.setDeptid(rs.getString("deptid"));          
            	cs.setCredit(rs.getString("credit"));
            	cs.setYear(rs.getInt("year"));
            	cs.setSemester(rs.getString("semester"));
                
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<String> getCoursesId() {
        List<String> ids = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select courseid from courses";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	String id ="";      
           
            	id=rs.getString("courseid");
            	
                ids.add(id);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ids;
    }
    
    public List<String> getCoursesNumberBYDeptid(String dpid) {
        List<String> numbers = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select coursenumber from courses where deptid='"+dpid+"'";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	String id ="";      
           
            	id=rs.getString("coursenumber");
            	
                numbers.add(id);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numbers;
    }
    
    public List<String> getCoursesNumberExcept(String dpid, String cid) {
        List<String> numbers = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select coursenumber from courses where deptid='"+dpid+"' and courseid!='"+cid+"'";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	String id ="";      
           
            	id=rs.getString("coursenumber");
            	
                numbers.add(id);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numbers;
    }
    
    public List<String> getCoursesNameBYDeptid(String dpid) {
        List<String> numbers = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select coursename from courses where deptid='"+dpid+"'";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	String id ="";      
           
            	id=rs.getString("coursename");
            	
                numbers.add(id);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numbers;
    }
    
    public List<String> getCoursesNameExcept(String dpid, String cid) {
        List<String> numbers = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select coursename from courses where deptid='"+dpid+"' and courseid!='"+cid+"'";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	String id ="";      
           
            	id=rs.getString("coursename");
            	
                numbers.add(id);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numbers;
    }
    
    public void arrangeCourseSchedule(String cid, int sid) {
        try {
            Statement statement = connection.createStatement();
            String sql= "update courses set courseschedule='"+sid+"' where courseid='"+cid+"'";
            statement.executeUpdate(sql);
           
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
  
    }
    
    public void arrangeLabSchedule(String cid, int sid) {
        try {
            Statement statement = connection.createStatement();
            String sql= "update courses set labschedule='"+sid+"' where courseid='"+cid+"'";
            statement.executeUpdate(sql);
           
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
  
    }
    
    public List<Course> getCoursesById(String id) { //get course by department
        List<Course> list = new ArrayList<Course>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from courses where deptid='"+id+"' ";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Course cs =new Course();      
           
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setCoursename(rs.getString("coursename"));
                cs.setCoursenumber(rs.getString("coursenumber"));
            	cs.setDeptid(rs.getString("deptid"));          
            	cs.setProgram(rs.getString("program"));
            	cs.setCredit(rs.getString("credit"));;
                
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public Course getCoursesByCourseId(String id) { //get course by courseid
    	Course cs =new Course();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from courses where courseid='"+id+"' ";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {     
           
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setCoursename(rs.getString("coursename"));
                cs.setCoursenumber(rs.getString("coursenumber"));
            	cs.setProgram(rs.getString("program"));
            	cs.setDeptid(rs.getString("deptid"));          
            	cs.setCredit(rs.getString("credit"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cs;
    }
    
    public void addCourse(String user, String id) { //registrar courses
    	
        try {
        	
            Statement statement = connection.createStatement();
            String sql= "insert into registration (username, courseid) values ('"+user+"','"+id+"')";

            statement.execute(sql);
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
      
    }
    
    public void addCourses(Course cs ) {
    	
        try {
        	
        	PreparedStatement preparedStatement = connection
                    .prepareStatement( "insert into courses (courseid,deptid,coursenumber,coursename,program,credit) values (?,?,?,?,?,?)");
    		preparedStatement.setString(1,cs.getCourseid());
    		preparedStatement.setString(2,cs.getDeptid());
    		preparedStatement.setString(3,cs.getCoursenumber());
    		preparedStatement.setString(4,cs.getCoursename());
    		preparedStatement.setString(5,cs.getProgram());   
    		preparedStatement.setString(6,cs.getCredit());   
    		
    		
    		preparedStatement.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
      
    }
    
    public void updateCourses(Course cs ) {
    	
        try {
        	
        	PreparedStatement preparedStatement = connection
                    .prepareStatement( "update courses set deptid=?, coursenumber=?,coursename=?,program=?,credit=? where courseid=?");
    		
    		preparedStatement.setString(1,cs.getDeptid());
    		preparedStatement.setString(2,cs.getCoursenumber());
    		preparedStatement.setString(3,cs.getCoursename());
    		preparedStatement.setString(4,cs.getProgram()); 
    		preparedStatement.setString(5,cs.getCredit());
    		preparedStatement.setString(6,cs.getCourseid());
    		
    		
    		preparedStatement.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
      
    }
    
    public void deleteCourse(String id) {
    	
        try {
        	
            Statement statement = connection.createStatement();
            String sql= "delete from courses where courseid='"+id+"'";

            statement.executeUpdate(sql);
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
     
      
    }
    
    public List<Course> searchCourses(String key) {
        List<Course> list = new ArrayList<Course>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from courses,classschedule where courses.courseid=classschedule.courseid and (coursename like '%"+key+"%'"
            		+ " or coursenumber='"+key+"' or deptid='"+key+"')";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Course cs =new Course();      
           
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setCoursename(rs.getString("coursename"));
                cs.setCoursenumber(rs.getString("coursenumber"));
            	cs.setProgram(rs.getString("program"));
            	cs.setDeptid(rs.getString("deptid"));          
            	cs.setCredit(rs.getString("credit"));
            	cs.setYear(rs.getInt("year"));
            	cs.setSemester(rs.getString("semester"));
                
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public Deadlines getDeadline(String id) {
    	
   	 	Deadlines dl = new Deadlines();
   	 	
        try {
        	
            Statement statement = connection.createStatement();
            String sql= "select * from deadlines,courses,classschedule where courseid='"+id+"' and courseschedule=id and classschedule.semester=deadlines.semester";
            ResultSet rs=statement.executeQuery(sql);
            
            while(rs.next()){
            
            	dl.setSemester(rs.getString("semester"));
            
                dl.setRegdeadline(rs.getTimestamp("regdeadline"));
            }
                                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dl;
    }
    
    public void updateCourseSetNull(String course_lab, String cid) {

        try {
        	String sql="";
        	
        	Statement statement = connection.createStatement();
        	if(course_lab.equals("course")){
            sql= "update courses set courseschedule=null where courseid='"+cid+"'";
        	}else if(course_lab.equals("lab")){
        	sql= "update courses set labschedule=null where courseid='"+cid+"'";
        	}
            statement.executeUpdate(sql);
            	
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public void updateCourseSetLabNull(String cid) {

        try {
        	
        	
        	Statement statement = connection.createStatement();
        
  //      	String sql= "update courses set labschedule=null where courseid='"+cid+"'";
            String sql= "update classschedule,courses set classschedule.courseid=null,courses.labschedule=null where id=labschedule and courses.courseid='"+cid+"'";

            statement.executeUpdate(sql);
            	
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    public void updateCourseSetCourseNull(String cid) {

        try {
        	
        	
        	Statement statement = connection.createStatement();
        
       // 	String sql= "update courses set courseschedule=null where courseid='"+cid+"'";
            String sql= "update classschedule,courses set classschedule.courseid=null,courses.courseschedule=null where id=courseschedule and courses.courseid='"+cid+"'";

            statement.executeUpdate(sql);
            	
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public List<Integer> getCoursesSchedule() {
        List<Integer> ids = new ArrayList<Integer>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select courseschedule from courses";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	int id = 0;      
           
            	id=rs.getInt("courseschedule");
            	
                ids.add(id);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ids;
    }
    public List<Integer> getLabSchedule() {
        List<Integer> ids = new ArrayList<Integer>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select labschedule from courses";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	int id = 0;      
           
            	id=rs.getInt("labschedule");
            	
                ids.add(id);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ids;
    }
    
    public int judgeLab(String cid, int id) {
    	
    	int lab=0 ; 
       
        try {
        	
            Statement statement = connection.createStatement();
            String sql= "select * from courses, classschedule where labschedule=id and courses.courseid='"+cid+"' and id='"+id+"'";
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
 
            	lab=rs.getInt("labschedule");
            
                
            }
           
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lab;
      
    }

    
    public int judgeCourse(String cid, int id) {
    	
    	int course =0; 
       
        try {
        	
        	
            Statement statement = connection.createStatement();
            String sql= "select * from courses, classschedule where courseschedule=id and courses.courseid='"+cid+"' and id='"+id+"'";
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
            	course=rs.getInt("courseschedule");
                         
            }
            
         
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return course;

    }


}
