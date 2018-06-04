/*
*
*
* This class reads command file from command line. Also, creates an output file name as
* monitoring.txt
*
* User enters the file name on command line and program open this file on Main class and
* then, pass this class.
*
* This class have some of methods to calculate or write specific thins on output file.
*
* Reading, calling methods and writing on output file operations are implemented readCommandFile
* on method.
*
*
* */



import java.io.*;
import static java.lang.System.exit;

public class Command {
    /*Defines field*/
    private String[] peopleOnCommandFile = new String[100]; //This array stores the peopleID's which are implementing on command file.
    private int peopleNum = 0; //Counts the number of people who are implementing on command file.

    /*Creates object related to reader class' to read files*/
    FoodReader fr = new FoodReader();
    PeopleReader pr = new PeopleReader();
    SportReader sr = new SportReader();
    FileWriter fw  = null; //Created an object from FileWriter class to write output on monitoring.txt

    public void readCommandFile(File file) throws IOException {

        int lineNumber = countCommandLineNumber(file); //Find and assign the how many lines are there on command file.

        fw = new FileWriter("monitoring.txt");  //Creates an output file

        /* Reads the input files in their own classes and gets with accessor methods. */
        fr.readFoodFile();
        Food[] foodArray = fr.getNewFoodArray();

        pr.readPeopleFile();
        People[] peopleArray = pr.getNewPeopleArray();

        sr.readSportFile();
        Sport[] sportArray = sr.getNewSportsArray();

        try {

            BufferedReader br = new BufferedReader(new FileReader(file)); // Command file reads
            String str;

            try {
                int i = 0;

                while ((str = br.readLine()) != null) { //Command file reads line by line and assign into str variable

                    /* This if condition is implemented when the command says that print the status of specific person*/
                    if (str.substring(0, 5).equalsIgnoreCase("print") && str.substring(5, 6).equalsIgnoreCase("(")) {
                        String id = str.substring(6, 11);  //Takes the person ID


                        /* Tries to find person from ID. */
                        for (int j = 0; j < peopleArray.length; j++) {
                            if (id.equals(peopleArray[j].getPeopleID())) {
                                fw.write(peopleArray[j].getPeopleName() + "\t" + (2018 - peopleArray[j].getPeopleYear()) + "\t"
                                        + peopleArray[j].getDailyCalorieNeed() + "kcal\t" + peopleArray[j].getTakenCalorie() +
                                        "kcal\t" + peopleArray[j].getBurnedCalorie() + "kcal\t" +
                                        (peopleArray[j].getTakenCalorie()-peopleArray[j].getBurnedCalorie() - peopleArray[j].getDailyCalorieNeed()) +
                                        "kcal");
                                if ( i == lineNumber-1) {
                                    continue;
                                } else { //Puts the newline character if this command is not in the last line
                                    fw.write("\n");
                                    printAsteriks();
                                }
                                break;
                            }
                        }
                        i++;

                    /* This condition is implemented if the command wants to everyone's status whose names are passed command file */
                    } else if (str.equalsIgnoreCase("printList")) {
                        printList(peopleOnCommandFile, peopleArray); //Calls the printList method

                        if ( i == lineNumber-1) {
                            continue;
                        } else { //Writes on the output file if asterisk characters are necessary
                            fw.write("\n");
                            printAsteriks();
                        }

                        i++;

                    /* If the command is not print or printList, program implements this block */
                    } else {
                        String[] lineOfCommand; //Line splits and stores this String array
                        lineOfCommand = str.split("\t");

                        if (lineOfCommand[1].substring(0, 1).equals("2")) {
                            int j, x;

                            /* Tries to match two sport ids which are in command file and sport file */
                            for (j = 0; j < sportArray.length; j++) {
                                if (sportArray[j].getSportID().equals(lineOfCommand[1])) {
                                    break;
                                }
                            }

                            /* Tries to match two person ids which are in command file and people file */
                            for (x = 0; x < peopleArray.length; x++) {
                                if (peopleArray[x].getPeopleID().equals(lineOfCommand[0])) {
                                    break;
                                }
                            }

                            /* Calculates the burned calorie because of doing sport */
                            double calorie = calculateBurnedCalorie(sportArray[j].getSportCalorie(), Integer.parseInt(lineOfCommand[2]));
                            peopleArray[x].calorieBurn(calorie);

                            controlPeopleCommandArray(peopleArray, x, peopleNum);



                            fw.write(peopleArray[x].getPeopleID() + "\thas\tburned\t" + Math.round(calorie) +
                                    "kcal\tthanks\tto\t" + sportArray[j].getSportName());

                            if ( i == lineNumber-1) {
                                continue;
                            } else { //Puts the newline and asterisk characters if they are necessary
                                fw.write("\n");
                                printAsteriks();
                            }



                        } else if (lineOfCommand[1].substring(0, 1).equals("1")) {
                            int j, x;

                            /*Tries to match two food ids which are in command file and food file */
                            for (j = 0; j < foodArray.length; j++) {
                                if (foodArray[j].getFoodID().equals(lineOfCommand[1])) {
                                    break;
                                }
                            }

                            /*Tries to match two people ids which are in command file and people file*/
                            for (x = 0; x < peopleArray.length; x++) {
                                if (peopleArray[x].getPeopleID().equals(lineOfCommand[0])) {
                                    break;
                                }
                            }

                            /*Calculates the taken calorie because of eating food */
                            int calorie = calculateTakenCalorie(foodArray[j].getFoodCalorie(), Integer.parseInt(lineOfCommand[2]));
                            peopleArray[x].calorieUp(calorie);


                            controlPeopleCommandArray(peopleArray, x, peopleNum);

                            fw.write(peopleArray[x].getPeopleID() + "\thas\ttaken\t" + calorie +
                                    "kcal\tfrom\t" + foodArray[j].getFoodName()); //Writes the output on the command file

                            if ( i == lineNumber-1) {
                                continue;
                            } else { //Puts the newline and asterisk characters on command file
                                fw.write("\n");
                                printAsteriks();
                            }


                        }
                        i++;

                    }
                }
            } catch (IOException e) {
                System.out.println("IOException handled!!!!");
                exit(-1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fİle could not found");
            exit(-1);
        }
        fw.close();
    }


    /*This method make control of names which are added or not on peopleOnCommand file
    * to write out on monitoring.txt file correctly.*/
    public void controlPeopleCommandArray(People[] peopleArray, int x, int peopleNum) {
        int counter = 0;
        if (peopleNum == 0) { //If there is no any person on the array, added automatically
            peopleOnCommandFile[0] = peopleArray[x].getPeopleID();
            incrementPeopleNum(); //Increments the number of people number
        } else {
            for (int i = 0; i < peopleNum; i++) {
                if (peopleArray[x].getPeopleID().equals(peopleOnCommandFile[i])) {
                    continue;
                } else {
                    counter++;
                }
            }
            if (counter == peopleNum) {
                peopleOnCommandFile[peopleNum] = peopleArray[x].getPeopleID();
                incrementPeopleNum();
            }
        }
    }


    /*Increments the number of people number*/
    public void incrementPeopleNum() {
        peopleNum++;
    }

    /* This method calls when printList command occurs*/
    public void printList(String[] array, People[] peopleArr) throws IOException{
        int x = 0;
        int counter = 0;

        while ( array[x] != null ) { //Finds how many person are there in array
            counter++;
            x++;
        }

        /*First loop is looking for people whose names are passed on command file
        * Second loop tries to match names*/
        for ( int i = 0 ; i < array.length; i++ ) {
            if ( i < peopleNum ) {
                for (int j = 0; j < peopleArr.length; j++) {
                    if (array[i].equals(peopleArr[j].getPeopleID())) {

                        //If condition does not add any signature, else condition adds plus character
                        if ( (peopleArr[j].getTakenCalorie()-peopleArr[j].getBurnedCalorie() - peopleArr[j].getDailyCalorieNeed()) <= 0 ) {
                            fw.write(peopleArr[j].getPeopleName() + "\t" + (2018 - peopleArr[j].getPeopleYear()) + "\t" +
                                    peopleArr[j].getDailyCalorieNeed() + "kcal\t" + peopleArr[j].getTakenCalorie() +
                                    "kcal\t" + peopleArr[j].getBurnedCalorie() + "kcal\t" +
                                    (peopleArr[j].getTakenCalorie()-peopleArr[j].getBurnedCalorie() - peopleArr[j].getDailyCalorieNeed()) +
                                    "kcal");
                        } else {
                            fw.write(peopleArr[j].getPeopleName() + "\t" + (2018 - peopleArr[j].getPeopleYear()) + "\t" +
                                    peopleArr[j].getDailyCalorieNeed() + "kcal\t" + peopleArr[j].getTakenCalorie() +
                                    "kcal\t" + peopleArr[j].getBurnedCalorie() + "kcal\t" + "+" +
                                    (peopleArr[j].getTakenCalorie()-peopleArr[j].getBurnedCalorie() - peopleArr[j].getDailyCalorieNeed()) +
                                    "kcal");
                        }

                        if ( i < (counter - 1) ) {
                            fw.write("\n");
                        }
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }


    public void printAsteriks() throws IOException{

        fw.write("***************\n");

    }


    public int calculateTakenCalorie(int calorie, int foodsPortion) { // Taken calorie calculates
        return (foodsPortion * calorie );
    }

    public double calculateBurnedCalorie(int calorie, int sportsMinute) { //Burned caloeri calculates
        return ((double)sportsMinute / 60) * calorie;
    }

    public int countCommandLineNumber(File file) { //Counts the number of line
        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;

            while ((str = br.readLine()) != null ) {
                count++;
            }

        } catch (IOException e) {
            System.out.println("IO Exception handled!");
        }

        return count;
    }
}
