/*
*
*This class have fields related to food.txt.
* foodID holds ID of every food in String type
* foodName holds name of every food in String type
* foodCalorie holds amount of food's calories in in type
*
*
* */


public class Food {
    private String foodID;
    private String foodName;
    private int foodCalorie;


    /* This constructor assigns food's ID, name and calorie.*/
    public Food(String foodID, String foodName, int foodCalorie) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodCalorie = foodCalorie;
    }

    /* Accessor methods declared related to foods attributes */
    public String getFoodID() {
        return foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodCalorie() {
        return foodCalorie;
    }

}
