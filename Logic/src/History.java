/*
 * Written by Boh Tuang Hwee, Jehiel (A0139995E)
 * Last updated: 3/5/2016, 8:00pm
 * CS2103
 */

import java.util.ArrayList;

public class History {
    
    private static ArrayList<String> userInputs;
    private static ArrayList<Display> oldDisplays;
    private static int oldDisplaysIndex;
    
    public static void saveDisplay(Display display) {
        if(oldDisplaysIndex < (oldDisplays.size() -1)){
            for(int i = (oldDisplays.size() - 1); i > oldDisplaysIndex; i--){
                oldDisplays.remove(i);
            }
        }
        oldDisplays.add(display);
        oldDisplaysIndex++;
    }
    
    public static void saveUserInput(String userInput) {
        userInputs.add(userInput);
    }
    
    public static boolean atLastState() {
        return (oldDisplaysIndex == (oldDisplays.size() -1));
    }
    
    public static boolean atFirstState() {
        return (oldDisplaysIndex == 0);
    }

    public static void decrementIndex() {
        oldDisplaysIndex--;
    }

    public static void incrementIndex() {
        oldDisplaysIndex++;
    }

    public static Display getDisplay(int offset) {
        return oldDisplays.get(oldDisplaysIndex + offset);
    }

}
