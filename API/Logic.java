/** 
 * This class consists of the logic of the program 
 * @author Aman Nagpal 
 * @version 1.0
 */
package API;

import Exceptions.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import API.Parent;
import BackEnd.Timer;

public class Logic extends Parent{

    /*
     * THis is used to keep track of weather pomodoro is in rest or pomodoro session
     * 1 = Pomodoro
     * 0 = rest
     */
    private int state = -1;
    private static boolean DEBUG = false;

    /**
     * Test code
     */
    public static void main (String[] args) {
        Logic logic = new Logic();
    }

    public Logic(){
        super(); 
    }

    public int getState(){
        return this.state; 
    }

    public void setState(int state){
        this.state = state; 
    }
    
    /**
     * This method runs when the user provides "start" as a command line argument
     */
    public void startBlocker(){
       super.startBlocker(this.time.getPomodoro());
    }

    /**
     * THis method runs when user doesnot provide an argument 
     */
    public void startProgram(){
    
        super.getBlockList();
        // Ask the user to get more websites to block
        super.webObj.addToBlockList();
        super.startBlocker(this.time.getPomodoro());
    
    }

    /**
     * This method is an API that unblocks the websites
     */
    public void unblock(){
        super.stopBlocker();
    }

    /**
     * THis method starts the timer and loops through it 
     */
    public boolean startPomodoro(int counter){
        if(DEBUG){
             System.out.println("here in the start Pomodoro ");
        }

       // recursion break test
       if(counter >= Timer.TOTALCOUNTER){
           return true;
       } 
       else{

           // set runtime
           Runtime runtime = Runtime.getRuntime();
           String[] arr = new String[2];
           // update the counter
           counter = counter + 1;
           this.time.setCounter(counter);

           this.time.setPomodoroStarted(new Date());
           // change the state of the program to pomodoro
           this.setState(1);
           System.out.println("Starting the website Blocker -----");
           this.startBlocker(this.time.getPomodoro());

           // Notification on apple 
           String script = "../Notifications/Block.scpt";
           arr[0] = "osascript";
           arr[1] = script;
           try{
            runtime.exec(arr);
           }
           catch(Exception e){
               System.out.println("Notification exception");
               System.out.println(e.getMessage());
           
           }

           // chage the state of the program to rest
           System.out.println("Rest started ---");
           this.setState(0);
           this.unblock();
           
           // notification on apple
           script = "../Notifications/Unblock.scpt";
           try{
            runtime.exec(arr);
           }
           catch(Exception e){
               System.out.println("Notification exception");
               System.out.println(e.getMessage());
           
           }

           return this.startPomodoro(this.time.getCounter());
       
       }
    }
    
    
    /**
     * This method starts the pomodoro timer
     * THis method runs when the user ties to quit the program
     */
    public boolean startPomodoro(long time, int counter, int state){

       // recursion break test
       if(counter > Timer.TOTALCOUNTER){
           return true;
       } 

       if(state == 1){
           System.out.println("Website Blocker Continued ----");
           this.startBlocker(time);
           // increase the coutner for next turn
           counter = counter + 1;
           // change state to rest after pomodoro session has completed
           this.setState(0);
       }
       else if (state == 0){
           System.out.println("Rest period Continued --");
            this.unblock(); 
       }
       return this.startPomodoro(counter);
    
    }

    /**
     * This method gives the users options to change
     */
    public void setOptions(){
        super.setPomodoro();
        super.setRest();

        double pomodoroMinutes = TimeUnit.MILLISECONDS.toMinutes(this.time.getPomodoro());
        double restMinutes = TimeUnit.MILLISECONDS.toMinutes(this.time.getRest());

        System.out.println("Pomodoro timer is: " + pomodoroMinutes + " minutes");
        System.out.println("Rest timer is: " + restMinutes + " minites");
    
    }



}
