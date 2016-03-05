/*
 * Written by Boh Tuang Hwee, Jehiel (A0139995E)
 * Last updated: 3/5/2016, 8:00pm
 * CS2103
 */

public abstract class Command{
    public Display execute(Display display){
        return display;
    }
}

/*
class DeleteCommand extends Command{
    private ArrayList<Integer> taskNumbers;
    private String invalidTaskNumbersMessage = "You have specified invalid task numbers: ";
    private String deletedMessage = "deleted: ";
    private final String MESSAGE_ERROR = "Error occured while updating to file";
    
    public DeleteCommand(){
        this.taskNumbers = null;
    }
    
    public DeleteCommand(ArrayList<Integer> taskNumbers){
        this.taskNumbers = taskNumbers;
    }
    
    public Display execute(ArrayList<Task> taskList){
        if(hasInvalidTaskNumbers(taskList)){
            setDisplay(invalidTaskNumbersMessage, null);
            return getDisplay();
        }
        
        taskList = deleteTaskFromList(taskList);

        if(updateFile(taskList)){
            History.saveList(taskList);
            setDisplay(deletedMessage, taskList);
        }
        else{
            setDisplay(MESSAGE_ERROR, null);
        }
        return getDisplay();
    }
    
    public boolean hasInvalidTaskNumbers(ArrayList<Task> taskList) {
        ArrayList<Integer> invalidTaskNumbers = new ArrayList<Integer>();
        int taskNum;
        for(int i = 0; i < taskNumbers.size(); i++){
            taskNum = taskNumbers.get(i);
            if((taskNum > taskList.size()) || (taskNum < 1)){
                if(invalidTaskNumbers.size() == 0){
                    invalidTaskNumbersMessage += taskNum;
                }
                else{
                    invalidTaskNumbersMessage += ", " + taskNum;
                }
                invalidTaskNumbers.add(taskNum);
            }
        }
        return (invalidTaskNumbers.size() > 0);
    }
    
    public ArrayList<Task> deleteTaskFromList(ArrayList<Task> taskList) {
        for(int i = 0; i < taskNumbers.size(); i++){
            if(i==0){
                deletedMessage += taskNumbers.get(i);
            }
            else{
                deletedMessage += ", " + taskNumbers.get(i);
            }
            taskList.remove(taskNumbers.get(i) - 1);
        }
        return taskList;
    }
    
    public boolean updateFile(ArrayList<Task> taskList) {
        try{
            Storage.editFile(taskList);
            return true;
        }catch(IOException error){
            return false;
        }
    }
}

class UndoCommand extends Command{
    private final String MESSAGE_UNDO = "Undid last command";
    private final String MESSAGE_ERROR_UNDO = "You have reached the earliest point possible";
    private final String MESSAGE_ERROR = "Error occured while updating to file";
    
    public UndoCommand(){
    }
    
    public Display execute(ArrayList<Task> taskList){
        if(History.atFirstState()){
            setDisplay(MESSAGE_ERROR_UNDO, null);
            return getDisplay();
        }
        
        taskList = History.getTaskList(-1);
        if(updateFile(taskList)){
            setDisplay(MESSAGE_UNDO,taskList);
            History.decrementIndex();
        }
        else{
            setDisplay(MESSAGE_ERROR, null);
        }
        return getDisplay();
    }
    
    public boolean updateFile(ArrayList<Task> taskList) {
        try{
            Storage.editFile(taskList);
            return true;
        }catch(IOException error){
            return false;
        }
    }
}

class RedoCommand extends Command{
    private final String MESSAGE_REDO = "Redid last command";
    private final String MESSAGE_ERROR_REDO = "You have reached the latest point possible";
    private final String MESSAGE_ERROR = "Error occured while updating to file";
    
    public RedoCommand(){
    }
    
    public Display execute(ArrayList<Task> taskList){
        if(History.atLastState()){
            setDisplay(MESSAGE_ERROR_REDO, null);
            return getDisplay();
        }
        
        taskList = History.getTaskList(1);
        if(updateFile(taskList)){
            setDisplay(MESSAGE_REDO, taskList);
            History.incrementIndex();
        }
        else{
            setDisplay(MESSAGE_ERROR,null);
        }
        return getDisplay();
    }
    
    public boolean updateFile(ArrayList<Task> taskList) {
        try{
            Storage.editFile(taskList);
            return true;
        }catch(IOException error){
            return false;
        }
    }
}

class ShowCommand extends Command{
    private final String MESSAGE_NO_TASKS_FOUND = "No such tasks found";
    private String keyword;
    
    public ShowCommand(){
        this.keyword = null;
    }
    
    public ShowCommand(String keyword){
        this.keyword = keyword;
    }
    
    public Display execute(ArrayList<Task> taskList){
        if(keyword == null){
            setDisplay(null, History.getTaskList(0));
            return getDisplay();
        }
        
        ArrayList<Task> searchedTasks = null;
        searchedTasks = getTasksContainingKeyword(taskList);
        if(searchedTasks.isEmpty()){
            setDisplay(MESSAGE_NO_TASKS_FOUND, null);
        }
        else{
            setDisplay(null, searchedTasks);
        }
        
        return getDisplay();
    }
    
    public ArrayList<Task> getTasksContainingKeyword(ArrayList<Task> taskList) {
        ArrayList<Task> tasksContainingKeyword = new ArrayList<Task>();
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).getDescription().contains(keyword)){
                tasksContainingKeyword.add(taskList.get(i));
            }
        }
        return tasksContainingKeyword;
    }
}
class EditCommand extends Command{
    private Integer taskNumber;
    private String description;
    private Calendar addStartDate;
    private Calendar addEndDate;
    private boolean deleteStartDate;
    private boolean deleteEndDate;
    private final String MESSAGE_ERROR_INVALID_TASK_NUMBER = "Pls specify a valid task number";
    
    public EditCommand(){
        this.taskNumber = null;
        this.description = null;
        this.addStartDate = null;
        this.addEndDate = null;
        this.deleteStartDate = false;
        this.deleteEndDate = false;
    }
    
    public EditCommand(Integer taskNumber, String description, Calendar addStartDate, 
            Calendar addEndDate, boolean deleteStartDate, boolean deleteEndDate){
        this.taskNumber = taskNumber;
        this.description = description;
        this.addStartDate = addStartDate;
        this.addEndDate = addEndDate;
        this.deleteStartDate = deleteStartDate;
        this.deleteEndDate = deleteEndDate;
    }
    
    public Display execute(ArrayList<Task> taskList){
        if(hasInvalidTaskNumber(taskList)){
            setDisplay(MESSAGE_ERROR_INVALID_TASK_NUMBER, null);
            return getDisplay();
        }
        editDescription(taskList);
        editStartDate(taskList);
        editEndDate(taskList);
        setDisplay(null, taskList);
        
        return getDisplay();
    }
    
    public boolean hasInvalidTaskNumber(ArrayList<Task> taskList){
        return ((taskNumber > taskList.size()) || (taskNumber < 1));
    }
    
    public void editDescription(ArrayList<Task> taskList) {
        if(description != null){
            taskList.get(taskNumber - 1).setDescription(description);
        }
    }
    
    public void editEndDate(ArrayList<Task> taskList) {
        if(addEndDate != null){
            taskList.get(taskNumber - 1).setEndDate(addEndDate);
        }
        if(deleteEndDate){
            taskList.get(taskNumber - 1).setEndDate(null);
        }
    }
    
    public void editStartDate(ArrayList<Task> taskList) {
        if(addStartDate != null){
            taskList.get(taskNumber - 1).setStartDate(addStartDate);
        }
        if(deleteStartDate){
            taskList.get(taskNumber - 1).setStartDate(null);
        }
    }
}*/