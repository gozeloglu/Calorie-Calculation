/*
*
*
* This class reads sport.txt file line by line, parse them
* and these attributes assigns the Sport class with constructor.
*
* Possible exceptions had handled with try-catch blocks.
*
* If something goes wrong execution of program is stops.
*
* Sport ID's stores on newSportsArray.
*
*
*
*/




import java.io.*;
import static java.lang.System.exit;


public class SportReader {
    private String[] line = new String[3];
    private Sport[] newSportsArray = new Sport[50];


    public void readSportFile() {
        File file = new File("sport.txt");

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;

            try {
                int i = 0;
                while ( ( str = br.readLine() ) != null ) {
                    line = str.split("\t");
                    Sport sport = new Sport(line[0], line[1], Integer.parseInt(line[2]));
                    newSportsArray[i] = sport;
                    i++;
                }

                br.close();
            } catch (IOException e) {
                System.out.println("IOException handled");
                exit(-1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sport file could not found!");
            exit(-1);
        }
    }


    //This accessor method return the sport ID's.
    public Sport[] getNewSportsArray() {
        return newSportsArray;
    }

}
