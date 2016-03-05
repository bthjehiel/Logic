import java.util.ArrayList;

public class TaskLists {
    private static ArrayList<ArrayList<TaskFloat>> list;
    
    public TaskLists(){
        list  = new ArrayList<ArrayList<TaskFloat>>();
    }
    
    public TaskLists (ArrayList<ArrayList<TaskFloat>> list){
        this.list = list;
    }
    
    public ArrayList<TaskFloat> getFloat(){
        return list.get(0);
    }
    public ArrayList<TaskFloat> getEvents(){
        return list.get(1);
    }
    public ArrayList<TaskFloat> getDeadline(){
        return list.get(2);
    }
    public ArrayList<TaskFloat> getReserved(){
        return list.get(3);
    }
    public ArrayList<TaskFloat> getDone(){
        return list.get(4);
    }
}
