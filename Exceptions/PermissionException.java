/**
 * This is a custom Exception which is raised when 
 * We donot hav permission to open the file
 */
package Exceptions;

public class PermissionException extends Exception{

    // Default Constructor
    public PermissionException(){
        // default message to be displayed
        super("Please run the program with sudo Command"); 
    }

    // Constructor with MEssage
    public PermissionException(String msg){
        super(msg); 
        System.exit(0);
    
    }



}
