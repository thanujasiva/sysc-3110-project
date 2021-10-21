import java.util.ArrayList;

public class Player {

    private int money;
    private ArrayList<Property> properties;
    private int id;
    private int position;

    /**
     * @author Sabah
     */
    public Player (){
        this.money = 1500;
        this.position = 0;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int changePosition(int roll){
        this.position += roll;
        return position;
    }

    public int getPosition() {
        return position;
    }

    public void printCurrentState(String property){
        System.out.println("Player " + id);
        System.out.println("Position: " + property);
        System.out.println("Money: $" + money);
    }
}
