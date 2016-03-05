/*
 * Written by Boh Tuang Hwee, Jehiel (A0139995E)
 * Last updated: 3/5/2016, 8:55pm
 * CS2103
 */

import java.util.ArrayList;

public class TaskFloat{
    private String description;
    private String location;
    private ArrayList<String> tags;
    
    public TaskFloat(){
        description = null;
        location = null;
        tags = null;
    }
    
    public TaskFloat(String description, String location, ArrayList<String> tags){
        this.description = description;
        this.location=location;
        this.tags = tags;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setTags(ArrayList<String> tags){
        this.tags= tags;
    }
    
    public ArrayList<String> getTags(){
        return tags;
    }

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}