/*
*
* This Sport class have sportID, sportName and sportCalorie
* attributes.
* This class just holds the attributes of Sport file.
*
* */

public class Sport {
    private String sportID;
    private String sportName;
    private int sportCalorie;


    //This constructor assigns the ID, name and calorie.
    public Sport(String sportID, String sportName, int sportCalorie) {
        this.sportID = sportID;
        this.sportName = sportName;
        this.sportCalorie = sportCalorie;
    }


    /*These accessors returns the sport's attributes.*/
    public String getSportID() {
        return sportID;
    }

    public String getSportName() {
        return sportName;
    }

    public int getSportCalorie() {
        return sportCalorie;
    }

}
