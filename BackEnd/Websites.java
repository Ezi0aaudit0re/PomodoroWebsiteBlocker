/**
 * This class consists of methods that asks the user 
 * For the websites they want to block
 * @author Aman Nagpal
 * @version 1.0
 */
package BackEnd;

import java.util.Scanner;
import java.util.ArrayList;
import IO.IO;

public class Websites{


    private static String blockFile = "./TXTFiles/Blocked.txt";
    private ArrayList<String> websites = null;
    private IO ioObj = null;

    /**
     * This function only runs when we run this file 
     * I use this to write my test code
     */
    public static void main (String[] args) {

        
    }

    /**
     * Constructor 
     */
    public Websites(){
        
       this.websites = new ArrayList<String>(); 
       ioObj = new IO();
       this.websites = this.ioObj.read(Websites.blockFile);

    }


    /**
     * THis function runs a loop and gets websites from the user
     * @return websites ArrayLsit
     */
    public ArrayList<String> addToBlockList(){
        ArrayList<String> websites = new ArrayList<String>(); 

        Scanner sf = new Scanner(System.in);
        System.out.println("Please enter the websites you want to block");
        System.out.println("Enter '-1' anytime when you are done entering the websites");
        // run an infinite loop to get websites from the user
        while(true){
            String str = sf.nextLine();
            if(Helper.checkQuit(str)){
                break; 
            }
            websites.add(str);
        }
    
        return websites; 
    }

    /**
     * This method gets all the elements in the array List 
     * it Basically prints all the websites in the blocked.txt file
     */
    public void getBlockList(){
        Helper.printArrayList(this.websites);
    }

    /**
     * This method asks the user for a website 
     * Then removes that website from the blacklist
     */
    public void removeFromBlockList(){


        System.out.println("Please enter the website you want to remove from the blacklist");
        Scanner sf = new Scanner(System.in);
        String str = sf.nextLine();

        // check if exists and remove from the list
        for(int itter =0, len = this.websites.size(); itter < len; itter++){
            if(this.websites.get(itter).equals(str)){
                this.websites.remove(itter);  
                break;
            } 
        
        }

        // write the new blacklist to the Blocked.txt file 
        Helper.writeToFile(this.websites, Websites.blockFile);

        System.out.println("The website: " + str + " was removed from the blacklist. Here is the new Blacklist");

        // get the new blacklist
        this.websites = this.ioObj.read(Websites.blockFile);

        // print it to the desktop 
        Helper.printArrayList(this.websites);
    
    
    }
    


}
