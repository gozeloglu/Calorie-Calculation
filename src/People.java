/*
*
* This class has people's attributes in class' field.
* dailyCalorieNeed attribute declared in 'long' type
* because while calculating calorie, method must
* round the result. So, in this case Math.round()
* accepts long numbers
*
* */



public class People {
    private String peopleID;
    private String peopleName;
    private String peopleGenre;
    private int peopleWeight;
    private int peopleHeight;
    private int peopleYear;
    private int takenCalorie;
    private int burnedCalorie;
    private long dailyCalorieNeed;

    /*This constructor assigns object's all of attributes. */
    public People(String peopleID, String peopleName, String peopleGenre, int peopleWeight, int peopleHeight, int peopleYear) {
        this.peopleID = peopleID;
        this.peopleName = peopleName;
        this.peopleGenre = peopleGenre;
        this.peopleWeight = peopleWeight;
        this.peopleHeight = peopleHeight;
        this.peopleYear = peopleYear;
    }


    /*This mutator method assigns new calorie amount to dailyCalorieNeed variable.  */
    public void setDailyCalorieNeed(long newDailyCalorie) {
        dailyCalorieNeed = newDailyCalorie;
    }

    /* These accessor method returns related attributes.*/
    public String getPeopleID() {
        return peopleID;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public int getPeopleYear() {
        return peopleYear;
    }

    public int getTakenCalorie() {
        return takenCalorie;
    }

    public int getBurnedCalorie() {
        return burnedCalorie;
    }

    public long getDailyCalorieNeed() {
        return dailyCalorieNeed;
    }


    //This calorieUp method add new taken calorie to person's takenCalorie variable.
    public void calorieUp(int newAdditionalCalorie) {
        takenCalorie += newAdditionalCalorie;
    }

    //This calorieBurn method add new burned calorie to person's burnedCalorie variable.
    public void calorieBurn(double newBurnedCalorie) {
        burnedCalorie += newBurnedCalorie;
    }

    //This method calculates a man how much calorie will take in one day.
    public long calculateManDailyCalorie (int weight, int height, int year) {
        double result = 66 + (13.75 * weight) + (5 * height) - (6.8 * (2018-year));
        long res = Math.round(result);
        return res;
    }
    //This method calculates a woman how much calorie will take in one day.
    public long calculateWomanDailyCalorie(int weight, int height, int year) {
        double result = 665 + (9.6 * weight) + (1.7 * height) - (4.7 * (2018-year));
        long resultCalorie = Math.round(result);
        return resultCalorie;
    }

}
