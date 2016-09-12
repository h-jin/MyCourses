package com.jin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jin.model.Classschedule;
import com.jin.util.DbUtil;

public class ClassscheduleDao {
	
	private Connection connection;

    public ClassscheduleDao() {
        connection = DbUtil.getConnection();
    }
    
    public List<Classschedule> getAllSchedule() {
        List<Classschedule> list = new ArrayList<Classschedule>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from classschedule,courses where classschedule.courseid=courses.courseid";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Classschedule cs =new Classschedule();      
            	
            	cs.setId(rs.getInt("id"));
            	cs.setYear(rs.getInt("year"));
            	cs.setSemester(rs.getString("semester"));
                cs.setDay(rs.getString("day"));
            	cs.setClassroom(rs.getString("classroom"));
            	cs.setStarthour(rs.getTime("starthour"));          
            	cs.setEndhour(rs.getTime("endhour")); 
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setInstructor(rs.getString("instructor"));
            	cs.setCoursename(rs.getString("coursename"));
            	cs.setCoursenumber(rs.getString("coursenumber"));
            	cs.setCredit(rs.getString("credit"));
            	cs.setDeptid(rs.getString("deptid"));
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<Classschedule> getAllScheduleBySemester(String year, String semester, String student) {
        List<Classschedule> list = new ArrayList<Classschedule>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from classschedule, registration where registration.courseid=classschedule.courseid"
            		+ " and registration.year=classschedule.year and registration.semester=classschedule.semester "
            		+ "and registration.year='"+year+"' and registration.semester='"+semester+"' and username='"+student+"'";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Classschedule cs =new Classschedule();      
            	
            	cs.setId(rs.getInt("id"));
            	cs.setYear(rs.getInt("year"));
            	cs.setSemester(rs.getString("semester"));
                cs.setDay(rs.getString("day"));
            	cs.setClassroom(rs.getString("classroom"));
            	cs.setStarthour(rs.getTime("starthour"));          
            	cs.setEndhour(rs.getTime("endhour")); 
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setInstructor(rs.getString("instructor"));
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Classschedule> getAllLabScheduleBySemester(String year, String semester, String student) {
        List<Classschedule> list = new ArrayList<Classschedule>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from labschedule, registration where registration.courseid=labschedule.courseid "
            		+ " and registration.year=labschedule.year and registration.semester=labschedule.semester "
            		+ "and registration.year='"+year+"' and registration.semester='"+semester+"' and username='"+student+"'";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Classschedule cs =new Classschedule();      
            	
            	cs.setId(rs.getInt("id"));
            	cs.setYear(rs.getInt("year"));
            	cs.setSemester(rs.getString("semester"));
                cs.setDay(rs.getString("day"));
            	cs.setClassroom(rs.getString("classroom"));
            	cs.setStarthour(rs.getTime("starthour"));          
            	cs.setEndhour(rs.getTime("endhour")); 
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setInstructor(rs.getString("instructor"));
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Classschedule> getAllLabSchedule() {
        List<Classschedule> list = new ArrayList<Classschedule>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select * from labschedule";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	Classschedule cs =new Classschedule();      
            	
            	cs.setId(rs.getInt("id"));
            	cs.setYear(rs.getInt("year"));
            	cs.setSemester(rs.getString("semester"));
                cs.setDay(rs.getString("day"));
            	cs.setClassroom(rs.getString("classroom"));
            	cs.setStarthour(rs.getTime("starthour"));          
            	cs.setEndhour(rs.getTime("endhour")); 
            	cs.setCourseid(rs.getString("courseid"));
            	cs.setInstructor(rs.getString("instructor"));
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<String> getAllCourseId() {
        List<String> list = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            String sql= "select courseid from classschedule where courseid IS NOT NULL";
            ResultSet rs = statement.executeQuery(sql);
           
            while (rs.next()) {
            	
            	String cs ="";  
            	
            	cs=rs.getString("courseid");
                list.add(cs);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public void addSchedule(Classschedule cs) {

        try {
        	
        	PreparedStatement preparedStatement = connection
                    .prepareStatement( "insert into classschedule (year,semester,day,classroom,starthour,endhour,courseid,instructor) values (?,?,?,?,?,?,?,?)");
        	
        	preparedStatement.setInt(1,cs.getYear());
        	preparedStatement.setString(2,cs.getSemester());
        	preparedStatement.setString(3,cs.getDay());
        	preparedStatement.setString(4,cs.getClassroom());
        	preparedStatement.setTime(5,cs.getStarthour());
        	preparedStatement.setTime(6,cs.getEndhour());
        	preparedStatement.setString(7,cs.getCourseid());
        	preparedStatement.setString(8,cs.getInstructor());
        	
        	preparedStatement.executeUpdate();
            	
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    public void addLab(Classschedule cs) {

        try {
        	
        	PreparedStatement preparedStatement = connection
                    .prepareStatement( "insert into labschedule (year,semester,day,classroom,starthour,endhour,courseid,instructor) values (?,?,?,?,?,?,?,?)");
        	
        	preparedStatement.setInt(1,cs.getYear());
        	preparedStatement.setString(2,cs.getSemester());
        	preparedStatement.setString(3,cs.getDay());
        	preparedStatement.setString(4,cs.getClassroom());
        	preparedStatement.setTime(5,cs.getStarthour());
        	preparedStatement.setTime(6,cs.getEndhour());
        	preparedStatement.setString(7,cs.getCourseid());
        	preparedStatement.setString(8,cs.getInstructor());
        	
        	preparedStatement.executeUpdate();
            	
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public void updateSchedule(Classschedule cs) {

        try {
        	
        	PreparedStatement preparedStatement = connection
                    .prepareStatement( "update classschedule set year=?,semester=?,day=?,classroom=?,starthour=?,endhour=?, courseid=? where id=?");
           
        	preparedStatement.setInt(1,cs.getYear());
        	preparedStatement.setString(2,cs.getSemester());
        	preparedStatement.setString(3,cs.getDay());
        	preparedStatement.setString(4,cs.getClassroom());
        	preparedStatement.setTime(5,cs.getStarthour());
        	preparedStatement.setTime(6,cs.getEndhour());
        	preparedStatement.setString(7,cs.getCourseid());
        	preparedStatement.setInt(8,cs.getId());
        	preparedStatement.executeUpdate();
            	
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public void updateScheduleSetNull(String cid) {

        try {
        	
        	Statement statement = connection.createStatement();
            String sql= "update classschedule set courseid=null where courseid='"+cid+"'";
            statement.executeUpdate(sql);
            	
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public void updateScheduleSetNullLab(String cid) {

        try {
        	
        	Statement statement = connection.createStatement();
            String sql= "update classschedule,courses set classschedule.courseid=null,courses.labschedule=null where id=labschedule and courses.courseid='"+cid+"'";
            statement.executeUpdate(sql);
            	
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    
    public void updateScheduleSetNullCourse(String cid) {

        try {
        	
        	Statement statement = connection.createStatement();
            String sql= "update classschedule,courses set classschedule.courseid=null,courses.courseschedule=null where id=courseschedule and courses.courseid='"+cid+"'";
            statement.executeUpdate(sql);
            	
                
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
    public void deleteSchedule(String sid) {
      
        try {
            Statement statement = connection.createStatement();
            String sql= "delete from classschedule where id='"+sid+"'";
            statement.executeUpdate(sql);
           
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
    public void deleteLabSchedule(String sid) {
        
        try {
            Statement statement = connection.createStatement();
            String sql= "delete from labschedule where id='"+sid+"'";
            statement.executeUpdate(sql);
           
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
	 public List<Classschedule> getAllScheduleByUser(String user) {
	        List<Classschedule> list = new ArrayList<Classschedule>();
	        try {
	            Statement statement = connection.createStatement();
		        String sql= "select * from registration,courses,classschedule where username='"+user+"' and courses.courseid=registration.courseid "
		          		+ "and classschedule.courseid=courses.courseid and classschedule.year=registration.year and classschedule.semester=registration.semester ";

	            ResultSet rs = statement.executeQuery(sql);
	           
	            while (rs.next()) {
	            	
	            	Classschedule cs =new Classschedule();      
	           
	            	cs.setId(rs.getInt("id"));
	            	cs.setYear(rs.getInt("year"));
	            	cs.setSemester(rs.getString("semester"));
	                cs.setDay(rs.getString("day"));
	            	cs.setClassroom(rs.getString("classroom"));
	            	cs.setStarthour(rs.getTime("starthour"));          
	            	cs.setEndhour(rs.getTime("endhour")); 
	            	cs.setCourseid(rs.getString("courseid"));
	            	cs.setCoursenumber(rs.getString("coursenumber"));
	            	cs.setCoursename(rs.getString("coursename"));
	            	cs.setClassroom(rs.getString("classschedule.classroom"));
	            	cs.setCredit(rs.getString("credit"));
	            	cs.setInstructor(rs.getString("instructor"));
	            	cs.setDeptid(rs.getString("deptid"));
	            	
	                list.add(cs);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 
	
	 
	 public List<Classschedule> getAllLabSchedule(String user) {
	        List<Classschedule> list = new ArrayList<Classschedule>();
	        try {
	            Statement statement = connection.createStatement();
	            String sql= "select * from registration,courses,labschedule where username='"+user+"' and courses.courseid=registration.courseid "
		          		+ "and labschedule.courseid=courses.courseid";
	            ResultSet rs = statement.executeQuery(sql);
	           
	            while (rs.next()) {
	            	
	            	Classschedule cs =new Classschedule();      
	           
	            	cs.setYear(rs.getInt("year"));
	            	cs.setSemester(rs.getString("semester"));
	                cs.setDay(rs.getString("day"));
	            	cs.setClassroom(rs.getString("classroom"));
	            	cs.setStarthour(rs.getTime("starthour"));          
	            	cs.setEndhour(rs.getTime("endhour"));                
	                list.add(cs);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 
	 public Classschedule oneLabSchedule(String id) {
	    	Classschedule cs =new Classschedule(); 
	    	
	        try {
	            Statement statement = connection.createStatement();
	            String sql= "select * from courses,classschedule where labschedule=id and courses.courseid='"+id+"'";
	            ResultSet rs = statement.executeQuery(sql);
	           
	            while (rs.next()) {
	        
	           
	            	cs.setYear(rs.getInt("year"));
	            	cs.setSemester(rs.getString("semester"));
	                cs.setDay(rs.getString("day"));
	            	cs.setClassroom(rs.getString("classroom"));
	            	cs.setStarthour(rs.getTime("starthour"));          
	            	cs.setEndhour(rs.getTime("endhour"));               
	            	
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return cs;
	    }
	 
	 public Classschedule oneSchedule(String id) {
	    	Classschedule cs =new Classschedule(); 
	    	
	        try {
	            Statement statement = connection.createStatement();
	            String sql= "select * from courses,classschedule where courseschedule=id and courses.courseid='"+id+"'";
	            ResultSet rs = statement.executeQuery(sql);
	           
	            while (rs.next()) {
	        
	           
	            	cs.setYear(rs.getInt("year"));
	            	cs.setSemester(rs.getString("semester"));
	                cs.setDay(rs.getString("day"));
	            	cs.setClassroom(rs.getString("classroom"));
	            	cs.setStarthour(rs.getTime("starthour"));          
	            	cs.setEndhour(rs.getTime("endhour"));               
	            	
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return cs;
	    }
	 
	 public Classschedule getScheduleById(String sid) {
	    	Classschedule cs =new Classschedule(); 
	    	
	        try {
	            Statement statement = connection.createStatement();
	            String sql= "select * from classschedule where id='"+sid+"'";
	            ResultSet rs = statement.executeQuery(sql);
	           
	            while (rs.next()) {
	        
	            	cs.setId(rs.getInt("id"));
	            	cs.setYear(rs.getInt("year"));
	            	cs.setSemester(rs.getString("semester"));
	                cs.setDay(rs.getString("day"));
	            	cs.setClassroom(rs.getString("classroom"));
	            	cs.setStarthour(rs.getTime("starthour"));          
	            	cs.setEndhour(rs.getTime("endhour")); 
	            	cs.setCourseid(rs.getString("courseid"));
	            	
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return cs;
	    }
	 
	 public List<Classschedule> getSchedule(int yr, String semst, String day, String room) {
	       
		 List<Classschedule> list = new ArrayList<Classschedule>();
		  
	    	
	        try {
	            Statement statement = connection.createStatement();
	            String sql= "select * from classschedule where year='"+yr+"' and semester='"+semst+"' and day='"+day+"' and classroom='"+room+"'";
	            ResultSet rs = statement.executeQuery(sql);
	            
	            while (rs.next()) {
	            	Classschedule cs =new Classschedule(); 
	            	cs.setId(rs.getInt("id"));
	            	cs.setYear(rs.getInt("year"));
	            	cs.setSemester(rs.getString("semester"));
	                cs.setDay(rs.getString("day"));
	            	cs.setClassroom(rs.getString("classroom"));
	            	cs.setStarthour(rs.getTime("starthour"));          
	            	cs.setEndhour(rs.getTime("endhour"));               
	            	list.add(cs);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 
	 public List<Classschedule> getLab(int yr, String semst, String day, String room) {
	       
		 List<Classschedule> list = new ArrayList<Classschedule>();
		  
	    	
	        try {
	            Statement statement = connection.createStatement();
	            String sql= "select * from labschedule where year='"+yr+"' and semester='"+semst+"' and day='"+day+"' and classroom='"+room+"'";
	            ResultSet rs = statement.executeQuery(sql);
	            
	            while (rs.next()) {
	            	Classschedule cs =new Classschedule(); 
	            	cs.setId(rs.getInt("id"));
	            	cs.setYear(rs.getInt("year"));
	            	cs.setSemester(rs.getString("semester"));
	                cs.setDay(rs.getString("day"));
	            	cs.setClassroom(rs.getString("classroom"));
	            	cs.setStarthour(rs.getTime("starthour"));          
	            	cs.setEndhour(rs.getTime("endhour"));               
	            	list.add(cs);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 
	 public Classschedule getCScheduleByCourseId(String cid, int year, String semester) {
	    	Classschedule cs =new Classschedule(); 
	    	
	        try {
	            Statement statement = connection.createStatement();
	            String sql= "select * from classschedule,courses where courses.courseid= classschedule.courseid and courses.courseid='"+cid+"'"
	            		+ " and year='"+year+"' and semester='"+semester+"'";
	            ResultSet rs = statement.executeQuery(sql);
	           
	            while (rs.next()) {
	        
	            	cs.setId(rs.getInt("id"));
	            	cs.setYear(rs.getInt("year"));
	            	cs.setSemester(rs.getString("semester"));
	                cs.setDay(rs.getString("day"));
	            	cs.setClassroom(rs.getString("classroom"));
	            	cs.setStarthour(rs.getTime("starthour"));          
	            	cs.setEndhour(rs.getTime("endhour")); 
	            	cs.setCourseid(rs.getString("courseid"));
	            	cs.setCoursenumber(rs.getString("coursenumber"));
	            	cs.setCoursename(rs.getString("coursename"));
	            	cs.setClassroom(rs.getString("classschedule.classroom"));
	            	cs.setCredit(rs.getString("credit"));
	            	cs.setInstructor(rs.getString("instructor"));
	            	cs.setDeptid(rs.getString("deptid"));
	            	
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return cs;
	    }
	 
	 public Classschedule getLScheduleByCourseId(String cid,int year, String semester) {
	    	Classschedule cs =new Classschedule(); 
	    	
	        try {
	            Statement statement = connection.createStatement();
	            String sql= "select * from labschedule,courses where courses.courseid= labschedule.courseid and courses.courseid='"+cid+"'"
	            		+ " and year='"+year+"' and semester='"+semester+"'";
	            ResultSet rs = statement.executeQuery(sql);
	           
	            while (rs.next()) {
	        
	            	cs.setId(rs.getInt("id"));
	            	cs.setYear(rs.getInt("year"));
	            	cs.setSemester(rs.getString("semester"));
	                cs.setDay(rs.getString("day"));
	            	cs.setClassroom(rs.getString("classroom"));
	            	cs.setStarthour(rs.getTime("starthour"));          
	            	cs.setEndhour(rs.getTime("endhour")); 
	            	cs.setCourseid(rs.getString("courseid"));
	            	cs.setCoursenumber(rs.getString("coursenumber"));
	            	cs.setCoursename(rs.getString("coursename"));
	            	cs.setClassroom(rs.getString("classschedule.classroom"));
	            	cs.setCredit(rs.getString("credit"));
	            	cs.setInstructor(rs.getString("instructor"));
	            	cs.setDeptid(rs.getString("deptid"));
	            	
	                           
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return cs;
	    }


}
