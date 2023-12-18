import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public abstract class Log {
	
	protected HashMap<String, ArrayList<String>> log;
    
	//Getter and setter
	public HashMap<String, ArrayList<String>> getlog() {return log;}
	public void setlog(HashMap<String, ArrayList<String>> log) {this.log = log;}
	

	public abstract void load_log(String fileName);
	public abstract List<String> print_collection();
	public abstract String print_count();
	
	
}
