/*
 * Written by Boh Tuang Hwee, Jehiel (A0139995E)
 * Last updated: 3/5/2016, 8:00pm
 * CS2103
 */
public class InvalidCommand extends Command{
    private final String MESSAGE_INVALID_COMMAND = "Pls enter a valid command";
    
    public InvalidCommand(){
    }
    
    public Display execute(Display display){
        display = new Display(MESSAGE_INVALID_COMMAND);
        return display;
    }
}