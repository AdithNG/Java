import java.util.Scanner;
import java.util.ArrayList;
import java.util.List; 

public class Driver {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//String fileName = "dataAllShow1stAnd2ndClass.txt";
		//String roster = "rosters.txt";
		
		AttendanceApp attendanceApp = new AttendanceApp();
		List<String> query = new ArrayList<>();
		String resultString = "";
		String userInput = "";
		String prevInput = userInput;
		
        while (!userInput.equals("0")) {
            System.out.print("Enter an option (type '0' to exit): ");
            userInput = sc.next();
            
            //Option for load_log
            if (userInput.equals("A")) {
            	System.out.print("Enter filename: ");
            	String fileName = sc.next();
            	System.out.println("Loading records from " + fileName + " ..." );
            	attendanceApp.load_log(fileName);	
            
            //Option for print_collection (log)
            }else if (userInput.equals("B")){
            	System.out.println("Printing Records... ");
            	query = attendanceApp.print_log();
           
            //Option for print_count (log)
            }else if (userInput.equals("C")) {
            	System.out.println("Counting Records...");
            	resultString  = attendanceApp.count_log();
            
            //Option for load_roster
            }else if (userInput.equals("D")) {
            	System.out.print("Enter filename: ");
            	String roster = sc.next();
            	System.out.println("Loading names from " + roster + " ..." );
            	attendanceApp.load_roster(roster);	
            
            //Option for print_collection (roster)
            }else if (userInput.equals("E")) {
            	System.out.println("Printing Records... ");
            	query = attendanceApp.print_roster();
            
            //Option for print_count (roster)
            }else if (userInput.equals("F")) {
            	System.out.println("Counting Records... ");
            	resultString = attendanceApp.count_roster();
            
            //Option for printing missing students
            }else if (userInput.equals("G")) {
            	System.out.println("****** Students missing in class *************");
            	query = attendanceApp.list_students_not_in_class();
            
        	//Option for listing swipe times of student
            }else if (userInput.equals("H")) {
            	System.out.println("\n****** List all swipe in and out for a student *******");
            	System.out.print("Enter student Name (lastName, firstName): ");
            	String name = sc.next() + " " + sc.next();
            	query = attendanceApp.list_all_times_checking_in_and_out(name);
            	
            //Option for listing all check in times
            }else if (userInput.equals("I")) {
            	System.out.println("\n****** Check in times for all students who attended***");
            	query = attendanceApp.list_all_times_checked_in();
            
        	//Option for listing students arriving after given time
            }else if (userInput.equals("J")) {
            	System.out.println("\n****** Students that arrived late ********************");
        		System.out.print("Enter Start Time (HH:MM:SS): ");
        		String time = sc.next();
        		query = attendanceApp.list_students_late_to_class(time);
            
        	//Option to get first student to enter on a given date
            }else if (userInput.equals("K") || (userInput.equals("Q"))) {
            	System.out.println("\n**** First student to enter ****");
            	System.out.print("Enter Date (DD/M/YYYY): ");
        		String date = sc.next();
        		resultString = attendanceApp.get_first_student_to_enter(date);
            
            //Option to get swipe data of particular student
            }else if (userInput.equals("L")) {
            	System.out.println("\n********* Looking up Student Attendance Data ***********");
            	System.out.print("Enter student Name (lastName, firstName): ");
            	String name = sc.next() + " " + sc.next();
            	resultString = attendanceApp.print_attendance_data_for_student(name);
            
            //Option to check if student is present
            }else if (userInput.equals("M")) {
            	System.out.println("\n**** Looking to see if Student was present on date ****");
            	System.out.print("Enter student Name (lastName, firstName): ");
            	String name = sc.next() + " " + sc.next();
            	System.out.print("Enter Date (DD/M/YYYY): ");
        		String date = sc.next();
        		resultString = attendanceApp.is_present(name, date);
        		System.out.println(resultString);
            
            //Option to list all students checked in on a date
            }else if (userInput.equals("N")) {
            	System.out.println("\n**** Students present on this date ****");
            	System.out.print("Enter Date (DD/M/YYYY): ");
        		String date = sc.next();
        		query = attendanceApp.list_all_students_checked_in(date);
            
            //Option to list all students checked in before a certain time
            }else if (userInput.equals("O")) {
            	System.out.println("\n**** Those present on date & before a time assigned ****");
            	System.out.print("Enter Date (DD/M/YYYY): ");
        		String date = sc.next();
        		System.out.print("Enter Start Time (HH:MM:SS): ");
        		String time = sc.next();
        		query = attendanceApp.list_all_students_checked_in_before(date, time);
            
            
            //Option to list attendance count of students who attended given number of days 
        	}else if (userInput.equals("P")) {
        		System.out.println("\n**** Those who attended given number of classes ****");
        		System.out.print("Enter number of days: ");
        		int days = sc.nextInt();
        		query = attendanceApp.list_students_attendance_count(days);
        	
        	//Option to print if query was made
        	}else if (userInput.equals("R")) {
        		if (prevInput.equals("C") || prevInput.equals("F") || prevInput.equals("K") || prevInput.equals("L") || prevInput.equals("M")){
        			System.out.println(resultString);
        		}else {
        			attendanceApp.print_query_list(query);
        		}
        	
        	//Option to print count of previous query
        	} else if (userInput.equals("S")) {
        		if (prevInput.equals("S") || prevInput.equals("F") || prevInput.equals("K") || prevInput.equals("L") || prevInput.equals("M")){
        			System.out.println("Previous query did not return a list");
        		}else {
        			attendanceApp.print_count(query);
        		}
        	}
            prevInput = userInput;
        }	
	}
}
