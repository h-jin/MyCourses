package com.jin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.dao.CourseDao;
import com.jin.dao.ClassscheduleDao;
import com.jin.dao.DeadlinesDao;
import com.jin.dao.RegistrationDao;
import com.jin.dao.ProgramDao;
import com.jin.model.Course;
import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;

public class CourseController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private CourseDao dao;
    private ClassscheduleDao dao2;
    private DeadlinesDao daod;
    private ProgramDao daop;
    private static String LIST_COURSES = "/courselist.jsp";
    private static String SEARCH_COURSES = "/search.jsp";
    private static String ADVISOR_SEARCH = "/advisor_search.jsp";
    private static String ADD_COURSE = "/add.jsp"; //registration
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String COURSES_BY_DEPTID = "/courses_by_department.jsp";
    private static String ADD_COURSES = "/add_courses.jsp";
    private static String UPDATE_COURSES = "/update_courses.jsp";
    private static String LIST_COURSES_DETAILS = "/course_details.jsp";
    private static String ERROR_MSG = "/error_msg.jsp";
    private static String SELECTED = "/selected.jsp";
    private static String ADVISOR_SELECTED = "/advisor_selected.jsp";
    
    
    public CourseController() {
        super();
        dao = new CourseDao();
        dao2 = new ClassscheduleDao();
        daod = new DeadlinesDao();
        daop = new ProgramDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        
    //    String username = request.getParameter("name");
        String courseid = request.getParameter("id");
        
        if (action.equalsIgnoreCase("checkcourse")){
        	forward = LIST_COURSES;
            request.setAttribute("courses", dao.getCourses()); 
            request.setAttribute("count", dao.getCourses().size()); 
            request.setAttribute("year_semester", dao.getYearSemester()); 
                        
        }else if (action.equalsIgnoreCase("advisor_view_courses")){
        	forward = ADVISOR_SEARCH;
            request.setAttribute("courses", dao.getCourses()); 
            request.setAttribute("count", dao.getCourses().size()); 
            request.setAttribute("year_semester", dao.getYearSemester()); 
                        
        }
        else if (action.equalsIgnoreCase("courses_provided")){
           forward = COURSES_BY_DEPTID;
           String dept_id = request.getParameter("id");
           request.setAttribute("courses", dao.getCoursesById(dept_id)); 
           request.setAttribute("count", dao.getCoursesById(dept_id).size()); 
           
        }else if(action.equalsIgnoreCase("add_courses")){
        	forward = ADD_COURSES;
        	String dept_id = request.getParameter("id");
        	request.setAttribute("programs", daop.getProgramNameById(dept_id)); 
        	
        }else if(action.equalsIgnoreCase("update_courses")){
        	forward = UPDATE_COURSES;
        	request.setAttribute("course", dao.getCoursesByCourseId(courseid));
        	
        }else if (action.equalsIgnoreCase("details")){
        	forward = LIST_COURSES_DETAILS;
        	
        	int year = Integer.parseInt(request.getParameter("year"));
        	String semester = request.getParameter("semester");
        	request.setAttribute("course", dao2.getCScheduleByCourseId(courseid, year, semester));
        	request.setAttribute("lab", dao2.getLScheduleByCourseId(courseid, year, semester));  
            request.setAttribute("deadline", daod.getDeadlines(semester, year));
           
           
        } 
        else if(action.equalsIgnoreCase("delete_courses")){
        	forward = COURSES_BY_DEPTID;
            String deptid = request.getParameter("dept_id");
            dao.deleteCourse(courseid);
            request.setAttribute("courses", dao.getCoursesById(deptid)); 
            request.setAttribute("count", dao.getCoursesById(deptid).size());
        	
        }else if (action.equalsIgnoreCase("dl")){
        	forward = ADD_COURSE ;
        	
            request.setAttribute("courses", dao.getCourses()); 
            request.setAttribute("dead", dao.getDeadline(courseid));
           
        } 
        else {
            forward = INSERT_OR_EDIT;
        }
   

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    } 
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String action = request.getParameter("action");
        
		if (action.equalsIgnoreCase("search")){
			
		String key = request.getParameter("keyword");
        forward = SEARCH_COURSES;
        request.setAttribute("courses", dao.searchCourses(key.trim())); 
        request.setAttribute("count", dao.searchCourses(key.trim()).size()); 
        request.setAttribute("year_semester", dao.getYearSemester()); 
        
		}else if (action.equalsIgnoreCase("advisor_search")){
			
		String key = request.getParameter("keyword");
        forward = ADVISOR_SEARCH;
        request.setAttribute("courses", dao.searchCourses(key.trim())); 
        request.setAttribute("count", dao.searchCourses(key.trim()).size()); 
        request.setAttribute("year_semester", dao.getYearSemester()); 
        
		}else if (action.equalsIgnoreCase("advisor_select")){
			
		int year = Integer.parseInt(request.getParameter("year"));
		String semst = request.getParameter("semester");
		forward = ADVISOR_SELECTED;
        request.setAttribute("courses", dao.selectCourses(year, semst));        
        request.setAttribute("count", dao.selectCourses(year, semst).size()); 
        request.setAttribute("year", year); 
        request.setAttribute("semester", semst); 
		}
		else if (action.equalsIgnoreCase("select")){
			
		int year = Integer.parseInt(request.getParameter("year"));
		String semst = request.getParameter("semester");
		forward = SELECTED;
        request.setAttribute("courses", dao.selectCourses(year, semst));        
        request.setAttribute("count", dao.selectCourses(year, semst).size()); 
        request.setAttribute("year", year); 
        request.setAttribute("semester", semst); 
		}
		else if (action.equalsIgnoreCase("add")){
			
			String course_id = request.getParameter("courseid");
			String dept_id = request.getParameter("deptid");
			String course_number = request.getParameter("coursenumber");
			String course_name = request.getParameter("coursename");
			String program = request.getParameter("program");
			String credit = request.getParameter("credit");
			
			Course cs = new Course();	
			if(!course_id.substring(0, 1).equals(dept_id.substring(0, 1))){
				String meg="invalid_id";
				forward = ERROR_MSG;
				request.setAttribute("msg", meg); 
			}
			int size= dao.getCoursesId().size();
			for(int i=0;i<size;i++){
				if(dao.getCoursesId().contains(course_id)){
					String meg="id_existed";
					forward = ERROR_MSG;
					request.setAttribute("msg", meg); 
				}
				
			}
			int sizen=dao.getCoursesNumberBYDeptid(dept_id).size();
			for(int i=0;i<sizen;i++){
				if(dao.getCoursesNumberBYDeptid(dept_id).contains(course_number)){
					String meg="number_existed";
					forward = ERROR_MSG;
					request.setAttribute("msg", meg); 
				}
				
			}
			
			int sizenm=dao.getCoursesNameBYDeptid(dept_id).size();
			for(int i=0;i<sizenm;i++){
				if(dao.getCoursesNameBYDeptid(dept_id).contains(course_name)){
					String meg="name_existed";
					forward = ERROR_MSG;
					request.setAttribute("msg", meg); 
				}
				
			}
			cs.setCourseid(course_id);
			cs.setDeptid(dept_id);
			cs.setCoursenumber(course_number);
			cs.setCoursename(course_name);
			cs.setProgram(program.trim());
			cs.setCredit(credit);
		
			
			if(!dao.getCoursesId().contains(course_id)
					&&!dao.getCoursesNumberBYDeptid(dept_id).contains(course_number)
					&&!dao.getCoursesNameBYDeptid(dept_id).contains(course_name)
					&& course_id.substring(0, 1).equals(dept_id.substring(0, 1))){
				dao.addCourses(cs);		
				forward = COURSES_BY_DEPTID;
		        request.setAttribute("courses", dao.getCoursesById(dept_id)); 
		        request.setAttribute("count", dao.getCoursesById(dept_id).size()); 
				
			}
			
			
		}else if (action.equalsIgnoreCase("update")){
			
			String course_id = request.getParameter("courseid");
			String dept_id = request.getParameter("deptid");
			String course_number = request.getParameter("coursenumber");
			String course_name = request.getParameter("coursename");
			String course_instructor = request.getParameter("instructor");
			
			Course cs = new Course();
		 
			 String trimed_name=course_name.replaceAll("\\s+","");
			 
			if("".equals(trimed_name)){
				
				cs.setCoursename(dao.getCoursesByCourseId(course_id).getCoursename());
			
			}else if(!dao.getCoursesNameExcept(dept_id,course_id).contains(course_name.trim())){
				cs.setCoursename(course_name.trim());
				
			}
			
			
			 String trimed_number=course_number.replaceAll("\\s+","");
			 
			if("".equals(trimed_number)){
				
				cs.setCoursenumber(dao.getCoursesByCourseId(course_id).getCoursenumber());
			
			}else if(!dao.getCoursesNumberExcept(dept_id,course_id).contains(trimed_number)){
				cs.setCoursenumber(trimed_number);
				
			}
			
			
			String trimed_instructor=course_instructor.replaceAll("\\s+","");
		/*	if("".equals(trimed_instructor)){
				cs.setInstructor(dao.getCoursesByCourseId(course_id).getInstructor());
				
			}else{
				cs.setInstructor(course_instructor);

			}*/
			cs.setCourseid(course_id);
			cs.setDeptid(dept_id);
			
			if(dao.getCoursesNumberExcept(dept_id,course_id).contains(trimed_number)
					||dao.getCoursesNameExcept(dept_id,course_id).contains(course_name.trim())){
				forward = ERROR_MSG;
				String message="invalid";
				request.setAttribute("msg", message );
			}else{
				dao.updateCourses(cs);
				
				forward = COURSES_BY_DEPTID;
		        request.setAttribute("courses", dao.getCoursesById(dept_id)); 
		        request.setAttribute("count", dao.getCoursesById(dept_id).size()); 
				
			}
			
			
			
			
		}
		else {
            forward = INSERT_OR_EDIT;
        }
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}
}
