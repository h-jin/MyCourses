package com.jin.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
	 
public class Validate {
		 
		private Pattern pattern1;
		private Matcher matcher1;
		private Pattern pattern2;
		private Matcher matcher2;
		private Pattern pattern3;
		private Matcher matcher3;
		private Pattern pattern4;
		private Matcher matcher4;
		private Pattern pattern5;
		private Matcher matcher5;
		private Pattern pattern6;
		private Matcher matcher6;
		private Pattern pattern7;
		private Matcher matcher7;
		private Pattern pattern8;
		private Matcher matcher8;
		private Pattern pattern9;
		private Matcher matcher9;
		private Pattern pattern10;
		private Matcher matcher10;
		private Pattern pattern11;
		private Matcher matcher11;
		
		private static final String USER_PATTERN = 
				"^([A-Za-z0-9\\_]{3,10})$";
		
		private static final String PASSWORD_PATTERN = 
				"^([_A-Za-z0-9-@*#]{6,32})$";
		
		private static final String ID_PATTERN = 
				"^([A-Za-z0-9\\s]{0,10})$";
		
		private static final String DEPTID_PATTERN = 
				"^([A-Za-z0-9]{4,10})$";
		
		private static final String PROGRAM_PATTERN = 
				"^([A-Za-z\\s]{0,45})$";
		
		private static final String YEAR_PATTERN = 
				"^([2]{1,1})+([0-9]{3,3})$";
		
		private static final String SEMESTER_PATTERN = 
				"^([1-3]{0,1})$";
		
		private static final String NAME_PATTERN = 
				"^([A-Za-z\\s\\.]{0,45})$";
		
		private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";
		
		private static final String DEPTNAME_PATTERN = 
				"^([A-Za-z\\s]{4,45})$";
		
		private static final String CONTACT_PATTERN = 
				"^([A-Za-z0-9\\s]{0,255})$";
	 
		public Validate() {
			pattern1 = Pattern.compile(EMAIL_PATTERN);
			pattern2 = Pattern.compile(USER_PATTERN);
			pattern3=  Pattern.compile(PASSWORD_PATTERN);
			pattern4=  Pattern.compile(ID_PATTERN);
			pattern5=  Pattern.compile(DEPTID_PATTERN);
			pattern6=  Pattern.compile(PROGRAM_PATTERN);
			pattern7=  Pattern.compile(YEAR_PATTERN);
			pattern8=  Pattern.compile(SEMESTER_PATTERN);
			pattern9=  Pattern.compile(NAME_PATTERN);
			pattern10= Pattern.compile(DEPTNAME_PATTERN);
			pattern11= Pattern.compile(CONTACT_PATTERN);
		}
	 
		public boolean validator(final String hex) { //validate email
	 
			matcher1 = pattern1.matcher(hex);
			return matcher1.matches();	 
		}
		
		public boolean validatorU(final String hex) { //validate user name
			 
			matcher2 = pattern2.matcher(hex);
			return matcher2.matches();	 
		}
		public boolean validatorP(final String hex) { //validate password
			 
			matcher3 = pattern3.matcher(hex);
			return matcher3.matches();	 
		}
		public boolean validatorR(final String hex) {// validate role
			 
			if(hex.equals("admin")||hex.equals("teacher")||hex.equals("student")||hex.equals("academic")||hex.equals("accounting")){
				return true;
			}else {
				return false;
			}
		}
		public boolean validatorId(final String hex) { //validate id
			 
			matcher4 = pattern4.matcher(hex);
			return matcher4.matches();	 
		}
		public boolean validatorDpid(final String hex) { //validate department id
			 
			matcher5 = pattern5.matcher(hex);
			return matcher5.matches();	 
		}
		public boolean validatorPm(final String hex) { //validate program name
			 
			matcher6 = pattern6.matcher(hex);
			return matcher6.matches();	 
		}
		public boolean validatorY(final String hex) { //validate year
			 
			matcher7 = pattern7.matcher(hex);
			return matcher7.matches();	 
		} 
		public boolean validatorS(final String hex) { //validate semester
			 
			matcher8 = pattern8.matcher(hex);
			return matcher8.matches();	 
		}
		public boolean validatorN(final String hex) { //validate first/middle/last name
			 
			matcher9 = pattern9.matcher(hex);
			return matcher9.matches();	 
		}
		public boolean validatorDN(final String hex) { //validate dept name
			 
			matcher10 = pattern10.matcher(hex);
			return matcher10.matches();	 
		}
		public boolean validatorContact(final String hex) { //validate contact
			 
			matcher11 = pattern11.matcher(hex);
			return matcher11.matches();	 
		}
		
}
