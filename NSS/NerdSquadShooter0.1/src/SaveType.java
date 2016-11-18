import java.util.Date;

@SuppressWarnings("serial")
public class SaveType implements java.io.Serializable {
    // User Name
    public String playerName;
    // Money
    public int money;
    // Time Played
    public int timePlayed;
    // Text Message
    public String ab;

    public String ac;

    public int ae;
    
    public Date saveTime;

    // Constructor
    public SaveType(){
        this.playerName = "Gosh";
        this.money = 0;
        this.timePlayed = 9000;
    }
    public SaveType(String name, int money, String ab, String ac, int tp) {

        this.playerName = name;
        this.money = money;
        this.timePlayed = tp;

        

    }

    /// Getters And Setters
    public String getName() {
        return playerName;
    }

    public void setName(String aa) {
        this.playerName = aa;
    }

    ///
    public int getMoney() {
        return money;
    }

    public void setMoney(int ad) {
        this.money = ad;
    }

    ///
    public int getTimePlayedInSec() {
        return timePlayed;
    }
    public int getTimePlayed(int division) {
        return timePlayed/division;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }
    ///

}
