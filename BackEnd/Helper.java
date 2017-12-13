/**
 * This class is a helper class and consists a lot of static methods 
 * @author Aman Nagpal 
 * @version 1.0
 */
package BackEnd;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import Exceptions.*;
import IO.IO;

public class Helper{

    public static ArrayList<String> loadWebsites(){

        // path where all the txt file of blocked files is 
        String websitesFilePath = "./TXTFiles/Blocked.txt";
        // arrayList in which all the websites will be stroed
        ArrayList<String> websites = null;
        // io object
        IO ioObj = new IO();

        // get the websites from IO.read
        try{
            websites = ioObj.read(websitesFilePath);
        }
        catch(Exception e){
             System.out.println(e.getMessage());
        }
    
        return websites;
    
    
    }

    /**
     * Loops throght the arrayList provided and prints it 
     */
    public static void printArrayList(ArrayList<String> arr){
        for(int itter = 0, len = arr.size(); itter< len; itter++){
             System.out.println(arr.get(itter));
        }    
    }

    /**
     * This method takes writes the data from ArrayList to the given file 
     * @param arr -> ArrayList 
     * @param fname Name of the file
     */
    public static void writeToFile(ArrayList<String> arr, String fname){

        IO ioObj = new IO();
        // get the outstream object
        PrintWriter outstream = ioObj.write(fname);

        // write to the file
        for(int itter =0, len = arr.size(); itter < len; itter++){
            outstream.append(arr.get(itter)); 
        }
        
        outstream.close(); // close the file 
    }


    /**
     * Overlaod the method writeToFile 
     * @param arr -> Array to write 
     * @param outstream -> PrintWriter Object to write to 
     */
    public static void writeToFile(ArrayList<String> arr, PrintWriter outstream){
        try{
            for(int itter =0, len= arr.size(); itter < len; itter++){
                outstream.append(arr.get(itter) + "\n"); 
            }
        }
        catch(Exception e){
             System.out.println(e.getMessage());
        
        }
    
    
    }

    /**
     * This function checks if the user entered -1
     * @return true if "-1" is entered
     */
    public static boolean checkQuit(String str){
    
        if(str.equals("-1")){
            return true; 
        }
        return false;
    
    }

    /**
     * Check if the user ran the program with the super user commad
     */
    public static void checkSudo() throws PermissionException{
        String fpath = "/etc/hosts";
        File file = new File(fpath);

        if(!file.canWrite()){
            throw new PermissionException();
        }

    
    }

    /**
     * This is a multipurpose method it gets a double value from the user
     */
    public static double getDouble(String ques, int counter){

        Scanner sf = new Scanner(System.in);
        double value = -1.0;
        System.out.println(ques);
        value = sf.nextDouble();
        if(value == -1.0){
            counter++; 
            if(Helper.checkCounter(counter)){
                return value; 
            }
            else{
                Helper.getDouble(ques, counter); 
            }
        }
        return value;

    
    
    }

    /**
     * This method checks if the user entered the incorrect value thrice
     */
    public static boolean checkCounter(int counter){
    
        if(counter == 3){
            return true; 
        }
        else{
            return false; 
        }
    
    }

    





}
