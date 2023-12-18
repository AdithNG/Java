import java.util.ArrayList;
import java.util.List;

public class AttendanceApp {
    
	private static Log log;
	private static Student roster;
	
	//Default constructor
	AttendanceApp(){
		log = new AttendanceLog();
		roster = new StudentRoster();
	}
	
	//Loads file
	public void load_log(String filename) {
		log.load_log(filename);
	}
	
	//Loads roster
	public void load_roster(String rosterFile) {
		roster.load_roster(rosterFile);
	}
	
	//Prints log
	public List<String> print_log() {
		return log.print_collection();
	}
		
	//Prints roster
	public List<String> print_roster() {
		return roster.print_collection();
	}
	
	//counts log entries
	public String count_log() {
		return log.print_count();
	}
			
	//Counts roster entries
	public String count_roster() {
		return roster.print_count();
	}
	
	//prints students not present in class
	public List<String> list_students_not_in_class() {
		//Create a list to hold absentees
		List<String> absentees = new ArrayList<>();
		
		//Separate out students that did not attend
		if(roster.getRoster() != null && log.getlog() != null) {
		    for (String student : roster.getRoster()) {
			    if (!log.getlog().containsKey(student)) {
				    absentees.add(student);
			    }
		    }
		}
		print_query_list(absentees);
		return absentees;
	}
	
	//Prints checking time of given student
	public List<String> list_all_times_checking_in_and_out(String student) {
		List<String> list = new ArrayList<>();
		
		//If student attended a class
		if(roster.getRoster() != null && log.getlog() != null) {
		    if (log.getlog().containsKey(student)) {
			    for (int i = 0; i < log.getlog().get(student).size(); i++) {
				    list.add(student + ", " + log.getlog().get(student).get(i));
			    }
        
            //If no record was found
		    } else {
                list.add("No student named " + student + " in the attendance log.");
            }
		}
		print_query_list(list);
		return list;
	}
	
	//Gives check-in time of all students
	public List<String> list_all_times_checked_in() {
		List<String> list = new ArrayList<>();
		
		//Separate check-in times
		if(roster.getRoster() != null && log.getlog() != null) {
		    for (String student : roster.getRoster()) {
			    if (log.getlog().containsKey(student)) {
				    list.add(student + ", " + log.getlog().get(student).get(0));
			    }
		    }
		}
		print_query_list(list);
		return list;
	}
	
	//Lists out students late to class
	public List<String> list_students_late_to_class(String startTime) {
		List<String> list = new ArrayList<>();
		
		//Separate late students
		if(roster.getRoster() != null && log.getlog() != null) {
		    for (String student : log.getlog().keySet()) {
			    for (String record : log.getlog().get(student)) {
				    String time = record.split(", ")[0];
				    if (time.compareTo(startTime) > 0) {
					    list.add(student + ", " + record);
				    }
			    }
		    }
		}
		print_query_list(list);
		return list;
	}
	
	//Prints attendance data of student
	public String print_attendance_data_for_student(String student) {
		
		if(roster.getRoster() != null && log.getlog() != null) {
			//If student found
		    if (log.getlog().containsKey(student)) {
			    String print = student + " " + log.getlog().get(student);
			    System.out.println(print);
			    return print;
		
	        //If student not found
		    }else{
			    String print = "No student of this name in the attendance log";
			    System.out.println(print);
			    return print;
		    }
		}
		return "";
	}
	
	//Tells if student was present on a date
	public String is_present(String studentName, String date) {
		
		if(roster.getRoster() != null && log.getlog() != null) {
			//If student is found
	        if (log.getlog().containsKey(studentName)) {
	        	//Split data of student
	        	for (String record : log.getlog().get(studentName)) {
	        		String[] recordSplit = record.split(", ");
	        		String studentDate = recordSplit[1];
	        		if (studentDate.equals(date)) {
	        			return "True";
	        		}
	            }
	        }
	    }
	    return "False";
	}
	
	//Lists names of students checked in at a date
	public List<String> list_all_students_checked_in(String date){
		List<String> list = new ArrayList<>();
		
		if(roster.getRoster() != null && log.getlog() != null) {
			for (String student : roster.getRoster()) {
				if (is_present(student, date) == "True") {
					list.add(student);
				}
			}
		}
		print_query_list(list);
		print_count(list);
		return list;
	}
	
	//Lists out students late to class
		public List<String> list_all_students_checked_in_before (String startDate, String startTime) {
			List<String> list = new ArrayList<>();
			
			if(roster.getRoster() != null && log.getlog() != null) {
				//Seperate early students
			    for (String student : log.getlog().keySet()) {
				    for (String record : log.getlog().get(student)) {
				    	String date = record.split(", ")[1];
					    String time = record.split(", ")[0];
					    //If date matches
					    if (date.equals(startDate)) {
					        //Check time
					    	if (time.compareTo(startTime) < 0) {
						        list.add(student);
					        }
					    }
				    }
			    }
			}
			print_query_list(list);
			print_count(list);
			return list;
		}
	
	
	public List<String> list_students_attendance_count(int numClasses){
		List<String> list = new ArrayList<>();
		
		if(roster.getRoster() != null && log.getlog() != null) {
			//Get days attended by stedents
			for (String student : roster.getRoster()) {
				//If student didn't attend
		        if (numClasses == 0 && !log.getlog().containsKey(student)) {
		            list.add(student);
		        } else if (numClasses > 0 && log.getlog().containsKey(student)) {
		            if (log.getlog().get(student).size() >= numClasses) {
		                list.add(student);
		            }
		        }
		    }
		}
		print_query_list(list);
		print_count(list);
		return list;
	}
	
	public String get_first_student_to_enter(String StartDate){
		String first_student = "";
	    String current_time = "23:59:59";
	    
	    if(roster.getRoster() != null && log.getlog() != null) {
	    	for (String student : log.getlog().keySet()) {
	            for (String record : log.getlog().get(student)) {
	                String[] tokens = record.split(", ");
	                String time = tokens[0];
	                String student_date = tokens[1];
	                if (student_date.equals(StartDate) && time.compareTo(current_time) < 0) {
	                    current_time = time;
	                    first_student = student;
	                }
	            }
	        }
	    }
	    System.out.println(first_student);
	    return first_student;
	}
	
	
	//Prints out a list
	public void print_query_list(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	//Prints out length of list
	public String print_count(List<String> list) {
		String print = "There were " + list.size() + " records for this query";
		System.out.println(print);
		return print;
	}
}
