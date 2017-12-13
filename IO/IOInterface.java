/**
 * This file contains the interface for IO class
 */
package IO;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public interface IOInterface{

    public PrintWriter write(String fname) throws FileNotFoundException;
    public ArrayList<String> read(String fname) throws FileNotFoundException;
}
