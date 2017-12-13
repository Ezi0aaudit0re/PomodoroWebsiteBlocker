/**
 * This file reset the hosts file
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

public class resetHosts{

    /**
     * We use this to directly reset the hosts file
     */
    public static void main (String[] args) {
        resetHosts set = new resetHosts();
        set.reset();
    }

    private IO ioObj = null;;
    private String file = null;
    private String hosts = null;
    private String test_file = null;
    private File outFile = null;

    public resetHosts(){
    
        ioObj = new IO();
        file = "./TXTFiles/hosts.txt";
        test_file = "./TXTFiles/hosts1.txt";
        hosts = "/etc/hosts";
        outFile = new File(hosts);
    
    }

    /**
     * This file creats a backup of the hosts file and saves it as hosts.txt
     */
    public void backupHostFile(){

        try{
        
            if(new File(file).exists()){
                return;
            }
            if(this.outFile.canRead()){
                Scanner sf = new Scanner(this.outFile); 
                ArrayList<String> lineInHostFile = ioObj.read(hosts);
                     
                // loop through the lines in the /etc/hosts
                PrintWriter outstream = ioObj.write(file);
                for(int itter =0, len = lineInHostFile.size(); itter < len; itter++){
                    outstream.append(lineInHostFile.get(itter) + "\n");
                }
                System.out.println("Backed up hosts files in resetHosts.java");

                outstream.close();
                
            
            
            }
            else{
                throw new PermissionException("Please run the command with a sudo previlages"); 
            }
        
        
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        
        }
    
    
    
    
    
    }

    /**
     * This file resets the hosts file to its orignal form
     * copies from hosts.txt file
     */
    public void reset() {
        PrintWriter outStream = null;
        try{
            ArrayList<String> lines = ioObj.read(this.file);

            if(!outFile.canWrite()){
                throw new PermissionException("run with sudo");
            }

            outStream = ioObj.write(hosts);
            for(int itter =0, len = lines.size(); itter < len; itter++){
                outStream.append(lines.get(itter) + "\n"); 
            }

            outStream.close();
        
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }



}
