/**
 * This class contains all the methods related to the timer 
 * @author Aman Nagpal 
 * @version 1.0 
 */
package BackEnd;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Timer{

    private double pomodoro = 1;
    private double rest = 1;
    private int counter = 1;
    private Date pomodoroStarted = new Date();
    // this is public so that it is available to other instances
    public static int TOTALCOUNTER = 4;

    /**
     * Returns time in miliseconds
     */
    public long getPomodoro(){
        return (long)this.pomodoro * 60 * 1000; 
    }

    public int getCounter(){
        return this.counter; 
    }



    /**
     * Returns time in milisecond
     */
    public long getRest(){
        return (long)this.rest * 60 * 1000;
    }

    public Date getPomodoroStarted(){
        return this.pomodoroStarted; 
    }

    public void setPomodoro(double value){
        this.pomodoro = value; 
    }

    public void setRest(double value){
        this.rest = value; 
    }

    public void setCounter(int counter){
        this.counter = counter; 
    }

    public void setPomodoroStarted(Date time){
        this.pomodoroStarted = time;
    }


    /**
     * This method changes the pomodoro timer based on the users input
     */
    public void changePomodoro(){

        double value = Helper.getDouble("Please enter new value for pomodoror", 0);
        if(value == -1.0){
             System.out.println("Incorrect value entered pomodor set to default 25 minutes");
        }
        else{
            this.setPomodoro(value); 
        }
         

    
    }

    /**
     * This method changes the rest timer based on the users input
     */
    public void changeRest(){

        double value = Helper.getDouble("Please enter new value for rest", 0);
        if(value == -1.0){
             System.out.println("Incorrect value entered pomodor set to default 25 minutes");
        }
        else{
            this.setRest(value); 
        }
    
    }


    /**
     * This method gives the time remaining in the timer
     */
    public long getTimeRemaining(){
        long miliseconds = Math.abs(new Date().getTime() - this.getPomodoroStarted().getTime()); 
        return miliseconds;

    }

    

}

