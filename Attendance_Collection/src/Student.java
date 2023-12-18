import java.util.List;
import java.util.ArrayList;

public abstract class Student {
    protected ArrayList<String> roster;

    //Getter and setter
	public ArrayList<String> getRoster() {return roster;}
	public void setRoster(ArrayList<String> roster) {this.roster = roster;}
	
	public abstract void load_roster(String fileName);
	public abstract List<String> print_collection();
	public abstract String print_count();
	
}
