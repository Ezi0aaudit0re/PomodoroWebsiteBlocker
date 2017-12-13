/**
 * This file declares the flow of the program
 * @author Aman Nagpal
 * @version 1.0
 */
package API;

import java.util.ArrayList;
import java.util.Scanner;
import Exceptions.*;
import BackEnd.*;

public class Parent{

    private ArrayList<String> websites; // websites to block
    private static String blockFile = "./TXTFiles/Blocked.txt";
    public Websites webObj = null;
    public Timer time = null;
    public Blocker blocker = null;
    public resetHosts reset = null;


    Parent(){

        webObj = new Websites();
        time = new Timer();
        blocker = new Blocker();
        reset = new resetHosts();

        // check if the user ran with the sudo commadn 
        try{
            Helper.checkSudo(); 
            // save the orignal hosts file do as soon as we check we have permission
            reset.backupHostFile();
        }
        catch(PermissionException e){
             System.out.println(e.getMessage());
             System.exit(1);
        }
         
    
    }

    /**
     * This method is used to display the BlackList to the user 
     */
    public void getBlockList(){
        System.out.println("These are the blocked Websites -- ");
        webObj.getBlockList();
    } 


    /**
     * This method starts the blocker 
     */
    public void startBlocker(long time){
         
        try{
            this.blocker.block();
            //Thread.sleep(this.time.getPomodoro());
            Thread.sleep(time);
        }
        catch(PermissionException e){
             System.out.println(e.getMessage());
        }
        catch(InterruptedException e){
             System.out.println("The timer was stopped");
        }
    
    }


    /**
     * This method is an API that unblocks the websites
     */
    public void stopBlocker(){
    
        try{
            this.blocker.unblock();
            // sleep for pomodoro time
            Thread.sleep(this.time.getRest());
        }
        catch(PermissionException e){
            System.out.println(e.getMessage());
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    
    }

    /**
     * This method is used to set the pomodoro timer
     */
    public void setPomodoro(){
    
        Scanner sf = new Scanner(System.in);
        System.out.println("Please enter the time for pomodoro you want to set");
        double pomodoro = sf.nextDouble();
        this.time.setPomodoro(pomodoro);
    
    }

    /**
     * This method is used to set the rest timer
     */

    public void setRest(){

        Scanner sf = new Scanner(System.in);
        System.out.println("Please enter the rest period: ");
        double rest = sf.nextDouble();
        this.time.setRest(rest);
    }

}
