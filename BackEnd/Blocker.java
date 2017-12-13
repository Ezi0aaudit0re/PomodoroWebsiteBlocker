    /**
 * This class consists the code to block and unblock websites
 * @author Aman Nagpal 
 * @version 1.0
 */
package BackEnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import Exceptions.*;
import IO.IO;
import BackEnd.resetHosts;

public class Blocker{

    // save the path of the file as a final variable
    private final String fpath = "/etc/hosts";
    private final boolean DEBUG = false;
    private ArrayList<String> lines = new ArrayList<String>();
    private File file = null;
    private IO ioObj = null;

    // getters and setters
    // getters 
    public ArrayList<String> getLines(){
        return this.lines; 
    }


    // setters
    public void setLines(ArrayList<String> data){
        this.lines = data; 
    }

    /**
     * Default Constructor to store what is already in the /etc/hosts file
     */
    public Blocker(){
        this.ioObj = new IO();
        this.file = new File(this.fpath);
        try{
            this.setLines(ioObj.read(this.fpath));     
        }
        catch(Exception e){
             System.out.println(e.getMessage());
        }
    
    }

    /**
     * This method blocks the websites
     */
    public void block() throws PermissionException{

        // check if we have permission to write to the file 
        if(this.file.canWrite()){
            PrintWriter outstream = this.ioObj.write(this.fpath);

            // store the websites to block
            ArrayList<String> websites = Helper.loadWebsites();

            if(DEBUG){
                Helper.printArrayList(this.getLines()); 
            }

            // write the basic file lies 
            Helper.writeToFile(this.getLines(), outstream);

            
            // block the websites
            outstream.append("\n\n## Website Blocker working\n");
            for (int itter =0, len = websites.size(); itter < len; itter++){
                outstream.append("0.0.0.0 " + websites.get(itter) + "\n"); 
                outstream.append(":: " + websites.get(itter) + "\n");
            }
            
            try{
            
                Runtime.getRuntime().exec("sudo killall -HUP mDNSResponder");
            }
            catch(Exception e){
                 System.out.println(e.getMessage());
            }

            outstream.append("## Website blocker ending\n");

            // close the file
            outstream.close();
        }
        else{
            throw new PermissionException("Please run the program with sudo command");
        }
    
    }

    /**
     * This function Unblocks the blocked websites
     * IT also throws the PErmission Exception error 
     * This unblocks the file by rewriting the lines to the /etc/hosts file
     */
    public void unblock() throws PermissionException{

        resetHosts reset = new resetHosts();
        reset.reset();

    }

}
