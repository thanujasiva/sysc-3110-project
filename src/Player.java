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
     * @author Shrimei
     * @author Maisha
     */
    public Player (){
        this.money = 1500;
        this.position = 0;
        this.properties = new ArrayList<>();
        this.colourGroupMatch = new HashMap<>();
    }

    /**
     * @author Sabah
     * @param id       the player id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * @author Shrimei
     * @return         the player id.
     */
    public int getId() {
        return id;
    }

    /**
     * @author Shrimei
     * @param roll      the dice roll amount
     * @return          the new position
     */
    public int changePosition(int roll){
        this.position += roll;
        return position;
    }

    /**
     * @author Sabah
     * @return          the list of properties owned
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * @author Shrimei
     * @return          the current position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @author Shrimei
     * @author Thanuja
     * @param property  the current property
     */
    public void printCurrentState(String property){
        System.out.println("Player " + id);
        System.out.println("Position: " + property);
        System.out.println("Money: $" + money);
        if (this.properties.size() > 0) {
            System.out.println("Current properties you own: ");
            for (Property property1 : this.properties) {
                System.out.println(property1.toString());
            }
        }
    }


    /**
     * @author Maisha
     * @author Shrimei
     * @param property      the property being purchased.
     */
    public void purchaseProperty(Property property){
        int amount = property.getPrice();
        if (this.money >= amount){
            this.money = this.money - amount;
            properties.add(property);
            colourGroupMatch.merge(property.getColourGroup(), 1, Integer::sum);
            property.setOwner(this);
            System.out.println("Congratulations! You now own " + property.getName());
            if(colourGroupMatch.get(property.getColourGroup()) == property.getColourGroup().getMax()) {
                System.out.println("You have a colour group! " + property.getColourGroup());
            }
        } else {
            System.out.println("You don't have enough money");
        }
    }

    /**
     * @author Maisha
     * @author Shrimei
     * @author Thanuja
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

    /**
     * @author Maisha
     * @param property      the property receiving rent
     */
    public void receiveRent(Property property){
        int rent = property.getRent();
        this.money += rent;
    }

    /**
     * @author Shrimei
     * @param property      the property receiving rent
     * @return              the rent amount
     */
    public int colourSetOwned(Property property){
        if(colourGroupMatch.get(property.getColourGroup()) == property.getColourGroup().getMax()){
            return property.getRentWithColourSet();
        } else {
            return property.getRent();
        }
    }
}
