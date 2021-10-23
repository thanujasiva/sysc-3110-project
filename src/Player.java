import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private int money;
    private ArrayList<Property> properties;
    private HashMap<ColourGroups, Integer> colourGroupMatch;
    private int id;
    private int position;

    /**
     * @author Sabah
     */
    public Player (){
        this.money = 1500;
        this.position = 0;
        this.properties = new ArrayList<>();
        this.colourGroupMatch = new HashMap<>();
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


    /**
     * @author Maisha
     * @param property      the property being purchased.
     */
    public void purchaseProperty(Property property){
        int amount = property.getPrice();
        if (this.money >= amount){
            this.money = this.money - amount;
            properties.add(property);
            if(colourGroupMatch.get(property.getColourGroup())==null){
                colourGroupMatch.put(property.getColourGroup(), 1);
            } else{
                colourGroupMatch.put(property.getColourGroup(), colourGroupMatch.get(property.getColourGroup())+1);
            }
            property.setOwner(this);
            System.out.println("Congratulations! You now own " + property.getName());
            if(colourGroupMatch.get(property.getColourGroup()) == property.getColourGroup().getMax()) {
                //int newRent = property.getRent() * 2;
                System.out.println("You have a colour group! " + property.getColourGroup());
            }
        } else {
            System.out.println("You don't have enough money");
        }
    }

    /**
     * @author Maisha
     * @param property      the property to pay rent for.
     * @return              returns true if player is able to pay rent, else false.
     */
    public boolean payRent(Property property){
        int rent = property.getOwner().colourSetOwned(property);
        if (this.money >= rent){
            System.out.println("You have to pay rent. Amount: $" + rent);
            this.money -= rent;
            return true;
        }else{
            System.out.println("You cannot pay rent");
            return false;
        }
    }

    public void receiveRent(Property property){
        int rent = property.getRent();
        this.money += rent;
    }

    public int colourSetOwned(Property property){
        if(colourGroupMatch.get(property.getColourGroup()) == property.getColourGroup().getMax()){
            return property.getRent()*2;
        } else {
            return property.getRent();
        }
    }
}
