/**
 * This class has the necessary methods required to read and write to a file 
 * @author Aman Nagpal 
 * @version 1.0
 */
package IO;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import IO.IOInterface;


public class IO implements IOInterface{

    /**
     * This method returns a Printwrite object for the file provided to it 
     */
    public PrintWriter write(String fname){

        PrintWriter outStream = null;
        try{
            outStream = new PrintWriter(fname);
            if(outStream == null){
                throw new FileNotFoundException(); 
            }
        }
        catch(Exception e){
             System.out.println(e.getMessage());
        }
        return outStream;
    
    
    }

    /**
     * This method checks if a file exists
     * IF it exists then reads the file save every file in ArrayList 
     * @return ArrayList<string> lines
     */
    public ArrayList<String> read(String fname){


        ArrayList<String> lines = new ArrayList<String>();
        try{

            File file = new File(fname);

            // check if a file exists else throw an error 
            if(file.exists()){
                Scanner sf = new Scanner(file);
                while (sf.hasNextLine()){
                    lines.add(sf.nextLine()); 
                }

                sf.close();
            
            
            }

            
            else{
                throw new FileNotFoundException("The file you are trying to open is missing"); 
            }
            
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lines;
    }



}

