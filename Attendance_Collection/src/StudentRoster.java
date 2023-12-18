import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentRoster extends Student{
	
	//Loads names into roster
	public void load_roster(String rosterName)
	{
		//Create new roster list
		ArrayList<String> theRoster = new ArrayList<>();
		
		//Open the file
		try (BufferedReader reader = new BufferedReader(new FileReader(rosterName))){
			String line = null;
			//Read in lines and add names to roster
	        while ((line = reader.readLine()) != null) {
	        	theRoster.add(line.trim());
	        }
	        //Close the file
	        reader.close();
		 
	    //Catch exception if file didn't open
		}catch (IOException e) {
			e.printStackTrace();
	    }
			
		//Set modified ArrayList
		setRoster(theRoster);
	}
	
	//Prints data in roster
	public List<String> print_collection() {
		List<String> list = new ArrayList<>();
		if (roster != null) {
			for (String name : roster) {
				System.out.println(name);
				list.add(name);
			}
		}
		return list;
	}
	
	//Prints number of students in roster
	public String print_count(){
		if (roster != null) {
			System.out.println("There were " + roster.size() + " records in this query.");
			return "There were " + roster.size() + " records in this query.";
		} else {
			System.out.println("There were 0 records in this query.");
			return "There were 0 records in this query.";
		}
	}
}
