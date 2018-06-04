/*
* FoodReader Class reads food.txt
* This class has an accessor method to access created
* array from other Class
*
* This class has Input/Output package to catch
* any exception while reading food.txt file.
*
* Also, java.lang.System.exit method had been included
* to exit program if any problem will occur while reading
* files.
* */



import java.io.*;
import static java.lang.System.exit;

public class FoodReader {

    //Arrays declared.
    private String[] line = new String[3];
    private Food[] newFoodsArray = new Food[100];


    /* This method reads food.txt file
    * Then, parses lines by tab character.
    * Creates an object and uses constructor to create field in Food Class.
    *
    * This method has 2 exception to read file with BufferedReader
    * */
    public void readFoodFile() {
        File file = new File("food.txt");

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            String str;

            try {
                int i = 0;
                while ( ( str = br.readLine() ) != null ) {
                    line = str.split("\t");
                    Food food = new Food(line[0], line[1], Integer.parseInt(line[2]));
                    newFoodsArray[i] = food;

                    i++;
                }
                br.close();

            } catch (IOException e) {
                System.out.println("IOExpection handled!");
                exit(-1);
            }
        } catch(FileNotFoundException e) {
            System.out.println("food.txt could not found!");
            exit(-1);
        }
    }

    /* This accessor method returns food array which has been created
    * on readFoodFile method.
    * This method can being called after creating an object from Food class
    * */
    public Food[] getNewFoodArray() {
        return newFoodsArray;
    }
}
