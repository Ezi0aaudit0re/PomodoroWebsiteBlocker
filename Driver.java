/**
 * This file is the driver for the Project
 * @author Aman Nagpal
 * @version 1.0
 */

import API.Logic;
import BackEnd.Timer;


public class Driver{

    public static void main (String[] args) {
        
        Logic logic = new Logic();


         // Stop the user from quiting the program 
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                if(logic.time.getCounter() == Timer.TOTALCOUNTER){
                    logic.reset.reset(); 
                }
                else{
                    long remainingTime = logic.time.getTimeRemaining();
                    logic.startPomodoro(remainingTime, logic.time.getCounter(), logic.getState());
                
                }
            }
        });
        
        try{
            logic.getBlockList();
            if(args.length == 1 && args[0].equals("websites")){
                logic.webObj.addToBlockList();
            } 
            else if(args.length == 1 && args[0].equals("options")){
                logic.setOptions();
            }

            logic.startPomodoro(logic.time.getCounter());
        }
        catch(Exception e){
            System.out.println("An exception got caught");
            System.out.println(e.getClass());
        }

    }


}
