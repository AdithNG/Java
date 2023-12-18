import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class AttendanceLog extends Log{
	
	//Loads data into dictionary
	public void load_log(String fileName) 
	{
		
		//Create new hashmap/dictionary
		HashMap<String, ArrayList<String>> attendanceData = new HashMap<>();
		
		//Open the file
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            String delimiter = ", ";
            
            //Read contents of file line by line and tokenize them
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                
                String lastName = values[0].trim();
                String firstName = values[1].trim();
                
                String time = values[2].trim();
                String date = values[3].trim();
                
                String theName = lastName + delimiter + firstName;
                String timeStamp = time + delimiter + date;
                
                //Add data to hashMap
                if (attendanceData.containsKey(theName)) {
                	attendanceData.get(theName).add(timeStamp);
                }else {
                	ArrayList<String> vector = new ArrayList<>();
                	vector.add(timeStamp);
                	attendanceData.put(theName, vector);
                }
            }
            //Close the file
            br.close();
        
        //Catch exception if file didn't open
		}catch (IOException e) {
            e.printStackTrace();
        }
		
		//Set modified HashMap
		setlog(attendanceData);
	}
	
	//Prints data in dictionary
	public List<String> print_collection() {
		List<String> list = new ArrayList<>();
		if (log != null) {
		    for (String studentKey : log.keySet()) {
	            System.out.println(studentKey + " " + log.get(studentKey));
	            list.add(studentKey + " " + log.get(studentKey));
		    }
	    }
		return list;
	}
	
	//Prints number of records in log
	public String print_count() {
		if (log != null) {
			String result = "There were " + log.size() + " records in this query.";
			System.out.println(result);
			return result;
		} else {
			String result = "There were 0 records in this query.";
			System.out.println(result);
			return result;
		}
	}
}
