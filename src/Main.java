/*
*
*
* This Main class takes argument from command line
* This argument is input files name and this name assign
* to file. Then, program starts
*
*
* */


import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;
/* CLOSE WHATEVER IS NEEDED*/

public class Main {
    public static void main(String[] args) {



        try {
            File file = new File(args[0]);
            /* Created an object from Command class to pass argument*/
            Command cmd = new Command();
            cmd.readCommandFile(file);
        } catch (IOException e) { //IO Exception handled in this catch block.
            System.out.println("Input/Output Exception handled");
            exit(-1);       //Execution of program is stops if any exception handled.
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You enter less than expected argument on command line!");
            exit(-1);
        }

    }
}