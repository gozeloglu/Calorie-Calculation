/*
*
* This class reads people.txt line by line, parse the every lines,
* pass these informations through the People class.
*
* Whenever you want to read People File, just create an object
* related to this class, then call the readPeopleFile method.
*
* Possible exception had handled with try-catch blocks.
*
* If something goes wrong execution of program is stops.
*
* People ID's stores in newPeopleArray.
*
*
* */



import java.io.*;
import static java.lang.System.exit;

public class PeopleReader {
    private String[] line = new  String[6];
    private People[] newPeopleArray = new People[100];

    public void readPeopleFile() {
        File file = new File("people.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;

            try {
                int i = 0;
                while ( ( str = br.readLine() ) != null ) {
                    line = str.split("\t");
                    People person = new People(line[0], line[1], line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]), Integer.parseInt(line[5]));

                    if ( line[2].equalsIgnoreCase("Male") ) {   //Controls the person's genre. If person is male, calls the calorie calculator method.
                        long calorie = person.calculateManDailyCalorie(Integer.parseInt(line[3]), Integer.parseInt(line[4]), Integer.parseInt(line[5]));
                        person.setDailyCalorieNeed(calorie);
                    }
                    else if ( line[2].equalsIgnoreCase("Female") ) {    //Controls the person's genre. If person is female, calls the calorie calculator method.
                        long calorie = person.calculateWomanDailyCalorie(Integer.parseInt(line[3]), Integer.parseInt(line[4]), Integer.parseInt(line[5]));
                        person.setDailyCalorieNeed(calorie);
                    }
                    newPeopleArray[i] = person;
                    i++;
                }

                br.close();
            } catch (IOException e) {
                System.out.println("IOException handled!");
                exit(-1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not found");
            exit(-1);
        }
    }


    //This accessor method returns the people ID's array.
    public People[] getNewPeopleArray() {
        return newPeopleArray;
    }
}
